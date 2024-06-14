package com.example.project.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class BasicService {
	
	@Autowired
	protected SqlSessionTemplate sDbDao;
	
	@Autowired
	protected SqlSessionTemplate mDbDao;
	
	public String getYesterdayDate(){
		String yesterdayString = 
				LocalDate.now()
				.minusDays(1)
				.format(DateTimeFormatter
						.ofPattern("yyyyMMdd"));
		
		return yesterdayString;
	}

}
