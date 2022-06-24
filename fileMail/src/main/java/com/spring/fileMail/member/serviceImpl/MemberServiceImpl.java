package com.spring.fileMail.member.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.fileMail.member.dao.MemberDao;
import com.spring.fileMail.member.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberDao memberDao;
	
	@Override
	public Map<String, Object> selectMemberList(Map<String, String> param) {
		Map<String, Object> respMap = new HashMap<String, Object>();
		try {
			List<Map<String,Object>> mList = memberDao.selectMemberList(param);
			respMap.put("list", mList);
			respMap.put("state", "OK");
		} catch (Exception e) {
			respMap.put("state", "False");
			e.printStackTrace();
		}
		return respMap;
	}

}
