package com.utils.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Description: 文件传输 <br/>
 * Created on: 2016/11/23 15:16 <br/>
 *
 * @author: <a href="mailto: liruifeng@clt.com">李瑞丰</a><br/>
 * @version: 1.0 <br/>
 * Copyright (c) 2016年 北京柯莱特科技有限公司 交付部
 */
public class FileTransferUtils {

    /**
     * Discription: 文件传输方法
     * Created on: 2016/11/23 15:20
     *
     * @param: file 被传输的文件 transDir 传输路径
     * @return: transDir 传输路径
     * @author: <a href="mailto: liruifeng@clt.com">李瑞丰</a>
     */
    public static String transferTo(MultipartFile file, String transDir) throws IOException {
        // 构建文件
        File newFile = new File(transDir);
        // 如果文件夹不存在创建文件夹
        if (!newFile.getParentFile().exists()) {
            newFile.mkdirs();
        }
        // 创建文件
        file.transferTo(newFile);
        return transDir;
    }
}
