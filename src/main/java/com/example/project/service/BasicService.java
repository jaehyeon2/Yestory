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
	
	
	protected String getYesterdayDate(){
		String yesterdayString = 
				LocalDate.now()
				.minusDays(1)
				.format(DateTimeFormatter
						.ofPattern("yyyy-MM-dd"));
		
		return yesterdayString;
	}

}
