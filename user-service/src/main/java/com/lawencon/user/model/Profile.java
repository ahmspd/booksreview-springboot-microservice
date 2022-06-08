package com.lawencon.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_profile")
public class Profile extends BaseModel {

	@Column(name = "profile_name", length = 100, nullable = false)
	private String profileName;

	@Column(name = "profile_phone", length = 20, unique = true, nullable = false)
	private String profilePhone;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getProfilePhone() {
		return profilePhone;
	}

	public void setProfilePhone(String profilePhone) {
		this.profilePhone = profilePhone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
