package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.AddCartLogic;
import dao.WhiskyDAO;
import dto.CartItem;
import dto.Member;
import dto.Whisky;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//リクエストパラメータを取得
			request.setCharacterEncoding("UTF-8");
			String blank = request.getParameter("amount");
			if(blank.equals("")) {
				blank = "0";
			}
			
			int amount = Integer.parseInt(blank);
			Integer id = Integer.parseInt(request.getParameter("ID"));
			int type = Integer.parseInt(request.getParameter("type"));
			
			//セッションパラメータを取得
			HttpSession session = request.getSession();
			Member member = (Member)session.getAttribute("member");
			
			//DAOからwhiskyのデータを取得
			WhiskyDAO dao = new WhiskyDAO();
			Whisky whisky = dao.select(id);
			
			if(type == 0) {
				//カートアイテムに追加依頼
				//セッションからカートを確認
				List<CartItem> cart = (List<CartItem>)session.getAttribute("cart");
				if(cart == null) {
					cart = new ArrayList<>();
				}
				
				//カート追加を依頼
				AddCartLogic logic = new AddCartLogic();
				cart = logic.process(id, amount, cart);
				
				//カートをセッションスコープに設定
				session.setAttribute("cart", cart);
				//画面遷移しない
				request.setAttribute("whisky", whisky);
				request.getRequestDispatcher("WEB-INF/whiskydetail.jsp").forward(request,  response);
			} else {
				//カートアイテムに追加依頼
				//セッションからカートを確認
				List<CartItem> cart = (List<CartItem>)session.getAttribute("cart");
				if(cart == null) {
					cart = new ArrayList<>();
				}
				
				//カート追加を依頼
				AddCartLogic logic = new AddCartLogic();
				cart = logic.process(id, amount, cart);
				
				//カートをセッションスコープに設定
				session.setAttribute("cart", cart);
				//フォワード
				response.sendRedirect("orderconfirm.jsp");
			}
			
		} catch(SQLException e) {
			//フォワード
			request.getRequestDispatcher("WEB-INF/SystemError.jsp").forward(request,  response);
		}
	}

}
