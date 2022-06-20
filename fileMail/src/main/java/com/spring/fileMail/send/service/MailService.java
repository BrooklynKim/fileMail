package com.spring.fileMail.send.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface MailService {
	public Map<String, Object> send(MultipartFile file,String email, String title, String body);
}

