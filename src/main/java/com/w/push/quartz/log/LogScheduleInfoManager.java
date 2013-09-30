package com.w.push.quartz.log;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Queues;
import com.w.push.entity.HitLog;
import com.w.push.entity.PushLog;

public class LogScheduleInfoManager {

	private static final Logger LOG = LoggerFactory.getLogger(LogScheduleInfoManager.class);
	private static final Logger PUSHLOG = LoggerFactory.getLogger("pushLog");
	private static final Logger HITLOG = LoggerFactory.getLogger("hitLog");
	private static final ConcurrentLinkedQueue<PushLog> LOG_QUEUE = Queues.newConcurrentLinkedQueue();
	private static final ConcurrentLinkedQueue<HitLog> HIT_QUEUE = Queues.newConcurrentLinkedQueue();
	
	public static void add(PushLog log) {
		LOG_QUEUE.offer(log);
	}
	
	public static void add(HitLog log) {
		HIT_QUEUE.offer(log);
	}
	public void log() {
		LOG.info("log...");
		int count = LOG_QUEUE.size();
		while(count -- > 0) {
			PushLog pushLog = LOG_QUEUE.poll();
			if(pushLog != null) {
				String content = JSON.toJSONString(pushLog);
				PUSHLOG.info(content);
			}
		}
		count = HIT_QUEUE.size();
		while(count -- > 0) {
			HitLog hitLog = HIT_QUEUE.poll();
			if(hitLog != null) {
				String content = JSON.toJSONString(hitLog);
				HITLOG.info(content);
			}
		}
		
	}
	
	public void store() {
		LOG.info("log store...");
	}
}
