package com.lawencon.user.constant;

public enum RoleType {

	ADMIN("ADM"), CUSTOMER("CUST");

	private String desc;

	private RoleType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
