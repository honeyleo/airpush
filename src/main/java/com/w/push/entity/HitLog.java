package com.w.push.entity;

import java.util.Date;

public class HitLog {

	private Long id;
	
	private Long contentId;
	
	private String uui;
	
	private Long channelId;
	
	private Long partnerId;
	
	private Long appId;
	
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

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String getUui() {
		return uui;
	}

	public void setUui(String uui) {
		this.uui = uui;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Long partnerId) {
		this.partnerId = partnerId;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Date getHitTime() {
		return hitTime;
	}

	public void setHitTime(Date hitTime) {
		this.hitTime = hitTime;
	}
	
	
}
