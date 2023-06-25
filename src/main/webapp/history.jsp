<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List"
	import="java.util.ArrayList" import="dto.Whisky" import="dto.CartItem"
	import="dto.Order" import="dto.OrderDetail" import="dto.Member"
	import="java.util.Date" import="dao.WhiskyDAO" %>
<%
// セッションスコープから取得
List<Order> orderlist = (List<Order>)session.getAttribute("orderlist");
List<OrderDetail> orderdetaillist = (List<OrderDetail>)session.getAttribute("orderdetaillist");
Member member = (Member)session.getAttribute("member");
WhiskyDAO dao = new WhiskyDAO();
Whisky whisky;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文履歴</title>
</head>
<body>

	注文履歴
	<br>
	<br>

	<% for (Order order : orderlist) { 
%>
	注文ID:
	<%= order.getId() %><br>
	会員ID:
	<%= order.getMemberId() %><br>
	送料:
	<%= order.getPostage() %><br>
	商品総計:
	<%= order.getTotal() %><br>
	支払い額総計:
	<%= order.getPostage() + order.getTotal() %><br>
	注文日:
	<%= order.getDate() %><br>
	注文ステータス
	<%= order.getStatus() %><br>

	<% for (OrderDetail orderdetail : orderdetaillist) {
		if(orderdetail.getOrderId() == order.getId()) { 
				whisky = dao.select(orderdetail.getItemId());
		%>
	商品名:
	<%= whisky.getName() %><br>
	個数:
	<%= orderdetail.getAmount() %><br>
	値段:
	<%= orderdetail.getPrice() %><br>
	小計:
	<%= orderdetail.getAmount() * orderdetail.getPrice() %><br><br><br>
	<% } %>
	<% } %>
	<% } %>




</body>
</html>