package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.WhiskyDetailService;
import dto.Whisky;

@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// リクエストパラメータを取得
			request.setCharacterEncoding("UTF-8");
			Integer id = Integer.parseInt(request.getParameter("id"));
			
			//セッションパラメータを取得
			HttpSession session = request.getSession();
			String word = (String)session.getAttribute("word");
			
			// 本の取得を依頼
			WhiskyDetailService service = new WhiskyDetailService();
			Whisky whisky = service.process(id);
			
			// リクエストスコープに設定
			request.setAttribute("whisky", whisky);
			
			// フォワード
			request.getRequestDispatcher("WEB-INF/whiskydetail.jsp").forward(request, response);
		} catch(SQLException e) {
			e.printStackTrace();
			
			// フォワード
			request.getRequestDispatcher("WEB-INF/SystemError.jsp").forward(request, response);
		}
	}

}
