package com.spring.fileMail.send.serviceImpl;

import java.util.Map;

import org.apache.groovy.util.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.fileMail.send.handler.MailHandler;
import com.spring.fileMail.send.service.MailService;


@Service
public class MailServiceImpl implements MailService{
	@Autowired
	private JavaMailSender sender;
	
	/*
	 * 정상작동
	public Map<String, Object> send(String email, String title, String body) {
		
		MailHandler mail;
		try {
			mail = new MailHandler(sender);
			mail.setFrom("", "MailSender");
			mail.setTo(email);
			mail.setSubject(title);
			mail.setText(body);
			mail.send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String resultCode = "S-1";
		String msg = "메일이 발송되었습니다.";
		return Maps.of("resultCode", resultCode, "msg", msg);
	}
	*/

	
public Map<String, Object> send(MultipartFile file, String email, String title, String body) {
		
		MailHandler mail;
		try {
			mail = new MailHandler(sender);
			mail.setFrom("", "MailSender");
			mail.setTo(email);
			mail.setSubject(title);
			mail.setText(body);
			mail.setFile(file);

			mail.send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String resultCode = "S-1";
		String msg = "메일이 발송되었습니다.";
		return Maps.of("resultCode", resultCode, "msg", msg);
	}
}

