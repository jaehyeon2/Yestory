package com.example.project.beans.model;

import java.util.Map;

public class ResponseModel {
	private String version;
	
	private Map<String, Object> template;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Map<String, Object> getTemplate() {
		return template;
	}

	public void setTemplate(Map<String, Object> template) {
		this.template = template;
	}
	
}
