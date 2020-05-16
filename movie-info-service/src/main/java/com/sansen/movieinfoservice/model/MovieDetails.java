package com.sansen.movieinfoservice.model;

import java.util.ArrayList;

public class MovieDetails {

	ArrayList<Object> results = new ArrayList<Object>();
	private float page;
	private float total_results;
	Dates DatesObject;
	private float total_pages;

	// Getter Methods

	public float getPage() {
		return page;
	}

	public float getTotal_results() {
		return total_results;
	}

	public Dates getDates() {
		return DatesObject;
	}

	public float getTotal_pages() {
		return total_pages;
	}

	// Setter Methods

	public void setPage(float page) {
		this.page = page;
	}

	public void setTotal_results(float total_results) {
		this.total_results = total_results;
	}

	public void setDates(Dates datesObject) {
		this.DatesObject = datesObject;
	}

	public void setTotal_pages(float total_pages) {
		this.total_pages = total_pages;
	}

	public ArrayList<Object> getResults() {
		return results;
	}

	public void setResults(ArrayList<Object> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "MovieDetails [results=" + results + ", page=" + page + ", total_results=" + total_results
				+ ", DatesObject=" + DatesObject + ", total_pages=" + total_pages + "]";
	}

	
}
