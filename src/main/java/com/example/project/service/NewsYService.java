package com.example.project.service;

import com.example.project.beans.param.YnewsParam;

public interface NewsYService {

	public boolean insertNews(YnewsParam newsParam) throws Exception;
	
	public boolean deleteNews(YnewsParam newsParam) throws Exception;
}
