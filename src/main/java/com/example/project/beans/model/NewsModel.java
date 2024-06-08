package com.example.project.beans.model;

public class NewsModel extends ModelS{
	
	private static final long serialVersionUID = 1L;
	
	private String nTitle;
	private String nContent;
	private String nDate;
	private String nUrl;
	private String nKeyword;
	
	public String getnTitle() {
		return nTitle;
	}
	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}
	public String getnContent() {
		return nContent;
	}
	public void setnContent(String nContent) {
		this.nContent = nContent;
	}
	public String getnDate() {
		return nDate;
	}
	public void setnDate(String nDate) {
		this.nDate = nDate;
	}
	public String getnUrl() {
		return nUrl;
	}
	public void setnUrl(String nUrl) {
		this.nUrl = nUrl;
	}
	public String getnKeyword() {
		return nKeyword;
	}
	public void setnKeyword(String nKeyword) {
		this.nKeyword = nKeyword;
	}

}
