package com.example.project.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.project.beans.model.YSummaryModel;
import com.example.project.beans.param.SummaryParam;
import com.example.project.dao.master.MSummaryDao;
import com.example.project.dao.slave.SSummaryDao;
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
			map.put("msType", summaryParam.getMsType());
			
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
		List<YSummaryModel> summaryList = null;
		Map<String, Object> map = new HashMap<>();
		try{
			map.put("mtTrend", summaryParam.getMtTrend());
			map.put("history", this.getYesterdayDate());
			
			summaryList = sDbDao.getMapper(SSummaryDao.class).selectSummaryList(map);
			
			if (summaryList==null){
				logger.info("SummaryYServiceImpl::selectSummaryList::Error = summaryList is null. trend = {}", summaryParam.getMtTrend());
			}
		}catch(Exception e){
			logger.info("SummaryYServiceImpl::selectSummaryList::Error = {}", e.getMessage());
		}
		return summaryList;
		
		
	}
	
	@Override
	public YSummaryModel selectSummary(SummaryParam summaryParam) throws Exception{
		YSummaryModel summary = null;
		Map<String, Object> map = new HashMap<>();
		try{
			map.put("history", this.getYesterdayDate());
			map.put("mtTrend", summaryParam.getMtTrend());
			map.put("number", Integer.parseInt(summaryParam.getNumber()));
			
			summary = sDbDao.getMapper(SSummaryDao.class).selectSummary(map);
			if (summary==null){
				logger.error("SummaryYServiceImpl::selectSummary::Error = summary is not exist");
				return null;
			}
		}catch(Exception e){
			logger.error("SummaryYServiceImpl::selectSummary::Error = {}", e.getMessage());
		}
		return summary;
	}

}
