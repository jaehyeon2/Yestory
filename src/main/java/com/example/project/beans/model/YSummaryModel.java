package com.example.project.beans.model;

public class YSummaryModel extends BasicModel{
	
	private static final long serialVersionUID = 1L;
	
	private String mtTrend;
	private String msTitle;
	private String msSummary;
	private String msUrl;
	
	public String getMtTrend() {
		return mtTrend;
	}
	public void setMtTrend(String mtTrend) {
		this.mtTrend = mtTrend;
	}
	public String getMsTitle() {
		return msTitle;
	}
	public void setMsTitle(String msTitle) {
		this.msTitle = msTitle;
	}
	public String getMsSummary() {
		return msSummary;
	}
	public void setMsSummary(String msSummary) {
		this.msSummary = msSummary;
	}
	public String getMsUrl() {
		return msUrl;
	}
	public void setMsUrl(String msUrl) {
		this.msUrl = msUrl;
	}
}
