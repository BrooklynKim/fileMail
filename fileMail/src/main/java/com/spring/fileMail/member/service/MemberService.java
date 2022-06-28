package com.spring.fileMail.member.service;

import java.util.Map;

public interface MemberService {
	
	public Map<String, Object> selectMemberList(Map<String, String> param);
	public Map<String, Object> insertMember(Map<String, String> param);
	public Map<String, Object> updateMember(Map<String, String> param); 
}
