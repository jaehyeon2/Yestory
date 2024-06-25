package com.example.project.beans.model.response.template.output.basicCard;

import java.util.List;

import com.example.project.beans.model.response.template.output.basicCard.carousel.Item;

public class Carousel {
	private String type;
	private List<Item> items;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
}
