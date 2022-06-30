package com.spring.fileMail.send.controller;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.fileMail.send.serviceImpl.MailServiceImpl;

import groovyjarjarpicocli.CommandLine.Model;

@Controller
@RequestMapping(value="/mail/")
public class MailController {

	@Resource
	MailServiceImpl MailService;
	
	@RequestMapping(value = "/mailSendPage", method = RequestMethod.GET) 
    public String mailSendPage(Locale locale, Model model,HttpServletRequest req, HttpServletResponse res) throws Exception { 
		return "/fileMail/mailsend"; // jsp 실제 경로
    }

	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> sendMailTest(@RequestParam(value="files") MultipartFile[] files,@RequestParam Map<String, String> param, MultipartHttpServletRequest request) throws Exception{
		return MailService.selectMemberList(files, param);
	}
	
	
	
	/*
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
	    	
	    		for(MultipartFile MultiFile : files) {
	    			MultipartFile[] mailFile = files;
	    	        param.put("oriFileNm",MultiFile.getOriginalFilename());
	    	        String oriFileNm = param.get("oriFileNm");
	    	        int beginIndex = oriFileNm.lastIndexOf("_")+1;
	     	        int endIndex = oriFileNm.lastIndexOf(".");
	     	        String result = oriFileNm.substring(beginIndex, endIndex);
	     	        
	     	        MimeMessage mail = mailSender.createMimeMessage();
		            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"charset=UTF-8");
		            
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
	*/
}
