package com.example.project.beans.param.request;

import java.util.Map;

public class Intent {
	
	private String id;
	private String name;
	private Map<String, Object> extra;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, Object> getExtra() {
		return extra;
	}
	public void setExtra(Map<String, Object> extra) {
		this.extra = extra;
	}
	
}
