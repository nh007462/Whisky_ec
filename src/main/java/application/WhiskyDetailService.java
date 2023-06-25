package application;

import java.sql.SQLException;

import dao.WhiskyDAO;
import dto.Whisky;

public class WhiskyDetailService {
	
	public Whisky process(Integer id) throws SQLException {
		WhiskyDAO dao = new WhiskyDAO();
		Whisky whisky = dao.select(id);
		return whisky;
	}

}
