package com.spring.fileMail.common.res;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ReqRespnObj {
	@SuppressWarnings("unchecked")
	public Map<String, Object> convertJsonParam(String jsonString) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> params = mapper.readValue(jsonString, Map.class);
		return params;
	}	
}
