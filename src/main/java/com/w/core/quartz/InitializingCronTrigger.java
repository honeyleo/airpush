package com.w.core.quartz;

import java.io.Serializable;
import java.text.ParseException;

import org.springframework.scheduling.quartz.CronTriggerBean;

public class InitializingCronTrigger extends CronTriggerBean implements Serializable {  
		
	private static final long serialVersionUID = 1151569123570556443L;
	
	
	public InitializingCronTrigger() {
		super();
	}

	@SuppressWarnings("unused")
	private ScheduleInfoManager scheduleInfoManager;   
    
    // 设值注入，通过setter方法传入被调用者的实例scheduleInfoManager  
    public void setScheduleInfoManager(ScheduleInfoManager scheduleInfoManager) {   
        this.scheduleInfoManager = scheduleInfoManager;   
        // 因为在getCronExpressionFromDB使用到了scheduleInfoManager，所以  
        // 必须上一行代码设置scheduleInfoManager后进行  
        String cronExpression = getCronExpressionFromDB ();   
        // ①   
        // 因为extends CronTriggerBean ，此处调用父类方法初始化cronExpression  
       try {
    	   
    	   setCronExpression(cronExpression);
		
       } catch (ParseException e) {
    	   e.printStackTrace();
       }   
       // ②
    } 
    private String getCronExpressionFromDB() {
		
		return "0/60 * * * * ?";
	}
}

	
