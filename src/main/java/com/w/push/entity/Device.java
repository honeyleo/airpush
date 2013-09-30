package com.w.push.entity;

public class Device {

	private Long id;
	
	private String uui;//用户唯一标识
	
	private String token;//设备令牌
	
	private String imei;//设备imei
	
	private Integer type;//Android、IOS等
	
	private String region;//地区
	
	private String operator;//运营商
	
	private Boolean online;//在线否

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUui() {
		return uui;
	}

	public void setUui(String uui) {
		this.uui = uui;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}
	
	
}
