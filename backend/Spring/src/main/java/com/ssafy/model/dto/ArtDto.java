package com.ssafy.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "art")
public class ArtDto {
	@Id
	@Column(name = "art_no")
	private int artNo;

	@Column(name = "art_title")
	private String artTitle;

	@Column(name = "art_artist")
	private String artArtist;

	@Column(name = "art_age")
	private String artAge;

	@Column(name = "art_size")
	private String artSize;

	@Column(name = "art_type")
	private String artType;

	@Column(name = "art_method")
	private String artMehtod;

	@Column(name = "art_museum")
	private String artMuseum;

	@Column(name = "art_url")
	private String artUrl;

	@Column(name = "art_genre")
	private String artGenre;

	@Column(name = "art_description")
	private String artDescription;

	@Column(name = "art_color")
	private String art_color;

	public int getArtNo() {
		return artNo;
	}

	public void setArtNo(int artNo) {
		this.artNo = artNo;
	}

	public String getArtTitle() {
		return artTitle;
	}

	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}

	public String getArtArtist() {
		return artArtist;
	}

	public void setArtArtist(String artArtist) {
		this.artArtist = artArtist;
	}

	public String getArtAge() {
		return artAge;
	}

	public void setArtAge(String artAge) {
		this.artAge = artAge;
	}

	public String getArtSize() {
		return artSize;
	}

	public void setArtSize(String artSize) {
		this.artSize = artSize;
	}

	public String getArtType() {
		return artType;
	}

	public void setArtType(String artType) {
		this.artType = artType;
	}

	public String getArtMehtod() {
		return artMehtod;
	}

	public void setArtMehtod(String artMehtod) {
		this.artMehtod = artMehtod;
	}

	public String getArtMuseum() {
		return artMuseum;
	}

	public void setArtMuseum(String artMuseum) {
		this.artMuseum = artMuseum;
	}

	public String getArtUrl() {
		return artUrl;
	}

	public void setArtUrl(String artUrl) {
		this.artUrl = artUrl;
	}

	public String getArtGenre() {
		return artGenre;
	}

	public void setArtGenre(String artGenre) {
		this.artGenre = artGenre;
	}

	public String getArtDescription() {
		return artDescription;
	}

	public void setArtDescription(String artDescription) {
		this.artDescription = artDescription;
	}

	public String getArt_color() {
		return art_color;
	}

	public void setArt_color(String art_color) {
		this.art_color = art_color;
	}

	@Override
	public String toString() {
		return "ArtDto [artNo=" + artNo + ", artTitle=" + artTitle + ", artArtist=" + artArtist + ", artAge=" + artAge
				+ ", artSize=" + artSize + ", artType=" + artType + ", artMehtod=" + artMehtod + ", artMuseum="
				+ artMuseum + ", artUrl=" + artUrl + ", artGenre=" + artGenre + ", artDescription=" + artDescription
				+ ", art_color=" + art_color + "]";
	}

}
