package com.w.push.quartz.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Queues;
import com.w.push.entity.PushLog;
import com.w.push.service.PushLogService;
import com.w.utils.DateUtil;

public class LogScheduleInfoManager {

	private static final Logger LOG = LoggerFactory.getLogger(LogScheduleInfoManager.class);
	
	private static final String DIR_PUSHLOG = "e:\\log\\airpush\\push\\";
	private static final String DIR_HITLOG = "e:\\log\\airpush\\hit\\";
	
	private static final Logger PUSHLOG = LoggerFactory.getLogger("pushLog");
	private static final Logger HITLOG = LoggerFactory.getLogger("hitLog");
	private static final ConcurrentLinkedQueue<PushLog> LOG_QUEUE = Queues.newConcurrentLinkedQueue();
	private static final ConcurrentLinkedQueue<PushLog> HIT_QUEUE = Queues.newConcurrentLinkedQueue();
	
	Executor workers = new ThreadPoolExecutor(2, 65536 << 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	
	@Resource private PushLogService pushLogService;
	
	public static void add(PushLog log) {
		LOG_QUEUE.offer(log);
	}
	
	public static void addHitLog(PushLog log) {
		HIT_QUEUE.offer(log);
	}
	public void log() {
		LOG.info("log...");
		workers.execute(new Runnable() {
			
			@Override
			public void run() {
				int count = LOG_QUEUE.size();
				while(count -- > 0) {
					PushLog pushLog = LOG_QUEUE.poll();
					if(pushLog != null) {
						String content = JSON.toJSONString(pushLog);
						PUSHLOG.info(content);
					}
				}
			}
		});
		workers.execute(new Runnable() {
			
			@Override
			public void run() {
				int count = HIT_QUEUE.size();
				while(count -- > 0) {
					PushLog hitLog = HIT_QUEUE.poll();
					if(hitLog != null) {
						String content = JSON.toJSONString(hitLog);
						HITLOG.info(content);
					}
				}
			}
		});
		
	}
	
	public void store() {
		LOG.info("log store...");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				pushLog(new Date());
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				hitLog(new Date());
			}
		}).start();
	}
	
	public void pushLog(Date date) {
		final String dir = DIR_PUSHLOG + DateUtil.date2String3(date);
		File file = new File(dir);
		File[] files = file.listFiles();
		for(File f : files) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f),"UTF-8"));
				String line = null;
				while((line = reader.readLine()) != null) {
					PushLog pushLog = JSON.parseObject(line, PushLog.class);
					pushLogService.save(pushLog);
				}
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void hitLog(Date date) {
		final String dir = DIR_HITLOG + DateUtil.date2String3(date);
		File file = new File(dir);
		File[] files = file.listFiles();
		for(File f : files) {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f),"UTF-8"));
				String line = null;
				while((line = reader.readLine()) != null) {
					PushLog hitLog = JSON.parseObject(line, PushLog.class);
					pushLogService.hit(hitLog);
				}
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
