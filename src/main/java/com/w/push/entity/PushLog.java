package com.w.push.entity;

import java.util.Date;

public class PushLog {

	private Long id;
	
	private Long contentId;
	
	private String uui;
	
	private Long channelId;
	
	private Long partnerId;
	
	private Long appId;
	
	private Integer status;//0-推送；1-点击
	
	private Date pushTime;
	
	private Date hitTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContentId() {
		return contentId;
	}

	public PushLog setContentId(Long contentId) {
		this.contentId = contentId;
		return this;
	}

	public String getUui() {
		return uui;
	}

	public PushLog setUui(String uui) {
		this.uui = uui;
		return this;
	}

	public Long getChannelId() {
		return channelId;
	}

	public PushLog setChannelId(Long channelId) {
		this.channelId = channelId;
		return this;
	}

	public Long getPartnerId() {
		return partnerId;
	}

	public PushLog setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
		return this;
	}

	public Long getAppId() {
		return appId;
	}

	public PushLog setAppId(Long appId) {
		this.appId = appId;
		return this;
	}

	public Date getPushTime() {
		return pushTime;
	}

	public PushLog setPushTime(Date pushTime) {
		this.pushTime = pushTime;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public PushLog setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public Date getHitTime() {
		return hitTime;
	}

	public PushLog setHitTime(Date hitTime) {
		this.hitTime = hitTime;
		return this;
	}
	
	
}
