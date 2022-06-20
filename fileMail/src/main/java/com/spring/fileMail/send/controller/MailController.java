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
	/*
	@Autowired
	private MailService mailService;
	
	@RequestMapping("/send")
	public String showSend() {
		return "fileMail/mailsend";
	}
	
	 * 잘되는거
	@RequestMapping("/doSend")
	@ResponseBody
	public String doSend(String email, String title, String body) {
		Map<String,Object> sendRs = mailService.send(email,title,body);
		return (String) sendRs.get("msg");
	}
	
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
    public void sendMailTest() throws Exception{
        
        String subject = "test 메일";
        String content = "이메일 전송 Test 입니다.";
        String from = "mailsender114@gmail.com";
        String to = "iphone@kakao.com";
        
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
            
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
	*/
	// mailsend.jsp
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    public Map<String,Object> sendMailTest(@RequestParam(required = false) MultipartFile fileUpForm,@RequestParam Map<String, String> param, MultipartHttpServletRequest request) throws Exception{
        
		Map<String, Object> respMap = new HashMap<String, Object>();
		
		File path = new File(request.getServletContext().getRealPath("/C:\\Users\\brooklyn\\Desktop\\Study\\fileMail"),"random");
		
        String subject = param.get("subject");
        String content = param.get("content");
        String from = param.get("fromEmail");
        String to = param.get("toEmail");
       
        
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
	// mailSending 코드
		@RequestMapping(value = "mailSending.do")
		public String mailSending(HttpServletRequest request, MultipartHttpServletRequest multi) {


			String setfrom = "mailsender114@gmail.com";
			String tomail = request.getParameter("tomail"); 
			String title = request.getParameter("title"); 
			String content = request.getParameter("content");
	       

			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

				messageHelper.setFrom(setfrom); 
				messageHelper.setTo(tomail); 
				messageHelper.setSubject(title);
				messageHelper.setText(content); 

				mailSender.send(message);
			} catch (Exception e) {
				System.out.println(e);
			}

			//return "redirect:/main.do";
			return "/mailsend";
		}
	*/
}
