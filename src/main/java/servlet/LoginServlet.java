package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.LoginService;
import dto.Member;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//リクエストパラメータに取得
			request.setCharacterEncoding("UTF-8");
			Integer id = Integer.parseInt(request.getParameter("ID"));
			String password = request.getParameter("パスワード");
			
		
			//ログイン情報確認を依頼
			LoginService service = new LoginService();
			Member member = service.process(id, password);
			
			//セッションスコープに格納
			if(member != null) {
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				//フォワードログイン完了
				request.getRequestDispatcher("WEB-INF/items_search.jsp").forward(request,  response);
			} else {
				//リクエストスコープに格納
				String message = "IDもしくはパスワードが間違っています";
				//リクエストスコープに格納
				request.setAttribute("message", message);
				//フォワードログイン失敗
				request.getRequestDispatcher("WEB-INF/login.jsp").forward(request,  response);
			}
		
			
			
		} catch(SQLException e) {
			//フォワード
			request.getRequestDispatcher("WEB-INF/SystemError.jsp").forward(request,  response);
		}
		
	}

}
