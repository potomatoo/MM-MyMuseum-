package com.ssafy.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserDto implements Serializable {
	@Id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "user_password")
	private String userPassword;

	@Column(name = "user_type")
	private int userType;

	@Column(name = "user_gender")
	private int userGender;
	@Column(name = "user_age")
	private int userAge;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getUserGender() {
		return userGender;
	}

	public void setUserGender(int userGender) {
		this.userGender = userGender;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userPassword=" + userPassword + ", userType=" + userType
				+ ", userGender=" + userGender + ", userAge=" + userAge + "]";
	}

}
