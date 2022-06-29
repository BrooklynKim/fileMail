package com.spring.fileMail.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
	@Autowired
	SqlSession sqlSession;
	
	public List<Map<String,Object>> selectMemberList(Map<String,String> param) throws Exception{
		return sqlSession.selectList("member.selectMemberList",param);
	}
	
	public void insetMember(Map<String, String> param) throws Exception{
	       sqlSession.insert("member.insetMember", param);
    }
	
	public int emailCheck(String email) throws Exception{
		return sqlSession.selectOne("member.emailCheck",email);
	}
	
	public void updateMember (Map<String, String> param) throws Exception {
		sqlSession.update("member.updateMember", param);
	}
	
	public void deleteMember(Map<String, Object> params) throws Exception {
        sqlSession.update("member.deleteMember", params);
    }

}
