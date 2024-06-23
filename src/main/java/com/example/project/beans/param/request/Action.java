package com.example.project.beans.param.request;

import java.util.Map;

public class Action {
	
	private String name;
	private String clientExtra;
	private Map<String, Object> params;
	private String id;
	private Map<String, Object> detailParams;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClientExtra() {
		return clientExtra;
	}
	public void setClientExtra(String clientExtra) {
		this.clientExtra = clientExtra;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Map<String, Object> getDetailParams() {
		return detailParams;
	}
	public void setDetailParams(Map<String, Object> detailParams) {
		this.detailParams = detailParams;
	}
}
