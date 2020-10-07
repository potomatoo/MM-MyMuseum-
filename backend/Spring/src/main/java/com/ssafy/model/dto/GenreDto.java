package com.ssafy.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
public class GenreDto implements Serializable {
	@Id
	@Column(name = "genre_name")
	private String genreName;

	@Column(name = "genre_url")
	private String genreUrl;

	@Column(name = "genre_age")
	private String genreAge;

	@Column(name = "genre_ktitle")
	private String genreKtitle;

	@Column(name = "genre_description")
	private String genreDescription;

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public String getGenreUrl() {
		return genreUrl;
	}

	public void setGenreUrl(String genreUrl) {
		this.genreUrl = genreUrl;
	}

	public String getGenreAge() {
		return genreAge;
	}

	public void setGenreAge(String genreAge) {
		this.genreAge = genreAge;
	}

	public String getGenreKtitle() {
		return genreKtitle;
	}

	public void setGenreKtitle(String genreKtitle) {
		this.genreKtitle = genreKtitle;
	}

	public String getGenreDescription() {
		return genreDescription;
	}

	public void setGenreDescription(String genreDescription) {
		this.genreDescription = genreDescription;
	}

	@Override
	public String toString() {
		return "GenreDto [genreName=" + genreName + ", genreUrl=" + genreUrl + ", genreAge=" + genreAge
				+ ", genreKtitle=" + genreKtitle + ", genreDescription=" + genreDescription + "]";
	}

}
