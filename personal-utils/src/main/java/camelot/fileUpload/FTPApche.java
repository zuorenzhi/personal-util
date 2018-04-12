package camelot.fileUpload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import camelot.props.PropertyUtils;


public class FTPApche {
	private FTPClient ftpClient = null;
    /**ftp服务器ip地址*/
    private static String FILE_SERVER_IP;
    /**ftp服务器用户名*/
    private static String LOGIN_NAME;
    /**ftp服务器密码*/
    private static String LOGIN_PASSWORD;

    /**图片服务器信息初始化*/
    private final static Logger logger= LoggerFactory.getLogger(FTPApche.class);

    static{
//        /*ftp配置*/
        FILE_SERVER_IP = PropertyUtils.getProperty("file_server_ip");
        LOGIN_NAME = PropertyUtils.getProperty("login_name");
        LOGIN_PASSWORD = PropertyUtils.getProperty("login_password");
    }
    /**
     * 使用ftp协议上传图片和使用oss云存储上传图片(上传至bucket根目录里面)
     * @param uploadDir
     * @param inputStream
     * @param suffix
     * @return
     * @throws FileNotFoundException
     */
    public static String uploadFileWithFtpOrOss(String uploadDir, InputStream inputStream, String suffix,MultipartFile file) throws FileNotFoundException {
        FTPUtils ftpUtils = new FTPUtils(FILE_SERVER_IP, LOGIN_NAME, LOGIN_PASSWORD);
        return ftpUtils.upload(uploadDir, inputStream, suffix);
    }

    public static void main(String[] args) throws Exception{
        System.out.println(FTPApche.uploadFileWithFtpOrOss("/imgs/forum", new FileInputStream(new File("d:\\tomcat.gif")), ".gif", null));
    }

    /**
     * ftp文件下载
     * @param fileName       文件名称全路径
     * @param realFileName   下载时文件名
     * @param response       HttpServletResponse
     * @return
     */
    public static Boolean downloadRemote(String fileName,String realFileName,HttpServletRequest request,HttpServletResponse response){
        FTPClient ftp = new FTPClient();
        logger.debug("\n 方法[{}]", "FtpApche-downloadRemote");
        try {
            int reply;
            ftp.connect(FILE_SERVER_IP);
            ftp.login(LOGIN_NAME, LOGIN_PASSWORD);
            // 设置文件传输类型为二进制 
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 获取ftp登录应答代码 
            reply = ftp.getReplyCode();
            // 验证是否登陆成功 
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                System.err.println("FTP server refused connection.");
                return false ;
            }
            // 转移到FTP服务器目录至指定的目录下
            String remotePath = fileName.substring(0,fileName.lastIndexOf("/")+1) ;
            String remoteFile = fileName.substring(fileName.lastIndexOf("/")+1) ;
            changeMakeWorkingDir(remotePath,ftp);
            
            //liiuchao 解决各种浏览器中文名乱码问题
            if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {  
            	realFileName = URLEncoder.encode(realFileName, "UTF-8");  
            } else {  
            	realFileName = new String(realFileName.getBytes("UTF-8"), "ISO8859-1");  
            }         
            
            // 获取文件列表 
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(remoteFile)) {
                	response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/octet-stream; charset=utf-8");
                    response.setHeader("Content-Disposition", "attachment;fileName=" + realFileName);
                    OutputStream os = response.getOutputStream();
                    ftp.retrieveFile(ff.getName(), os);
                    os.flush();
                    os.close();
                }
            }
            ftp.logout();
        } catch(IOException e) {
            e.printStackTrace();
            logger.debug("\n 方法[{}]，异常：[{}]", "FtpApche-downloadRemote", e.getMessage());
            return false ;
        } finally {
            if(ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch(IOException ioe) {
                    logger.debug( "\n 方法[{}]，异常：[{}]", "FtpApche-downloadRemote", ioe.getMessage());
                    return false ;
                }
            }
        }
        return true ;
    }

    /**
     * 避免chang文件夹失败，一层一层跳入
     * <p>Discription:[方法功能中文描述]</p>
     * Created on 2016年7月28日
     * @param path
     * @param ftp
     * @throws IOException
     * @author:[李健]
     */
    private static void changeMakeWorkingDir(String path, FTPClient ftp) throws IOException {
		String[] dirs = path.split("/");
		for (String dir : dirs) {
			dir = new String(dir.getBytes("GBK"), "iso-8859-1");
			ftp.changeWorkingDirectory(dir);
		}
	}
} 