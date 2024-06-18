package com.example.project.dao.slave;

import java.util.List;
import java.util.Map;

import com.example.project.beans.model.YNewsModel;

public interface SNewsDao {
	
	public List<YNewsModel> selectNewsList(Map<String, Object> map) throws Exception;
	
	public YNewsModel selectNews(Map<String, Object> map) throws Exception;
	
	public int deleteNews(Map<String, Object> map) throws Exception;
	
}
