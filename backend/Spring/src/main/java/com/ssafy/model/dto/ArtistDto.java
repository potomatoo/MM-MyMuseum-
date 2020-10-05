package com.ssafy.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "artist")
public class ArtistDto implements Serializable {
	@Id
	@Column(name = "artist_name")
	private String artistName;

	@Column(name = "artist_age")
	private String artistAge;

	@Column(name = "artist_img")
	private String artistImg;

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getArtistAge() {
		return artistAge;
	}

	public void setArtistAge(String artistAge) {
		this.artistAge = artistAge;
	}

	public String getArtistImg() {
		return artistImg;
	}

	public void setArtistImg(String artistImg) {
		this.artistImg = artistImg;
	}

	@Override
	public String toString() {
		return "ArtistDto [artistName=" + artistName + ", artistAge=" + artistAge + ", artistImg=" + artistImg + "]";
	}

}
