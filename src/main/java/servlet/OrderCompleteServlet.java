package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import dto.CartItem;
import dto.Member;
import dto.OrderDetail;

@WebServlet("/OrderCompleteServlet")
public class OrderCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//リクエストパラメータを取得
			request.setCharacterEncoding("UTF-8");
			int total = Integer.parseInt(request.getParameter("total"));

			//セッションパラメータを取得
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
			Member member = (Member)session.getAttribute("member");

			//注文テーブルからIDを取得して次の番号を注文IDに設定
			OrderDAO orderdao = new OrderDAO();
			List<Integer> orderidlist = orderdao.idreturn();
			
			Integer neworderid = Collections.max(orderidlist) + 1;

			//注文テーブルにデータ格納依頼
			orderdao.insert(member, neworderid, total);

			//注文詳細テーブルにデータを格納依頼
			OrderDetailDAO oddao = new OrderDetailDAO();
			OrderDetail orderdetail = null;
			for (CartItem cartitem : cart) {
				orderdetail = new OrderDetail(neworderid, cartitem.getWhisky().getId(), cartitem.getWhisky().getPrice(),
						cartitem.getAmount());
				oddao.insert(orderdetail);
			}
			
			session.removeAttribute("cart");
			
			//フォワード
			response.sendRedirect("ordercomplete.jsp");

		} catch (SQLException e) {
			//フォワード
			request.getRequestDispatcher("WEB-INF/SystemError.jsp").forward(request, response);
		}
	}

}
