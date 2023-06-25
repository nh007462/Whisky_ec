package application;

import java.sql.SQLException;
import java.util.List;

import dao.WhiskyDAO;
import dto.Whisky;

public class SearchService {
	
	public List<Whisky> process(String word) throws SQLException{
		WhiskyDAO dao = new WhiskyDAO();
		List<Whisky> whiskylist = dao.selectByName(word);
		return whiskylist;
	}

}
