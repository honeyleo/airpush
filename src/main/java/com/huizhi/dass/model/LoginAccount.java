package com.huizhi.dass.model;

import java.util.List;

public class LoginAccount extends BaseEntity {
	
	private Admin user;
	
	private List<Role> roles;
	
	private List<Menu> menus;

	public Admin getUser() {
		return user;
	}

	public void setUser(Admin user) {
		this.user = user;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
	

}
