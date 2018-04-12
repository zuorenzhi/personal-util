package camelot.email;

import java.io.File;
import java.util.Properties;

import jodd.mail.Email;
import jodd.mail.EmailAttachment;
import jodd.mail.EmailAttachmentBuilder;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;

import camelot.VerifyUtils;


/**
 * <p>Description: [发送邮件工具类]</p>
 * Created on 2016年3月15日
 * @author <a href="mailto: liuxiangping86@clt.com">刘香平</a>
 * @version 1.0 Copyright (c) 2016 北京柯莱特科技有限公司 交付部
 */
public class EmailBeanLiu {
	
	/** 邮件实体 */
	private EmailBean emailBean;
	
	/** 发送邮件Session */
	private SendMailSession session;
	
	/**
	 * <p>Discription:[发送邮件构造方法]</p>
	 * @coustructor 方法.
	 */
	public EmailBeanLiu(EmailBean emailBean){
		
		@SuppressWarnings("rawtypes")
		SmtpServer smtpServer = null;
		if(emailBean.getSmtpPort() > 0){
			smtpServer = SmtpServer.create(emailBean.getSmtpHost(), emailBean.getSmtpPort());
		}else{
			smtpServer = SmtpServer.create(emailBean.getSmtpHost());
		}
		smtpServer.authenticateWith(emailBean.getSmtpUser(), emailBean.getSmtpPass());
        session = smtpServer.createSession();
        session.open();
        this.emailBean = emailBean;
	}
	
	/**
	 * <p>Discription:[发送邮件构造方法]</p>
	 * @coustructor 方法.
	 */
	public EmailBeanLiu(Properties properties){
		
		this.emailBean = this.resolveProperties(properties);
		@SuppressWarnings("rawtypes")
		SmtpServer smtpServer = null;
		if(emailBean.getSmtpPort() > 0){
			smtpServer = SmtpServer.create(emailBean.getSmtpHost(), emailBean.getSmtpPort());
		}else{
			smtpServer = SmtpServer.create(emailBean.getSmtpHost());
		}
		smtpServer.authenticateWith(emailBean.getSmtpUser(), emailBean.getSmtpPass());
        session = smtpServer.createSession();
        session.open();
	}
	
	/**
	 * <p>Discription:[发送邮件]</p>
	 * Created on 2016年3月16日
	 * @param from 发件人
	 * @param to 收件人
	 * @param subject 主题
	 * @param mailContent 邮件内容
	 * @return 发送电子邮件的消息ID
	 * @author:[刘香平]
	 */
	public String send(String to, String subject, String mailContent){
		Email email = Email.create().to(to).subject(subject);
		email.from(emailBean.getPersonal(), emailBean.getEmail());
		email.addText(mailContent);
		return this.send(email);
	}
	
	/**
	 * <p>Discription:[发送邮件]</p>
	 * Created on 2016年3月16日
	 * @param from 发件人
	 * @param to 收件人
	 * @param subject 主题
	 * @param mailContent 邮件内容
	 * @return 发送电子邮件的消息ID
	 * @author:[刘香平]
	 */
	public String send(String to, String subject, String mailContent, String mimetype){
		Email email = Email.create().to(to).subject(subject);
		email.from(emailBean.getPersonal(), emailBean.getEmail());
		this.setContent(email, mimetype, mailContent);
		return this.send(email);
	}
	
	/**
	 * <p>Discription:[发送邮件]</p>
	 * Created on 2016年3月16日
	 * @param from 发件人
	 * @param to 收件人
	 * @param subject 主题
	 * @param mailContent 邮件内容
	 * @param appendixs 附件
	 * @return 发送电子邮件的消息ID
	 * @author:[刘香平]
	 */
	public String send(String to, String subject, String mailContent, File[] appendixs){
		
		Email email = Email.create().to(to).subject(subject);
		email.addText(mailContent);
		this.setAppendixs(email, appendixs);
		return this.send(email);
	}
	
	/**
	 * <p>Discription:[发送邮件]</p>
	 * Created on 2016年3月16日
	 * @param from 发件人
	 * @param to 收件人
	 * @param subject 主题
	 * @param mailContent 邮件内容
	 * @param appendixs 附件
	 * @param mimetype 邮件类型：text/plain、text/html
	 * @return 发送电子邮件的消息ID
	 * @author:[刘香平]
	 */
	public String send(String to, String subject, String mailContent, File[] appendixs, String mimetype){
		
		Email email = Email.create().to(to).subject(subject);
		this.setContent(email, mimetype, mailContent);
		this.setAppendixs(email, appendixs);
		return this.send(email);
	}
	
	/**
	 * <p>Discription:[发送邮件]</p>
	 * Created on 2016年3月16日
	 * @param from 发件人
	 * @param to 收件人集合
	 * @param subject 主题
	 * @param mailContent 邮件内容
	 * @return 发送电子邮件的消息ID
	 * @author:[刘香平]
	 */
	public String send(String[] to, String subject, String mailContent){
		Email email = Email.create().to(to).subject(subject);
		email.addText(mailContent);
		return this.send(email);
	}

	/**
	 * <p>Discription:[发送邮件]</p>
	 * Created on 2016年3月16日
	 * @param from 发件人
	 * @param to 收件人集合
	 * @param subject 主题
	 * @param mailContent 邮件内容
	 * @param appendixs 附件
	 * @param mimetype 邮件类型：text/plain、text/html
	 * @return 发送电子邮件的消息ID
	 * @author:[刘香平]
	 */
	public String send(String[] to, String subject, String mailContent, File[] appendixs, String mimetype){
		
		Email email = Email.create().to(to).subject(subject);
		this.setContent(email, mimetype, mailContent);
		this.setAppendixs(email, appendixs);
		return this.send(email);
	}
	
	/**
	 * <p>Discription:[设置邮件内容]</p>
	 * Created on 2016年3月16日
	 * @param email 邮件
	 * @param mimetype 邮件内容类型：text/plain、text/html
	 * @param mailContent 邮件内容
	 * @author:[刘香平]
	 */
	private void setContent(Email email, String mimetype, String mailContent){
		if ("text/plain".equals(mimetype) || VerifyUtils.isEmpty(mimetype)) {
			email.addText(mailContent);
		} else if ("text/html".equals(mimetype)) {
			email.addHtml(mailContent);
		}
	}
	
	/**
	 * <p>Discription:[添加附件]</p>
	 * Created on 2016年3月16日
	 * @param email 邮件实体
	 * @param appendixs 附件集合
	 * @author:[刘香平]
	 */
	private void setAppendixs(Email email, File[] appendixs){
		if(appendixs != null && appendixs.length > 0){
			EmailAttachmentBuilder attachmentBuilder = EmailAttachment.attachment();
			for(File appendix:appendixs){				
				attachmentBuilder.file(appendix);
				email.attach(attachmentBuilder);
			}
		}
	}
	
	/**
	 * <p>Discription:[解析Properties配置参数]</p>
	 * Created on 2016年3月16日
	 * @param properties
	 * @return 返回邮件实体
	 * @author:[刘香平]
	 */
	private EmailBean resolveProperties(Properties properties){
		
		EmailBean emailBean = new EmailBean();
		emailBean.setSmtpHost(properties.getProperty("email.host"));
		String port = properties.getProperty("email.port");
		if(VerifyUtils.isNotEmpty(port) && Integer.valueOf(port) > 0){			
			emailBean.setSmtpPort(Integer.valueOf(port));
		}
		emailBean.setSmtpUser(properties.getProperty("email.user"));
		emailBean.setSmtpPass(properties.getProperty("email.pass"));
		emailBean.setEmail(properties.getProperty("email.email"));
		emailBean.setPersonal(properties.getProperty("email.personal"));
		return emailBean;
	}
	
	/**
	 * <p>Discription:[发送邮件]</p>
	 * Created on 2016年3月16日
	 * @param email 邮件实体
	 * @return 发送电子邮件的消息ID
	 * @author:[刘香平]
	 */
	private String send(Email email){
		String result = session.sendMail(email);
        return result;
	}

	/**
	 * <p>Discription:[关闭邮件连接]</p>
	 * Created on 2016年3月16日
	 * @author:[刘香平]
	 */
	public void close(){
		session.close();
	}
	
	public static void main(String[] args) {
//		testLiuxp();
//		testZuorz();
		testZuorz_163_good();
	}

	private static void testLiuxp() {
		EmailBean emailBean = new EmailBean();
		emailBean.setEmail("liuxiangping86@clt.com");
		emailBean.setPersonal("刘香平");
		emailBean.setSmtpHost("smtp.camelotchina.com");
		emailBean.setSmtpPass("liu%1934853513");
		emailBean.setSmtpUser("liuxiangping86@clt.com");
		EmailBeanLiu sendEmailLiu = new EmailBeanLiu(emailBean);
		sendEmailLiu.send("418028499@qq.com", "test", "邮件工具类测试");
	}
	private static void testZuorz() {
		EmailBean emailBean = new EmailBean();
		emailBean.setEmail("zuorenzhi@clt.com");
		emailBean.setPersonal("左仁智");
		emailBean.setSmtpHost("smtp.camelotchina.com");
		emailBean.setSmtpPass("zuo19891018");
		emailBean.setSmtpUser("zuorenzhi@clt.com");
		EmailBeanLiu sendEmailLiu = new EmailBeanLiu(emailBean);
		sendEmailLiu.send("317859237@qq.com", "test", "邮件工具类测试");
	}
	/**
	 * <p>Discription:[163测试--好使]</p>
	 * Created on 2017年3月15日
	 * @author:[左仁智]
	 */
	private static void testZuorz_163_good() {
		EmailBean emailBean = new EmailBean();
		emailBean.setEmail("q317859237@163.com");
		emailBean.setSmtpPass("zuorz510722");
		emailBean.setSmtpUser("q317859237@163.com");
		emailBean.setPersonal("zrz");
		emailBean.setSmtpHost("smtp.163.com");
		EmailBeanLiu sendEmailLiu = new EmailBeanLiu(emailBean);
//		sendEmailLiu.send("317859237@qq.com", "file2Bean", "邮件工具类测试");
		sendEmailLiu.send("q317859237@126.com", "test", "邮件工具类测试");
	}
}