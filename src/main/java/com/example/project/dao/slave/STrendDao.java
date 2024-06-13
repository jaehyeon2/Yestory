package com.example.project.dao.slave;

import java.sql.SQLException;
import java.util.List;

import com.example.project.beans.model.TrendModel;
import com.example.project.beans.param.TrendParam;

public interface STrendDao {

	public List<TrendModel> selectTrendList(TrendParam trendParam) throws SQLException;
	
	public TrendModel selectTrend(TrendParam trendParam) throws SQLException;
}
