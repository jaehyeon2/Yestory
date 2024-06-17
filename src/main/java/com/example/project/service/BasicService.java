package com.example.project.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class BasicService {
	
	@Autowired
	protected SqlSession mDbDao;
	
	@Autowired
	protected SqlSession sDbDao;
	
	
	public String getYesterdayDate(){
		String yesterdayString = 
				LocalDate.now()
				.minusDays(1)
				.format(DateTimeFormatter
						.ofPattern("yyyyMMdd"));
		
		return yesterdayString;
	}

}
