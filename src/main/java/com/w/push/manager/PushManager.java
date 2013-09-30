package com.w.push.manager;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.w.core.exception.AppException;
import com.w.core.model.Message;
import com.w.push.cache.Cache;
import com.w.push.cache.Key;
import com.w.push.entity.App;
import com.w.push.entity.PushContent;
import com.w.push.entity.PushCriteria;
import com.w.push.entity.PushLog;
import com.w.push.quartz.log.LogScheduleInfoManager;

@Component
public class PushManager {

	public Message getPushContent(String uui, String qn, Long cid) {
		
		Message.Builder builder = Message.Builder.newBuilder();
		Cache cache = Cache.getInstance();
		try {
			if(Strings.isNullOrEmpty(qn)) {
				throw new AppException("100", "qn error");
			}
			String[] qns = qn.split("-");
			if(qns.length < 2) {
				throw new AppException("100", "qn error");
			}
			long channelId = Long.parseLong(qns[0]);
			String channel = cache.hget(Key.KEY_CHANNEL, qns[0]);
			if(Strings.isNullOrEmpty(channel)) {
				throw new AppException("101", "channel not found");
			}
			long appId = Long.parseLong(qns[1]);
			String aValue = cache.hget(Key.KEY_APP, qns[1]);
			App app = JSON.parseObject(aValue, App.class);
			if(app == null) {
				throw new AppException("102", "channel not found");
			}
			Map<String,String> contentMap = cache.hgetAll(Key.KEY_CONTENT);
			if(contentMap == null || contentMap.isEmpty()) {
				builder.put("c", 0);
				return builder.build();
			}
			List<Message> data = Lists.newArrayList();
			for(Entry<String, String> entry : contentMap.entrySet()) {
				if(cid >= Long.parseLong(entry.getKey())) {
					break;
				}
				PushContent entity = JSON.parseObject(entry.getValue(), PushContent.class);
				Map<String,String> criteraiMap = cache.hgetAll(Key.KEY_CRITERIA + entry.getKey());
				Collection<String> coll = criteraiMap.values();
				boolean flag = true;
				int i = 0;
				for(String cStr : coll) {
					PushCriteria pc = JSON.parseObject(cStr, PushCriteria.class);
					boolean result = true;
					result = result && (pc.getPartnerId() == 0 || pc.getPartnerId() == app.getPartnerId());
					result = result && (pc.getChannelId() == 0 || pc.getChannelId() == channelId);
					result = result && (pc.getAppId() == 0 || pc.getAppId() == appId);
					if(result) {
						flag = true;
						break;
					}
					i++;
					if(i == coll.size()) {
						flag = result;
					}
				}
				if(flag) {
					
					Message.Builder dBuilder = Message.Builder.newBuilder();
					dBuilder.put("id", entity.getId()).put("title", entity.getTitle()).put("content", entity.getContent())
						.put("type", entity.getType()).put("msgType", entity.getMsgType()).put("url", entity.getUrl());
					data.add(dBuilder.build());
					
					PushLog pushLog = new PushLog();
					pushLog.setAppId(appId).setChannelId(channelId).setContentId(entity.getId()).setUui(uui).setPartnerId(app.getPartnerId()).setPushTime(new Date()).setStatus(0);
					LogScheduleInfoManager.add(pushLog);
				}
			}
			builder.put("c", 0);
			builder.put("d", data);
		}catch(AppException e) {
			builder.put("c", e.getCode()).put("m", e.getMessage());
		} catch (Exception e) {
			builder.put("c", "200").put("m", "system error");
		}
		return builder.build();
	}
	
	public Message hit(String uui, String qn, Long cid) {
		Message.Builder builder = Message.Builder.newBuilder();
		Cache cache = Cache.getInstance();
		try {
			if(Strings.isNullOrEmpty(qn)) {
				throw new AppException("100", "qn error");
			}
			String[] qns = qn.split("-");
			if(qns.length < 2) {
				throw new AppException("100", "qn error");
			}
			long channelId = Long.parseLong(qns[0]);
			String channel = cache.hget(Key.KEY_CHANNEL, qns[0]);
			if(Strings.isNullOrEmpty(channel)) {
				throw new AppException("101", "channel not found");
			}
			long appId = Long.parseLong(qns[1]);
			String aValue = cache.hget(Key.KEY_APP, qns[1]);
			App app = JSON.parseObject(aValue, App.class);
			if(app == null) {
				throw new AppException("102", "channel not found");
			}
			PushLog hitLog = new PushLog();
			hitLog.setAppId(appId).setChannelId(channelId).setContentId(cid).setUui(uui).setPartnerId(app.getPartnerId()).setHitTime(new Date()).setStatus(1);
			LogScheduleInfoManager.addHitLog(hitLog);
			builder.put("c", 0);
		}catch(AppException e) {
			builder.put("c", e.getCode()).put("m", e.getMessage());
		} catch (Exception e) {
			builder.put("c", "200").put("m", "system error");
		}
		return builder.build();
	}
}
