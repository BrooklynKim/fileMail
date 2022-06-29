package com.spring.fileMail.send.serviceImpl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.fileMail.send.dao.MailDao;
import com.spring.fileMail.send.service.MailService;


@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	MailDao mailDao;
	
	@Override
	public Map<String,Object> selectMemberList(MultipartFile[] files,Map<String,String> param) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		
		 try {
			//String to = mailDao.selectMemberList(param.get("toEmail"));
			List<Map<String,Object>> memberList = mailDao.selectMemberList(param);
	
			String from = param.get("fromEmail");
			String subject = param.get("subject");
			String content = param.get("content");
			
			for(int i=0; i<memberList.size(); i++) {
				String to = memberList.get(i).get("A_EMAIL").toString();
				String name = memberList.get(i).get("A_NAME").toString();
			
	    		for(MultipartFile MultiFile : files) {
	    			//MultipartFile[] mailFile = files;
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
		            
			            if(name.equals(result)) { 
			            	mailHelper.addAttachment(oriFileNm.toString(), file);
			            	mailSender.send(mail);
			            	//respMap.put("state","OK");
				       }else {
				           	respMap.put("state","False");
				       }
	    		}       
	    		respMap.put("state","OK");//state test code
			}
        } catch(Exception e) {
        	respMap.put("state","False");
            e.printStackTrace();
        }
		return respMap;
	}
	
	
	/*
	// 정상작동 1
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	MailDao mailDao;
	
	@Override
	public Map<String,Object> selectMemberList(MultipartFile[] files,Map<String,String> param) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		
		String from = param.get("fromEmail");
		String to = param.get("toEmail");
		String subject = param.get("subject");
		String content = param.get("content");
    try {
    	
    		for(MultipartFile MultiFile : files) {
    			//MultipartFile[] mailFile = files;
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
	            	respMap.put("state","OK");
	            }else {
	            	respMap.put("state","False");
	            }
	            //mailSender.send(mail);
    		}
        } catch(Exception e) {
        	respMap.put("state","False");
            e.printStackTrace();
        }
		return respMap;
	}
	
	
	 // 정상작동 2
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

}

