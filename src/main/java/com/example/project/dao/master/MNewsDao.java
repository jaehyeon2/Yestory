package com.example.project.dao.master;

import java.sql.SQLException;

import com.example.project.beans.param.NewsParam;

public interface MNewsDao {
	
	public int insertNews(NewsParam newsParam) throws SQLException;
	
	public int updateNews(NewsParam newsParam) throws SQLException;
	
	public int deleteNews(NewsParam newsParam) throws SQLException;
}
