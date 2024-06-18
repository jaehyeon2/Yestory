package com.example.project.service;

import java.util.List;

import com.example.project.beans.model.YSummaryModel;
import com.example.project.beans.param.SummaryParam;

public interface SummaryYService {

	public boolean insertSummary(SummaryParam summaryParam) throws Exception;
	public boolean deleteSummary(SummaryParam summaryParam) throws Exception;
	public List<YSummaryModel> selectSummaryList(SummaryParam summaryParam) throws Exception;
}
