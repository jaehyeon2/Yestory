package com.example.project.dao.master;

import java.sql.SQLException;

import com.example.project.beans.param.YtrendParam;

public interface MtrendDao {
	
	public int insertTrend(YtrendParam trendParam) throws SQLException;
	
	public int updateTrend(YtrendParam trendParam) throws SQLException;
	
	public int deleteTrend(YtrendParam trendParam) throws SQLException;
}
