package com.ssafy.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "favorite")
public class FavoriteDto {
	@Id
	@Column(name = "favorite_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int favoriteNo;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "art_no")
	private int artNo;

	public int getFavoriteNo() {
		return favoriteNo;
	}

	public void setFavoriteNo(int favoriteNo) {
		this.favoriteNo = favoriteNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getArtNo() {
		return artNo;
	}

	public void setArtNo(int artNo) {
		this.artNo = artNo;
	}

	@Override
	public String toString() {
		return "FavoriteDto [favoriteNo=" + favoriteNo + ", userId=" + userId + ", artNo=" + artNo + "]";
	}
}
