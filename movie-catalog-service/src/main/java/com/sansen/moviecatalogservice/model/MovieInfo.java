package com.sansen.moviecatalogservice.model;

import java.util.List;

public class MovieInfo {

	private float popularity;//: 224.468,
	private int vote_count;//: 3037,
	private boolean video;//: false,
	private String poster_path;//: "/aQvJ5WPzZgYVDrxLX4R6cLJCEaQ.jpg",
	private String id;//: 454626,
	private boolean adult;//: false,
	private String backdrop_path;//: "/stmYfCUGd8Iy6kAMBr6AmWqx8Bq.jpg",
	private String original_language;//: "en",
	private String original_title;//: "Sonic the Hedgehog",
	private List<String> genre_ids;//: [28,35,878,10751],
	private String title;//: "Sonic the Hedgehog",
	private float vote_average;//: 7.6,
	private String overview;//: "Based on the global blockbuster videogame franchise from Sega, Sonic the Hedgehog tells the story of the world’s speediest hedgehog as he embraces his new home on Earth. In this live-action adventure comedy, Sonic and his new best friend team up to defend the planet from the evil genius Dr. Robotnik and his plans for world domination.",
	private String release_date;//: "2020-02-12"
	
	
	public MovieInfo() {
	}

	public MovieInfo(String id, String title, String overview) {
		this.id = id;
		this.title = title;
		this.overview = overview;
	}

	public float getPopularity() {
		return popularity;
	}
	
	public void setPopularity(float popularity) {
		this.popularity = popularity;
	}
	public int getVote_count() {
		return vote_count;
	}
	public void setVote_count(int vote_count) {
		this.vote_count = vote_count;
	}
	public boolean isVideo() {
		return video;
	}
	public void setVideo(boolean video) {
		this.video = video;
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
	public String getid() {
		return id;
	}
	public void setid(String id) {
		this.id = id;
	}
	public boolean isAdult() {
		return adult;
	}
	public void setAdult(boolean adult) {
		this.adult = adult;
	}
	public String getBackdrop_path() {
		return backdrop_path;
	}
	public void setBackdrop_path(String backdrop_path) {
		this.backdrop_path = backdrop_path;
	}
	public String getOriginal_language() {
		return original_language;
	}
	public void setOriginal_language(String original_language) {
		this.original_language = original_language;
	}
	public String getOriginal_title() {
		return original_title;
	}
	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}
	public List<String> getGenre_ids() {
		return genre_ids;
	}
	public void setGenre_ids(List<String> genre_ids) {
		this.genre_ids = genre_ids;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getVote_average() {
		return vote_average;
	}
	public void setVote_average(float vote_average) {
		this.vote_average = vote_average;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
//	public String getDesc() {
//		// TODO Auto-generated method stub
//		return overview;
//	}
//	
}
