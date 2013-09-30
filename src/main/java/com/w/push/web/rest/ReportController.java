package com.w.push.web.rest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.huizhi.dass.common.util.RequestUtil;
import com.twmacinta.util.MD5;
import com.w.core.exception.AppException;
import com.w.core.model.Message;
import com.w.push.manager.DeviceManager;
import com.w.push.manager.PushManager;
import com.w.push.quartz.init.DataInit;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Resource private PushManager pushManager;
	@Resource private DeviceManager deviceManager;
	@Resource private DataInit dataInit;
	
	private boolean isTest = true;
	
	@RequestMapping("/poll")
	@ResponseBody
	public Message poll(HttpServletRequest request, 
			HttpServletResponse response) throws AppException {
		
		String uui = RequestUtil.getString(request, "uui");
		String qn = RequestUtil.getString(request, "qn");
		long cid = RequestUtil.getLong(request, "cid");
		String sign = RequestUtil.getString(request, "s");
		
		if(Strings.isNullOrEmpty(sign)) {
			Message.Builder builder = Message.Builder.newBuilder();
			builder.put("c", "4");
			return builder.build();
		}
		
		if(!isTest) {
			MD5 md5 = new MD5();
			StringBuilder sb = new StringBuilder();
			sb.append("uui="+uui).append("&cid=").append(cid).append("&qn=").append(qn).append("&").append(uui);
			md5.Update(sb.toString());
			if(!sign.equalsIgnoreCase(md5.asHex())) {
				Message.Builder builder = Message.Builder.newBuilder();
				builder.put("c", "4");
				return builder.build();
			}
		}
		
		if(!deviceManager.isExist(uui)) {
			uui = deviceManager.createSession();
			Message.Builder builder = Message.Builder.newBuilder();
			builder.put("c", "1");
			builder.put("uui", uui);
			return builder.build();
		}
		Message message = pushManager.getPushContent(uui, qn, cid);
		return message;
	}
	
	@RequestMapping("/hit")
	@ResponseBody
	public Message hit(HttpServletRequest request, 
			HttpServletResponse response) throws AppException {
		String uui = RequestUtil.getString(request, "uui");
		String qn = RequestUtil.getString(request, "qn");
		long cid = RequestUtil.getLong(request, "cid");
		Message message = pushManager.hit(uui, qn, cid);
		return message;
	}
	
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
