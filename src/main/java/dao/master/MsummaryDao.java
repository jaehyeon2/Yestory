package dao.master;

import java.sql.SQLException;

import com.example.project.beans.param.SummaryParam;

public interface MsummaryDao {

	public int saveSummary(SummaryParam summaryParam) throws SQLException;
	
	public int updateSummary(SummaryParam summaryParam) throws SQLException;
	
	public int deleteSummary(SummaryParam summaryParam) throws SQLException;
	
}
