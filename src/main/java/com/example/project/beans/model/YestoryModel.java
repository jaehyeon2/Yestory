package com.example.project.beans.model;

import java.util.List;

public class YestoryModel extends BasicModel{
	
	private static final long serialVersionUID = 1L;
	
	private List<YNewsModel> newsList;
	private List<YSummaryModel> summaryList;
	private List<YTrendModel> trendList;
	
	public List<YNewsModel> getNewsList() {
		return newsList;
	}
	public void setNewsList(List<YNewsModel> newsList) {
		this.newsList = newsList;
	}
	public List<YSummaryModel> getSummaryList() {
		return summaryList;
	}
	public void setSummaryList(List<YSummaryModel> summaryList) {
		this.summaryList = summaryList;
	}
	public List<YTrendModel> getTrendList() {
		return trendList;
	}
	public void setTrendList(List<YTrendModel> trendList) {
		this.trendList = trendList;
	}
}
