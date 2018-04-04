package com.camelot.email;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * <p>Description: [韩世雄提供的邮件发送类]</p>
 * Created on 2017年3月15日
 * @author  <a href="mailto: zuorenzhi@camelotchina.com">左仁智</a>
 * @version 1.0 
 * Copyright (c) 2017 北京柯莱特科技有限公司 交付部
 */
@SuppressWarnings("restriction")
public class EmailBeanHan {

	private static final String defaultMimetype = "text/plain";

	public static void main(String[] args) {
		Properties props = new Properties();// 邮件服务其配置
		props.put("mail.transport.protocol", "smtp");// 协议
		props.put("mail.smtp.host", "smtp.163.com");// smtp服务器地址
		props.put("mail.smtp.auth", "true");// 需要校验

		final String account = "q317859237@163.com";// 账号
		final String pwd = "zuorz510722";// 密码
		String mimetype = "text/html";// 消息类型
		File[] attachments = null;// 附件
		String[] receivers = { "1071799791@qq.com","q317859237@163.com","drinking.tears@qq.com" };// 收件人
		String charset = "UTF-8";// 设置编码
		String mailContent = "正文123";// 正文
		String subject = "发送主题";// 主题

		try {
			// 发送邮件
			sendEmail(account, pwd, receivers, mimetype, charset, attachments, props, mailContent, subject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void sendEmail(final String account, final String pwd,
			String[] receivers, String mimetype, String charset,
			File[] attachements, Properties props, String mailContent,
			String subject) throws Exception {
		
		Session session = Session.getDefaultInstance(props,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(account, pwd);// 登录用户名/密码
					}
				});
		session.setDebug(true);// 需要校验

		MimeMessage mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress(account));// 发件人邮件

		InternetAddress[] toAddress = new InternetAddress[receivers.length];
		for (int i = 0; i < receivers.length; i++) {
			toAddress[i] = new InternetAddress(receivers[i]);
		}
		mimeMessage.setRecipients(Message.RecipientType.TO, toAddress);// 收件人邮件
		mimeMessage.setSubject(subject, charset);

		Multipart multipart = new MimeMultipart();
		// 正文
		MimeBodyPart body = new MimeBodyPart();
		String header = (mimetype != null && !"".equals(mimetype) ? mimetype : defaultMimetype) + ";charset=" + charset;
		body.setContent(mailContent, header);
		multipart.addBodyPart(body);// 发件内容

		// 附件
		if (attachements != null) {
			for (File attachement : attachements) {
				MimeBodyPart attache = new MimeBodyPart();
				attache.setDataHandler(new DataHandler(new FileDataSource(attachement)));
				String fileName = getLastName(attachement.getName());
				attache.setFileName(MimeUtility.encodeText(fileName, charset,null));
				multipart.addBodyPart(attache);
			}
		}

		mimeMessage.setContent(multipart);
		mimeMessage.setSentDate(new Date());// formcat.parse("2010-5-23")
		Transport.send(mimeMessage);
	}

	private static String getLastName(String fileName) {
		int pos = fileName.lastIndexOf("\\");
		if (pos > -1) {
			fileName = fileName.substring(pos + 1);
		}
		pos = fileName.lastIndexOf("/");
		if (pos > -1) {
			fileName = fileName.substring(pos + 1);
		}
		return fileName;
	}
}
