package com.w.push.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.w.core.dao.GenericDao;
import com.w.core.service.GenericService;
import com.w.push.dao.PushContentDao;
import com.w.push.entity.PushContent;
import com.w.push.service.PushContentService;

@Service
public class PushContentServiceImpl extends GenericService<PushContent> implements
		PushContentService {

	@Resource private PushContentDao pushContentDao;
	
	@Override
	public GenericDao<PushContent> getGenericDao() {
		return pushContentDao;
	}


}
