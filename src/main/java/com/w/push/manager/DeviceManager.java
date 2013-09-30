package com.w.push.manager;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.w.push.cache.Cache;

@Component
public class DeviceManager {

	private Cache cache = Cache.getInstance();
	
	public boolean isExist(String uui) {
		String value = cache.get(uui);
		if(Strings.isNullOrEmpty(value)) {
			return false;
		}
		return true;
	}
	public String createSession() {
		return com.w.utils.UUID.uuid();
	}
}
