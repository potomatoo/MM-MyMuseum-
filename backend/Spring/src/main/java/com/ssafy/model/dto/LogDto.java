package com.ssafy.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log")
public class LogDto {
	@Id
	@Column(name = "log_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int logNo;

	@Column(name = "user_id")
	private String user_id;

	@Column(name = "art_no")
	private int artNo;

	@Column(name = "log_type")
	private int type;

	public int getLogNo() {
		return logNo;
	}

	public void setLogNo(int logNo) {
		this.logNo = logNo;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getArtNo() {
		return artNo;
	}

	public void setArtNo(int artNo) {
		this.artNo = artNo;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "LogDto [logNo=" + logNo + ", user_id=" + user_id + ", artNo=" + artNo + ", type=" + type + "]";
	}

}
