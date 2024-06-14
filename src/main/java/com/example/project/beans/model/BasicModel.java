package com.example.project.beans.model;

import java.io.Serializable;

public class BasicModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected String history;

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}
}
