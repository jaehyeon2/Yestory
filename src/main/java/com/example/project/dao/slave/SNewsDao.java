package com.example.project.dao.slave;

import java.sql.SQLException;
import java.util.List;

import com.example.project.beans.model.YnewsModel;
import com.example.project.beans.param.YnewsParam;

public interface SNewsDao {
	
	public List<YnewsModel> selectNewsList(YnewsParam newsParam) throws Exception;
	
	public YnewsModel selectNews(YnewsParam newsParam) throws Exception;
	
	public int deleteNews(YnewsParam newsParam) throws Exception;
	
}
