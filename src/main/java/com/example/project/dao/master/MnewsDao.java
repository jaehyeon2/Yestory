package com.example.project.dao.master;

import java.sql.SQLException;
import java.util.Map;

public interface MnewsDao {
	
	public int insertNews(Map<String, Object> map) throws SQLException;
	
	public int updateNews(Map<String, Object> mapm) throws SQLException;
	
	public int deleteNews(Map<String, Object> map) throws SQLException;
}
