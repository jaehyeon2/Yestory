package com.example.project.dao.master;

import java.util.Map;

public interface MNewsDao {
	
	public int insertNews(Map<String, Object> map) throws Exception;
	
	public int updateNews(Map<String, Object> mapm) throws Exception;
	
	public int deleteNews(Map<String, Object> map) throws Exception;
}
