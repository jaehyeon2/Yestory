package com.example.project.dao.master;

import java.sql.SQLException;

import com.example.project.beans.param.YnewsParam;

public interface MnewsDao {
	
	public int insertNews(YnewsParam newsParam) throws SQLException;
	
	public int updateNews(YnewsParam newsParam) throws SQLException;
	
	public int deleteNews(YnewsParam newsParam) throws SQLException;
}
