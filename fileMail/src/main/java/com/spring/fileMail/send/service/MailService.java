package com.spring.fileMail.send.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface MailService {
	
	public Map<String,Object> selectMemberList(MultipartFile[] files,Map<String,String> param);
	
}