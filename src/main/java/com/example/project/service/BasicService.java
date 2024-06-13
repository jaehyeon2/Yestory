package com.example.project.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BasicService {
	
	public String getYesterdayDate(){
		String yesterdayString = 
				LocalDate.now()
				.minusDays(1)
				.format(DateTimeFormatter
						.ofPattern("yyyyMMdd"));
		
		return yesterdayString;
	}

}
