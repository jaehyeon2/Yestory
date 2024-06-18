package com.example.project.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.project.beans.param.NewsParam;
import com.example.project.dao.master.MNewsDao;
import com.example.project.service.BasicService;
import com.example.project.service.NewsYService;

@Service
public class NewsYServiceImpl extends BasicService implements NewsYService {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean insertNews(NewsParam newsParam) throws Exception{
		
		Map<String, Object> map = new HashMap<>();
		try{
			map.put("mtTrend", newsParam.getMtTrend());
			map.put("mnTitle", newsParam.getMnTitle());
			map.put("mnContent", newsParam.getMnContent());
			map.put("mnUrl", newsParam.getMnUrl());
			map.put("history", newsParam.getHistory());
			
			int result = mDbDao.getMapper(MNewsDao.class).insertNews(map);
			if (result<1){
				throw new Exception("insertNews error");
			}
			
		}catch(Exception e){
			logger.error("CrawlingServiceImpl::insertNaverNews::Error = {}", e.getMessage());
			throw e;
		}
		return true;
	}

	@Override
	public boolean deleteNews(NewsParam newsParam) throws Exception {
		Map<String, Object> map = new HashMap<>();
		try{
			map.put("history", newsParam.getHistory());
			
			mDbDao.getMapper(MNewsDao.class).deleteNews(map);
			
		}catch(Exception e){
			logger.error("NewsYServiceImpl::deleteNews::Error = {}", e.getMessage());
			throw e;
		}
		return true;
	}

}
