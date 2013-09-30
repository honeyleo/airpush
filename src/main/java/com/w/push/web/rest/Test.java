package com.w.push.web.rest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.w.core.exception.AppException;
import com.w.core.model.Message;
import com.w.push.quartz.init.DataInit;

@Controller
@RequestMapping("/test")
public class Test {

	@Resource private DataInit dataInit;
	
	@RequestMapping("/dataInit")
	@ResponseBody
	public Message dataInit(HttpServletRequest request, 
			HttpServletResponse response) throws AppException {
		Message.Builder builder = Message.Builder.newBuilder();
		dataInit.init();
		builder.put("c", 0);
		return builder.build();
	}
}
