package com.w.push.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.w.core.dao.GenericDao;
import com.w.core.service.GenericService;
import com.w.push.dao.PartnerDao;
import com.w.push.entity.Partner;
import com.w.push.service.PartnerService;

@Service
public class PartnerServiceImpl extends GenericService<Partner> implements
		PartnerService {

	@Resource private PartnerDao partnerDao;
	
	@Override
	public GenericDao<Partner> getGenericDao() {
		return partnerDao;
	}


}
