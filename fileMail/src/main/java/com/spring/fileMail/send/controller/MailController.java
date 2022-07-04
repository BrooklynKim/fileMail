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
		//request.setCharacterEncoding("UTF-8");
		return MailService.selectMemberList(files, param);
	}
	
}