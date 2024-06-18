package com.example.project.service;

import com.example.project.beans.param.NewsParam;

public interface NewsYService {

	public boolean insertNews(NewsParam newsParam) throws Exception;
	
	public boolean deleteNews(NewsParam newsParam) throws Exception;
}
