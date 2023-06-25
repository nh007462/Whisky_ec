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

import dao.OrderDAO;
import dao.OrderDetailDAO;
import dto.Member;
import dto.Order;
import dto.OrderDetail;

@WebServlet("/HistoryServlet2")
public class HistoryServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//セッションから会員情報を取得
			HttpSession session = request.getSession();
			Member member = (Member) session.getAttribute("member");

			//データベースから注文履歴を取得
			OrderDAO orderdao = new OrderDAO();
			OrderDetailDAO oddao = new OrderDetailDAO();
			List<Order> orderlist = orderdao.selectAll(member.getId());
			List<OrderDetail> orderdetaillist = new ArrayList<>();
			for (Order order : orderlist) {
				orderdetaillist.add(oddao.select(order.getId()));
			}

			//セッションに格納
			session.setAttribute("orderlist", orderlist);
			session.setAttribute("orderdetaillist", orderdetaillist);

			//フォワード
			response.sendRedirect("history.jsp");

		} catch (SQLException e) {
			//フォワード
			request.getRequestDispatcher("WEB-INF/SystemError.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
