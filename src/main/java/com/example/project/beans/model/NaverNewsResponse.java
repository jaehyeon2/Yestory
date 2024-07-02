package com.example.project.beans.model;

import java.util.List;

import com.example.project.beans.model.newsResponse.News;

public class NaverNewsResponse {
	private String lastBuildDate;
	private String total;
	private String start;
	private String display;
	
	private List<News> items;
	
	public String getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public List<News> getItems() {
		return items;
	}

	public void setItems(List<News> items) {
		this.items = items;
	}
	
	
}
