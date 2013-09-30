package com.w.core.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.CronTriggerBean;

public class ScheduleInfoManager {

	private static final Logger LOG = LoggerFactory.getLogger(ScheduleInfoManager.class);
	
	private Scheduler scheduler;

	// 设值注入，通过setter方法传入被调用者的实例scheduler
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public void reScheduleJob() throws SchedulerException, java.text.ParseException {
		// 运行时可通过动态注入的scheduler得到trigger，注意采用这种注入方式在有的项目中会有问题，如果遇到注入问题，可以采取在运行方法时候，获得bean来避免错误发生。
		CronTriggerBean trigger = (CronTriggerBean) scheduler.getTrigger(
				"cronTrigger", Scheduler.DEFAULT_GROUP);
		String dbCronExpression = getCronExpressionFromDB();
		String originConExpression = trigger.getCronExpression();
		// 判断从DB中取得的任务时间(dbCronExpression)和现在的quartz线程中的任务时间(originConExpression)是否相等
		// 如果相等，则表示用户并没有重新设定数据库中的任务时间，这种情况不需要重新rescheduleJob
		if (!originConExpression.equalsIgnoreCase(dbCronExpression)) {
			trigger.setCronExpression(dbCronExpression);
			LOG.info("rescheduleJob...");
			scheduler.rescheduleJob("cronTrigger", Scheduler.DEFAULT_GROUP,
					trigger);
		}
		// 下面是具体的job内容，可自行设置
		executeJobDetail();
	}

	public void executeJobDetail() {
//		LOG.info("scheduler...");
	}
	private String getCronExpressionFromDB() {
		
		return "0/60 * * * * ?";
	}
	
}
