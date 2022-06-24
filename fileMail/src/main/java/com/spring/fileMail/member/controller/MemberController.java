package com.spring.fileMail.member.controller;

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

import com.spring.fileMail.member.serviceImpl.MemberServiceImpl;

import groovyjarjarpicocli.CommandLine.Model;

@Controller
@RequestMapping(value="/member/")
public class MemberController {
	
	@Resource
	MemberServiceImpl memberService;
	
	@RequestMapping(value = "/memberListPage", method = RequestMethod.GET) 
    public String userMng(Locale locale, Model model,HttpServletRequest req, HttpServletResponse res) throws Exception { 
		return "/memberList/memberList"; // jsp 실제 경로
    }
	
	@RequestMapping("/memberList")
	@ResponseBody
	public Map<String , Object> memberList (@RequestParam Map<String, String> param) throws Exception {
		return memberService.selectMemberList(param);
	}

}
