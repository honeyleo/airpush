package com.w.push.web.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huizhi.dass.common.util.RequestUtil;

@Controller
@RequestMapping("/test")
public class Test {

	private HttpServletResponse response;
	
	@RequestMapping("/poll")
	public void poll(HttpServletRequest request, HttpServletResponse response) {
		String msg = RequestUtil.getString(request, "msg");
		try {
			this.response.getWriter().write(msg);
			this.response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/push")
	public void push(HttpServletRequest request, HttpServletResponse response) {
		
		this.response = response;
		
	}
}
