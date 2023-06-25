package application;

import java.sql.SQLException;

import dao.MemberDAO;
import dto.Member;

public class LoginService {
	public Member process(Integer id, String password) throws SQLException{
		MemberDAO dao = new MemberDAO();
		Member member = dao.login(id, password);
		return member;
	}

}
