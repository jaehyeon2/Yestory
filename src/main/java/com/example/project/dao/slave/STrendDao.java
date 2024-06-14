package com.example.project.dao.slave;

import java.sql.SQLException;
import java.util.List;

import com.example.project.beans.model.YtrendModel;
import com.example.project.beans.param.YtrendParam;

public interface STrendDao {

	public List<YtrendModel> selectTrendList(YtrendParam trendParam) throws SQLException;
	
	public YtrendModel selectTrend(YtrendParam trendParam) throws SQLException;
}
