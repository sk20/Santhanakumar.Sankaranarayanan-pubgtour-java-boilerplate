package com.sansen.movieinfoservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieInfo {

	private String movieId;
	private String name;
	private String desc;
	private String poster_path;
	
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
	
}
