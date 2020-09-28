package com.ssafy.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "museum")
public class MuseumDto {
	@Id
	@Column(name = "museum_name")
	private String museumName;

	@Column(name = "museum_country")
	private String museumCountry;

	@Column(name = "museum_description")
	private String museumDescription;

	@Column(name = "museum_url")
	private String museumUrl;

	public String getMuseumName() {
		return museumName;
	}

	public void setMuseumName(String museumName) {
		this.museumName = museumName;
	}

	public String getMuseumCountry() {
		return museumCountry;
	}

	public void setMuseumCountry(String museumCountry) {
		this.museumCountry = museumCountry;
	}

	public String getMuseumDescription() {
		return museumDescription;
	}

	public void setMuseumDescription(String museumDescription) {
		this.museumDescription = museumDescription;
	}

	public String getMuseumUrl() {
		return museumUrl;
	}

	public void setMuseumUrl(String museumUrl) {
		this.museumUrl = museumUrl;
	}

	@Override
	public String toString() {
		return "MuseumDto [museumName=" + museumName + ", museumCountry=" + museumCountry + ", museumDescription="
				+ museumDescription + ", museumUrl=" + museumUrl + "]";
	}
}
