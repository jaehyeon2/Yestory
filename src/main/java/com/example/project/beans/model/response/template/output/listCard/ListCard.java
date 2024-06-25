package com.example.project.beans.model.response.template.output.listCard;

import java.util.List;

public class ListCard {
	private Header header;
	private List<ListItem> items;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public List<ListItem> getItems() {
		return items;
	}
	public void setItems(List<ListItem> items) {
		this.items = items;
	}
}
