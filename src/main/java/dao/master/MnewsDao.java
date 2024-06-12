package dao.master;

import java.sql.SQLException;

import com.example.project.beans.param.NewsParam;

public interface MnewsDao {
	
	public int saveNews(NewsParam newsParam) throws SQLException;
	
	public int updateNews(NewsParam newsParam) throws SQLException;
	
	public int deleteNews(NewsParam newsParam) throws SQLException;
}
