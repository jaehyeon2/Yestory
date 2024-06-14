package com.example.project.beans.model;

public class YnewsModel extends BasicModel{
	
	private static final long serialVersionUID = 1L;
	
	private String mnKeyword;
	private String mnTitle;
	private String mnContent;
	private String mnUrl;
	private String mnDate;
	
	public String getMnKeyword() {
		return mnKeyword;
	}
	public void setMnKeyword(String mnKeyword) {
		this.mnKeyword = mnKeyword;
	}
	public String getMnTitle() {
		return mnTitle;
	}
	public void setMnTitle(String mnTitle) {
		this.mnTitle = mnTitle;
	}
	public String getMnContent() {
		return mnContent;
	}
	public void setMnContent(String mnContent) {
		this.mnContent = mnContent;
	}
	public String getMnUrl() {
		return mnUrl;
	}
	public void setMnUrl(String mnUrl) {
		this.mnUrl = mnUrl;
	}
	public String getMnDate() {
		return mnDate;
	}
	public void setMnDate(String mnDate) {
		this.mnDate = mnDate;
	}
}
