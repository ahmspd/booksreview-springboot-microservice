package com.lawencon.user.constant;

public enum ResponseMessageType {

	SAVED("Saved"), DELETED("Deleted"), ERROR("Error");

	private String desc;

	private ResponseMessageType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
