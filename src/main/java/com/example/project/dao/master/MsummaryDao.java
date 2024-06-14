package com.example.project.dao.master;

import java.sql.SQLException;

import com.example.project.beans.param.YsummaryParam;

public interface MsummaryDao {

	public int insertSummary(YsummaryParam summaryParam) throws SQLException;
	
	public int updateSummary(YsummaryParam summaryParam) throws SQLException;
	
	public int deleteSummary(YsummaryParam summaryParam) throws SQLException;
	
}
