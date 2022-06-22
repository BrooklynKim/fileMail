package com.spring.fileMail.send.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

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
		@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	    public Map<String,Object> sendMailTest(@RequestParam(value="files") MultipartFile[] files,@RequestParam Map<String, String> param, MultipartHttpServletRequest request) throws Exception{
	        
			Map<String, Object> respMap = new HashMap<String, Object>();
			
			String from = param.get("fromEmail");
			String to = param.get("toEmail");
			String subject = param.get("subject");
	        String content = param.get("content");
	    try {
	    	/*
	        // 새로추가
	        for(MultipartFile file : files) {
	        	if(!file.getOriginalFilename().isEmpty()) {
	        		BufferedOutputStream bos = new BufferedOutputStream(
	        				new FileOutputStream(
	        						new File(file.getOriginalFilename())));
	        		MultipartFile[] mailFile = files;
	        		String oriFileNm = param.get("oriFileNm");
	        		
	        		int beginIndex = oriFileNm.lastIndexOf("_")+1;
	     	        int endIndex = oriFileNm.lastIndexOf(".");
	     	        String result = oriFileNm.substring(beginIndex, endIndex);
	        		
	     	        MimeMessage mail = mailSender.createMimeMessage();
		            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
	     	        
		            mailHelper.setFrom(from);
		            mailHelper.setTo(to);
		            mailHelper.setSubject(subject);
		            mailHelper.setText(content, true);
		            
		            FileSystemResource fsr = new FileSystemResource(new File("C:\\Users\\brooklyn\\Desktop\\Study\\fileMail\\"+oriFileNm.toString()));
		            for(int i=0; i<to.length(); i++) {
		            	 if(oriFileNm.toString().equals(result)) { 
		 	            	mailHelper.addAttachment(oriFileNm.toString(), fsr);
		 	            	mailSender.send(mail);
		 	            }else {
		 	            	respMap.put("state","False");
		 	            }
		 	            respMap.put("state","OK");
		            }
	     	        bos.write(file.getBytes());
	        		bos.flush();
	        		bos.close();
		        	}
		        }
		        */
	    		for(MultipartFile MultiFile : files) {
	    			MultipartFile[] mailFile = files;
	    	        param.put("oriFileNm",MultiFile.getOriginalFilename());
	    	        String oriFileNm = param.get("oriFileNm");
	    	        int beginIndex = oriFileNm.lastIndexOf("_")+1;
	     	        int endIndex = oriFileNm.lastIndexOf(".");
	     	        String result = oriFileNm.substring(beginIndex, endIndex);
	     	        
	     	        MimeMessage mail = mailSender.createMimeMessage();
		            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
		            
		            mailHelper.setFrom(from);
		            mailHelper.setTo(to);
		            mailHelper.setSubject(subject);
		            mailHelper.setText(content, true);
		            
		            FileSystemResource file = new FileSystemResource(new File("C:\\Users\\brooklyn\\Desktop\\Study\\fileMail\\"+oriFileNm.toString()));
		            
		            if("홍길동".equals(result)) { 
		            	mailHelper.addAttachment(oriFileNm.toString(), file);
		            	mailSender.send(mail);
		            }else {
		            	respMap.put("state","False");
		            }
		            //mailSender.send(mail);
		            respMap.put("state","OK");
	    		}
	        } catch(Exception e) {
	        	respMap.put("state","False");
	            e.printStackTrace();
	        }
	        return respMap;
		}
	
	
/*
	@Autowired
	private JavaMailSender mailSender;
		@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	    public Map<String,Object> sendMailTest(@RequestParam(value="fileUpForm", required = false) MultipartFile fileUpForm,@RequestParam Map<String, String> param, MultipartHttpServletRequest request) throws Exception{
	        
			Map<String, Object> respMap = new HashMap<String, Object>();
			
			String from = param.get("fromEmail");
			String to = param.get("toEmail");
			String subject = param.get("subject");
	        String content = param.get("content");
	        
	        MultipartFile mailFile = fileUpForm;
	        param.put("oriFileNm",mailFile.getOriginalFilename());
	        String oriFileNm = param.get("oriFileNm");
	        
	        //규칙 사이 문자열 추출
	        int beginIndex = oriFileNm.lastIndexOf("_")+1;
	        int endIndex = oriFileNm.lastIndexOf(".");
	        String result = oriFileNm.substring(beginIndex, endIndex);
	        
	       
	        try {
	            MimeMessage mail = mailSender.createMimeMessage();
	            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
	            
	            mailHelper.setFrom(from);
	            mailHelper.setTo(to);
	            mailHelper.setSubject(subject);
	            mailHelper.setText(content, true);
	            
	            //FileSystemResource file = new FileSystemResource(new File("C:\\Users\\brooklyn\\Desktop\\Study\\testFile.txt"));
	            FileSystemResource file = new FileSystemResource(new File("C:\\Users\\brooklyn\\Desktop\\Study\\fileMail\\"+oriFileNm.toString())); 
	            //mailHelper.addAttachment(oriFileNm.toString(), file);
	            
	            if("홍길동".equals(result)) { 
	            	mailHelper.addAttachment(oriFileNm.toString(), file);
	            	mailSender.send(mail);
	            }else {
	            	respMap.put("state","False");
	            }
	            //mailSender.send(mail);
	            respMap.put("state","OK");
	        } catch(Exception e) {
	        	respMap.put("state","False");
	            e.printStackTrace();
	        }
	        return respMap;
		}
*/
}
