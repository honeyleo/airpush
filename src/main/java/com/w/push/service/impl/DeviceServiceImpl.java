package com.w.push.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.w.core.dao.GenericDao;
import com.w.core.service.GenericService;
import com.w.push.dao.DeviceDao;
import com.w.push.entity.Device;
import com.w.push.service.DeviceService;

@Service
public class DeviceServiceImpl extends GenericService<Device> implements DeviceService {

	@Resource private DeviceDao deviceDao;
	
	@Override
	public GenericDao<Device> getGenericDao() {
		return deviceDao;
	}

	
}
