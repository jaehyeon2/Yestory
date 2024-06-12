package dao.slave;

import java.sql.SQLException;
import java.util.List;

import com.example.project.beans.model.SummaryModel;
import com.example.project.beans.param.SummaryParam;

public interface SsummaryDao {

	public List<SummaryModel> selectSummaryList(SummaryParam summaryParam) throws SQLException;
	
	public SummaryModel selectSummary(SummaryParam summaryParam) throws SQLException;
	
}
