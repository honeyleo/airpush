package com.w.push.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.w.core.dao.GenericDao;
import com.w.core.service.GenericService;
import com.w.push.dao.PushLogDao;
import com.w.push.entity.PushLog;
import com.w.push.service.PushLogService;

@Service
public class PushLogServiceImpl extends GenericService<PushLog> implements PushLogService {

	@Resource private PushLogDao pushLogDao;
	
	@Override
	public GenericDao<PushLog> getGenericDao() {
		return pushLogDao;
	}

	@Override
	public void hit(PushLog entity) {
		pushLogDao.hit(entity);
	}

	
}
