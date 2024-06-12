package com.example.project.beans.model;

public class TrendModel extends BasicModel{

	private static final long serialVersionUID = 1L;
	
	private String mtTrend;
	private String insertDate;
	
	public String getMtTrend() {
		return mtTrend;
	}
	public void setMtTrend(String mtTrend) {
		this.mtTrend = mtTrend;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
}
