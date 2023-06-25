package servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		int pagetran = Integer.parseInt(request.getParameter("request"));
		
		
		//判別処理
		if(pagetran == 0) {
			//フォワード
			request.getRequestDispatcher("WEB-INF/member_register.jsp").forward(request, response);
		} else if(pagetran == 1) {
			//フォワード
			request.getRequestDispatcher("WEB-INF/items_search.jsp").forward(request, response);
		} else if(pagetran == 2) {
			//フォワード
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		} else {
			//リクエストパラメータを取得
			String logout = "logout";
			request.setAttribute("logout", logout);
			//requestからセッションを取得する
			HttpSession sess = request.getSession();
			//セッションにある全ての要素名を取得する
			Enumeration vals = sess.getAttributeNames();
			//取得した要素名をループ処理で全て削除する
			while(vals.hasMoreElements()){
			  String nm = (String)vals.nextElement();
			  sess.removeAttribute(nm);
			}

			//フォワード
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		}
		
	}

}
