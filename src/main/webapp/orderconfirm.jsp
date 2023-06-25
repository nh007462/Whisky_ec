<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List"
	import="java.util.ArrayList" import="dto.Whisky" import="dto.CartItem"%>
<%
// セッションスコープから取得
List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
int total = 0;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入確認画面</title>
</head>
<body>

	<jsp:include page="header.jsp" />

	<%
	if (cart.size() != 0) {
		for (CartItem cartitem : cart) {
	%>
	ID:
	<%=cartitem.getWhisky().getId()%>
	名前:
	<%=cartitem.getWhisky().getName()%>
	値段:
	<%=String.format("%,d", cartitem.getWhisky().getPrice())%>円 個数:
	<%=cartitem.getAmount()%>
	小計:
	<%=String.format("%,d", cartitem.getWhisky().getPrice() * cartitem.getAmount())%>円
	<br>
	<%
	total += cartitem.getWhisky().getPrice() * cartitem.getAmount();
	%>
	<%
	}
	%>
	合計:
	<%=String.format("%,d", total)%>
	円
	<br>
	<br>
	<br> 購入しますか？
	<br>
	<form action="OrderCompleteServlet" method="post">
		<input type="hidden" name="total" value=<%= total %>>
		<input type="submit" value="購入確定"><br>
	</form>
	<%
	} else {
	%>
	カートの中身は0です。
	<%
	}
	%>



</body>
</html>