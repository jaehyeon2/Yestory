package com.example.project.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.example.project.service.IndexService;

@EnableAsync
@Service
public class IndexServiceImpl implements IndexService {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void saveIndexKey(String msNote, long lngMsNo, String userId) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
