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
		
		int fileFlag = 0;
		
		 try {
			//String to = mailDao.selectMemberList(param.get("toEmail"));
			List<Map<String,Object>> memberList = mailDao.selectMemberList(param);
	
			String from = param.get("fromEmail");
			String subject = param.get("subject");
			String content = param.get("content");
			
			for(int i=0; i<memberList.size(); i++) {
				String to = memberList.get(i).get("A_EMAIL").toString();
				String name = memberList.get(i).get("A_NAME").toString();
				String num = memberList.get(i).get("A_NUM").toString();
				
	    		for(MultipartFile MultiFile : files) {
	    			//MultipartFile[] mailFile = files;
	    	        param.put("oriFileNm",MultiFile.getOriginalFilename());
	    	        String oriFileNm = param.get("oriFileNm");
	    	        
	    	        //사번
	     	        int num_beginIndex = oriFileNm.lastIndexOf("-")+1;
	     	        int num_endIndex = oriFileNm.lastIndexOf("_");
	     	        
	    	        // 이름
	    	        int beginIndex = oriFileNm.lastIndexOf("_")+1;
	     	        int endIndex = oriFileNm.lastIndexOf(".");

	     	        String result_name = oriFileNm.substring(beginIndex, endIndex);
	     	        String result_num = oriFileNm.substring(num_beginIndex, num_endIndex);
	     	        
	     	        String totalResult = new String(result_num+"_"+result_name);
	     	        // A0000_홍길동
	     	        String fileNm = new String(num+"_"+name);
	     	        
	     	        MimeMessage mail = mailSender.createMimeMessage();
		            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
		            
		            mailHelper.setFrom(from);
		            mailHelper.setTo(to);
		            mailHelper.setSubject(subject);
		            mailHelper.setText(content, true);
		            
		            FileSystemResource file = new FileSystemResource(new File("C:\\Users\\brooklyn\\Desktop\\Study\\fileMail\\"+oriFileNm.toString()));
		            
		            if(totalResult.equals(fileNm)) { 
		            	mailHelper.addAttachment(oriFileNm.toString(), file);
		            	mailSender.send(mail);
		            	fileFlag++;
			       }
	    		}       
	    		
			}
			
			
			if(fileFlag == files.length) {
				respMap.put("state","OK");//state test code
			}else {
				respMap.put("state","False");
			}
				
        } catch(Exception e) {
        	respMap.put("state","False");
            e.printStackTrace();
        }
		return respMap;
	}
	
	
}

