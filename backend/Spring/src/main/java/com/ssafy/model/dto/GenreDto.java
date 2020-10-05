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

	@Override
	public String toString() {
		return "GenreDto [genreName=" + genreName + ", genreUrl=" + genreUrl + "]";
	}

}
