package com.example.project.dao.slave;

import java.util.List;
import java.util.Map;

import com.example.project.beans.model.YnewsModel;

public interface SnewsDao {
	
	public List<YnewsModel> selectNewsList(Map<String, Object> map) throws Exception;
	
	public YnewsModel selectNews(Map<String, Object> map) throws Exception;
	
	public int deleteNews(Map<String, Object> map) throws Exception;
	
}
