package com.w.push.quartz.init;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.w.core.model.Criteria;
import com.w.push.cache.Cache;
import com.w.push.cache.Key;
import com.w.push.cache.RedisBase;
import com.w.push.entity.App;
import com.w.push.entity.Channel;
import com.w.push.entity.Partner;
import com.w.push.entity.PushContent;
import com.w.push.entity.PushCriteria;
import com.w.push.service.AppService;
import com.w.push.service.ChannelService;
import com.w.push.service.PartnerService;
import com.w.push.service.PushContentService;
import com.w.push.service.PushCriteriaService;

public class DataInit {

	@Resource private ChannelService channelService;
	@Resource private PartnerService partnerService;
	@Resource private AppService appService;
	@Resource private PushContentService pushContentService;
	@Resource private PushCriteriaService pushCriteriaService;
	
	@Resource private RedisBase redis;
	
	public synchronized void init() {
		//初始化基础数据
		initChannel();
		initPartner();
		initApp();
		initPush();
	}
	
	private void initChannel() {
		Criteria criteria = new Criteria();
		List<Channel> list = channelService.findListByCriteria(criteria);
		for(Channel entity : list) {
			redis.HASH.hset(Key.KEY_CHANNEL, String.valueOf(entity.getId()), entity.getName());
		}
		redis.KEYS.expired(Key.KEY_CHANNEL, Cache.ONE_DAY);
	}
	private void initPartner() {
		Criteria criteria = new Criteria();
		List<Partner> list = partnerService.findListByCriteria(criteria);
		for(Partner entity : list) {
			redis.HASH.hset(Key.KEY_PARTNER, String.valueOf(entity.getId()), entity.getName());
		}
		redis.KEYS.expired(Key.KEY_PARTNER, Cache.ONE_DAY);
	}
	private void initApp() {
		Criteria criteria = new Criteria();
		List<App> list = appService.findListByCriteria(criteria);
		for(App entity : list) {
			String value = JSON.toJSONString(entity);
			redis.HASH.hset(Key.KEY_APP, String.valueOf(entity.getId()), value);
		}
		redis.KEYS.expired(Key.KEY_APP, Cache.ONE_DAY);
	}
	
	private void initPush() {
		Criteria criteria = new Criteria();
		criteria.put("status", 1);
		List<PushContent> list = pushContentService.findListByCriteria(criteria);
		for(PushContent entity : list) {
			String value = JSON.toJSONString(entity);
			redis.HASH.hset(Key.KEY_CONTENT, String.valueOf(entity.getId()), value);
			Criteria criteria2 = new Criteria();
			criteria2.put("status", 1);
			criteria2.put("contentId", entity.getId());
			List<PushCriteria> cList = pushCriteriaService.findListByCriteria(criteria2);
			for(PushCriteria ec : cList) {
				String value2 = JSON.toJSONString(ec);
				redis.HASH.hset(Key.KEY_CRITERIA + entity.getId(), String.valueOf(ec.getId()), value2);
			}
			redis.KEYS.expired(Key.KEY_CRITERIA + entity.getId(), Cache.ONE_DAY);
		}
		redis.KEYS.expired(Key.KEY_CONTENT, Cache.ONE_DAY);
	}
}
