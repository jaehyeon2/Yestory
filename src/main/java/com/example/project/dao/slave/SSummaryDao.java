package com.example.project.dao.slave;

import java.util.List;

import com.example.project.beans.model.YsummaryModel;
import com.example.project.beans.param.YsummaryParam;

public interface SSummaryDao {

	public List<YsummaryModel> selectSummaryList(YsummaryParam summaryParam) throws Exception;
	
	public YsummaryModel selectSummary(YsummaryParam summaryParam) throws Exception;
	
}
