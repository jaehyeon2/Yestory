package com.example.project.dao.master;

import java.sql.SQLException;

import com.example.project.beans.param.TrendParam;

public interface MTrendDao {
	
	public int insertTrend(TrendParam trendParam) throws SQLException;
	
	public int updateTrend(TrendParam trendParam) throws SQLException;
	
	public int deleteTrend(TrendParam trendParam) throws SQLException;
}
