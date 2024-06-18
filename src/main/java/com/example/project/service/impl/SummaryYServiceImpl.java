package com.example.project.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.project.beans.model.YSummaryModel;
import com.example.project.beans.param.SummaryParam;
import com.example.project.dao.master.MSummaryDao;
import com.example.project.service.BasicService;
import com.example.project.service.SummaryYService;

@Service
public class SummaryYServiceImpl extends BasicService implements SummaryYService {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean insertSummary(SummaryParam summaryParam) throws Exception {
		Map<String, Object> map = new HashMap<>();
		try{
			map.put("history", summaryParam.getHistory());
			map.put("mtTrend", summaryParam.getMtTrend());
			map.put("msTitle", summaryParam.getMsTitle());
			map.put("msSummary", summaryParam.getMsSummary());
			map.put("msUrl", summaryParam.getMsUrl());
			
			int intResult = mDbDao.getMapper(MSummaryDao.class).insertSummary(map);
			if (intResult<1){
				throw new Exception("insertSummary error");
			}
			
		}catch(Exception e){
			logger.error("SummaryYService::insertSummary::Error = {}", e.getMessage());
			throw e;
		}
		return true;
	}

	@Override
	public boolean deleteSummary(SummaryParam summaryParam) throws Exception {
		Map<String, Object> map = new HashMap<>();
		try{
			map.put("history", summaryParam.getHistory());
			
			int intResult = mDbDao.getMapper(MSummaryDao.class).deleteSummary(map);
			if (intResult<1){
				logger.info("SummaryYServiceImpl::deletesummary::summary is not exist. history = {}", summaryParam.getHistory());
			}
		}catch(Exception e){
			logger.error("SummaryYServiceImpl::deleteSummary::Error = {}", e.getMessage());
		}
		return true;
	}

	@Override
	public List<YSummaryModel> selectSummaryList(SummaryParam summaryParam) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
