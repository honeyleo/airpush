package com.w.push.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.w.core.dao.GenericDao;
import com.w.core.service.GenericService;
import com.w.push.dao.ChannelDao;
import com.w.push.entity.Channel;
import com.w.push.service.ChannelService;

@Service
public class ChannelServiceImpl extends GenericService<Channel> implements
		ChannelService {

	@Resource private ChannelDao channelDao;
	
	@Override
	public GenericDao<Channel> getGenericDao() {
		return channelDao;
	}


}
