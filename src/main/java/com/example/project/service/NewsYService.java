package com.example.project.service;

import java.util.List;

import com.example.project.beans.model.YNewsModel;
import com.example.project.beans.param.NewsParam;

public interface NewsYService {

	public boolean insertNews(NewsParam newsParam) throws Exception;
	
	public boolean deleteNews(NewsParam newsParam) throws Exception;
	
	public List<YNewsModel> selectNewsList(NewsParam newsParam) throws Exception;
}
