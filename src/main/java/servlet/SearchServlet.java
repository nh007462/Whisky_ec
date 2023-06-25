package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.SearchService;
import dto.Whisky;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//リクエストパラメータに取得
			request.setCharacterEncoding("UTF-8");
			String word = request.getParameter("word");
		
			//検索を依頼
			SearchService service = new SearchService();
			List<Whisky> whiskylist = service.process(word);
			
			//リクエストスコープに格納
			request.setAttribute("whisky", whiskylist);
			//セッションスコープに格納
			HttpSession session = request.getSession();
			session.setAttribute("word", word);
		
			//フォワード
			request.getRequestDispatcher("WEB-INF/result.jsp").forward(request,  response);
		} catch(SQLException e) {
			//フォワード
			request.getRequestDispatcher("WEB-INF/SystemError.jsp").forward(request,  response);
		}
	}

}
