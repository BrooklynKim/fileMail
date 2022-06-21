package com.spring.fileMail.send.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.MimeMessage;

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
			
			String from = param.get("fromEmail");
			String to = param.get("toEmail");
			String subject = param.get("subject");
	        String content = param.get("content");
	        
	        MultipartFile mailFile = fileUpForm;
	        String oriFileNm = param.put("oriFileNm",mailFile.getOriginalFilename());
	        
	        //파일처리
	        //String attachFile = param.get("fileUpForm");
	        String convertNm = new String(oriFileNm.getBytes("8859_1"),"UTF-8");//한글처리
	        //File path = new File(request.getServletContext().getRealPath("/C:\\Users\\brooklyn\\Desktop\\Study\\fileMail"),"random");
	        /*
	        Pattern pattern = Pattern.compile("_(.*?).");
	        Matcher matcher = pattern.matcher(convertNm);
	        if(matcher.find()) {
	        	System.out.println(matcher.group(1));
	        }
	        */
	        try {
	            MimeMessage mail = mailSender.createMimeMessage();
	            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
	            
	            mailHelper.setFrom(from);
	            mailHelper.setTo(to);
	            mailHelper.setSubject(subject);
	            mailHelper.setText(content, true);
	            
	            //FileSystemResource file = new FileSystemResource(new File("C:\\Users\\brooklyn\\Desktop\\Study\\testFile.txt"));
	            FileSystemResource file = new FileSystemResource(new File("C:\\Users\\brooklyn\\Desktop\\Study\\"));
	            mailHelper.addAttachment("testFile.txt", file);
	            
	            if(convertNm == "홍길동") {
	            	mailHelper.addAttachment(convertNm, file);
	            }
	            
	            
	            mailSender.send(mail);
	            respMap.put("state","OK");
	        } catch(Exception e) {
	        	respMap.put("state","False");
	            e.printStackTrace();
	        }
	        
	        return respMap;
		}
}
