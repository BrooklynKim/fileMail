package com.spring.fileMail.send.handler;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

public class MailHandler {
	private JavaMailSender sender;
	private MimeMessage message;
	private MimeMessageHelper messageHelper;

	/*
	 * 잘되는거
	public MailHandler(JavaMailSender sender) throws MessagingException {
		this.sender = sender;
		this.message = this.sender.createMimeMessage();
		this.messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	}

	public void setFrom(String mail, String name) throws UnsupportedEncodingException, MessagingException {
		messageHelper.setFrom(mail, name);
	}

	public void setTo(String mail) throws MessagingException {
		messageHelper.setTo(mail);
	}

	public void setSubject(String subject) throws MessagingException {
		messageHelper.setSubject(subject);
	}

	public void setText(String text) throws MessagingException {
		messageHelper.setText(text, true);
	}

	public void send() {
		try {
			sender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	public MailHandler(JavaMailSender sender) throws MessagingException {
		this.sender = sender;
		this.message = this.sender.createMimeMessage();
		this.messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	}

	public void setFrom(String mail, String name) throws UnsupportedEncodingException, MessagingException {
		messageHelper.setFrom(mail, name);
	}

	public void setTo(String mail) throws MessagingException {
		messageHelper.setTo(mail);
	}

	public void setSubject(String subject) throws MessagingException {
		messageHelper.setSubject(subject);
	}

	public void setText(String text) throws MessagingException {
		messageHelper.setText(text, true);
	}

	public void setFile(MultipartFile file) throws Exception{
		FileSystemResource fileSend = new FileSystemResource(new File("C:\\Users\\brooklyn\\Desktop\\Study\\mvc구조_순서.pptx"));
		messageHelper.addAttachment("mvc구조_순서.pptx", fileSend);
	}
	
	public void send() {
		try {
			sender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
