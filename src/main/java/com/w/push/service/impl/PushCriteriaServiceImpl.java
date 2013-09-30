package com.w.push.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.w.core.dao.GenericDao;
import com.w.core.service.GenericService;
import com.w.push.dao.PushCriteriaDao;
import com.w.push.entity.PushCriteria;
import com.w.push.service.PushCriteriaService;

@Service
public class PushCriteriaServiceImpl extends GenericService<PushCriteria> implements
		PushCriteriaService {

	@Resource private PushCriteriaDao pushCriteriaDao;
	
	@Override
	public GenericDao<PushCriteria> getGenericDao() {
		return pushCriteriaDao;
	}


}
