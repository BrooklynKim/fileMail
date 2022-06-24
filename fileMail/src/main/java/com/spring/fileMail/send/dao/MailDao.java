package com.spring.fileMail.send.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MailDao {

	@Autowired
	SqlSession sqlSession;
	
	public List<Map<String,Object>> selectMemberList(Map<String,String> param) throws Exception{
		return sqlSession.selectList("mail.selectMemberList",param);
	}
	
}
