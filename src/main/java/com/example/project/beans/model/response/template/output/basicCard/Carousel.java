package com.example.project.beans.model.response.template.output.basicCard;

import java.util.List;

import com.example.project.beans.model.response.template.output.basicCard.carousel.BasicItem;

public class Carousel {
	private String type;
	private List<BasicItem> items;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<BasicItem> getItems() {
		return items;
	}
	public void setItems(List<BasicItem> items) {
		this.items = items;
	}
}
