package com.w.push.manager;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.huizhi.dass.common.util.RequestUtil;
import com.w.push.cache.Cache;
import com.w.push.cache.Key;
import com.w.push.entity.Device;
import com.w.push.service.DeviceService;

@Component
public class DeviceManager {

	private final static Logger LOG = LoggerFactory.getLogger(DeviceManager.class);
	
	final static Executor pool = new ThreadPoolExecutor(2, 65536 << 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	
	@Resource private DeviceService deviceService;
	
	private Cache cache = Cache.getInstance();
	
	public boolean isExist(String uui) {
		String value = cache.hget(Key.KEY_DEVICE, uui);
		if(Strings.isNullOrEmpty(value)) {
			return false;
		}
		return true;
	}
	public String createSession(HttpServletRequest request) {
		final String imei = RequestUtil.getString(request, "imei");
		String region = RequestUtil.getString(request, "region");
		String operator = RequestUtil.getString(request, "operator");
		final String uui = RequestUtil.getString(request, "uui");
		final Device device = new Device();
		device.setImei(imei);
		device.setRegion(region);
		device.setOperator(operator);
		device.setUui(uui);
		String value = JSON.toJSONString(device);
		cache.hset(Key.KEY_DEVICE, uui, value);
		pool.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					deviceService.save(device);
				} catch (Exception e) {
					LOG.error("save device error.[uui={},imei={}]", uui, imei);
				}
			}
		});
		return uui;
	}
}
