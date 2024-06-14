package com.example.project.dao.slave;

import java.sql.SQLException;
import java.util.List;

import com.example.project.beans.model.YsummaryModel;
import com.example.project.beans.param.YsummaryParam;

public interface SSummaryDao {

	public List<YsummaryModel> selectSummaryList(YsummaryParam summaryParam) throws SQLException;
	
	public YsummaryModel selectSummary(YsummaryParam summaryParam) throws SQLException;
	
}
