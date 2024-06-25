package com.example.project.beans.model.response.template.output.basicCard.carousel;

import java.util.List;
import java.util.Map;

import com.example.project.beans.model.response.template.output.basicCard.carousel.item.Button;

public class BasicItem {
	private String title;
	private String description;
	private Map<String, Object> thumbnail;
	private List<Button> buttons;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Map<String, Object> getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(Map<String, Object> thumbnail) {
		this.thumbnail = thumbnail;
	}
	public List<Button> getButtons() {
		return buttons;
	}
	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}
}
