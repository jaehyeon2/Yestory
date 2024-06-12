package dao.master;

import java.sql.SQLException;

import com.example.project.beans.param.TrendParam;

public interface MtrendDao {
	
	public int saveTrend(TrendParam trendParam) throws SQLException;
	
	public int updateTrend(TrendParam trendParam) throws SQLException;
	
	public int deleteTrend(TrendParam trendParam) throws SQLException;
}
