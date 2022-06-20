package com.spring.fileMail.send.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping(value="/mail/")
public class MailController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	// mailsend.jsp
		@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	    public Map<String,Object> sendMailTest(@RequestParam(required = false) MultipartFile fileUpForm,@RequestParam Map<String, String> param, MultipartHttpServletRequest request) throws Exception{
	        
			Map<String, Object> respMap = new HashMap<String, Object>();
			
			File path = new File(request.getServletContext().getRealPath("/C:\\Users\\brooklyn\\Desktop\\Study\\fileMail"),"random");
			
			String from = param.get("fromEmail");
			String to = param.get("toEmail");
			String subject = param.get("subject");
	        String content = param.get("content");
	        String attachFile = param.get("fileUpForm");
	        
	        String fileNm = attachFile.substring(0,3);
	        System.out.println(fileNm);
	        
	        
	       
	        MultipartFile mailFile = fileUpForm;
	        
	        try {
	            MimeMessage mail = mailSender.createMimeMessage();
	            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
	           
	            mailHelper.setFrom(from);
	            mailHelper.setTo(to);
	            mailHelper.setSubject(subject);
	            mailHelper.setText(content, true);
	           
	            FileSystemResource file = new FileSystemResource(new File("C:\\Users\\brooklyn\\Desktop\\Study\\testFile.txt")); 
	            mailHelper.addAttachment("testFile.txt", file);
	            
	            mailSender.send(mail);
	            respMap.put("state","OK");
	        } catch(Exception e) {
	        	respMap.put("state","False");
	            e.printStackTrace();
	        }
	        
	        return respMap;
		}
	
	/*
	// mailsend.jsp
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    public Map<String,Object> sendMailTest(@RequestParam(required = false) MultipartFile fileUpForm,@RequestParam Map<String, String> param, MultipartHttpServletRequest request) throws Exception{
        
		Map<String, Object> respMap = new HashMap<String, Object>();
		
		File path = new File(request.getServletContext().getRealPath("/C:\\Users\\brooklyn\\Desktop\\Study\\fileMail"),"random");
		
		String from = param.get("fromEmail");
		String to = param.get("toEmail");
		String subject = param.get("subject");
        String content = param.get("content");
       
        MultipartFile mailFile = fileUpForm;
        
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
           
            mailHelper.setFrom(from);
            mailHelper.setTo(to);
            mailHelper.setSubject(subject);
            mailHelper.setText(content, true);
           
            FileSystemResource file = new FileSystemResource(new File("C:\\Users\\brooklyn\\Desktop\\Study\\testFile.txt")); 
            mailHelper.addAttachment("testFile.txt", file);
            
            mailSender.send(mail);
            respMap.put("state","OK");
        } catch(Exception e) {
        	respMap.put("state","False");
            e.printStackTrace();
        }
        
        return respMap;
	}
	
	*/
}
