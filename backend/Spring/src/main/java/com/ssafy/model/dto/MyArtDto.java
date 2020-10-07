package com.ssafy.model.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "myart")
public class MyArtDto {

	@Id
	@Column(name = "myart_no")
	private int myartNo;

	@Column(name = "myart_title")
	private String myartTitle;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "myart_age")
	private Date myartAge;

	@Column(name = "myart_type")
	private String myartType;

	@Column(name = "myart_url")
	private String myartUrl;

	@Column(name = "myart_description")
	private String myartDescription;

	public int getMyartNo() {
		return myartNo;
	}

	public void setMyartNo(int myartNo) {
		this.myartNo = myartNo;
	}

	public String getMyartTitle() {
		return myartTitle;
	}

	public void setMyartTitle(String myartTitle) {
		this.myartTitle = myartTitle;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getMyartAge() {
		return myartAge;
	}

	public void setMyartAge(Date myartAge) {
		this.myartAge = myartAge;
	}

	public String getMyartType() {
		return myartType;
	}

	public void setMyartType(String myartType) {
		this.myartType = myartType;
	}

	public String getMyartUrl() {
		return myartUrl;
	}

	public void setMyartUrl(String myartUrl) {
		this.myartUrl = myartUrl;
	}

	public String getMyartDescription() {
		return myartDescription;
	}

	public void setMyartDescription(String myartDescription) {
		this.myartDescription = myartDescription;
	}

	@Override
	public String toString() {
		return "MyArtDto [myartNo=" + myartNo + ", myartTitle=" + myartTitle + ", userId=" + userId + ", myartAge="
				+ myartAge + ", myartType=" + myartType + ", myartUrl=" + myartUrl + ", myartDescription="
				+ myartDescription + "]";
	}

}
