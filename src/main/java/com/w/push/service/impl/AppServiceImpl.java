package com.w.push.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.w.core.dao.GenericDao;
import com.w.core.service.GenericService;
import com.w.push.dao.AppDao;
import com.w.push.entity.App;
import com.w.push.service.AppService;

@Service
public class AppServiceImpl extends GenericService<App> implements AppService {

	@Resource private AppDao appDao;
	
	@Override
	public GenericDao<App> getGenericDao() {
		return appDao;
	}

	
}
