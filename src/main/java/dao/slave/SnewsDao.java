package dao.slave;

import java.sql.SQLException;
import java.util.List;

import com.example.project.beans.model.NewsModel;
import com.example.project.beans.param.NewsParam;

public interface SnewsDao {
	
	public List<NewsModel> selectNewsList(NewsParam newsParam) throws SQLException;
	
	public NewsModel selectNews(NewsParam newsParam) throws SQLException;
	
}
