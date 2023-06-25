<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dto.Member"%>
<%
//リクエストスコープから取得
Member member = (Member)session.getAttribute("member");
%>

<h1><a href = "index.html">ウヰスキーホーム</a>  <a href = "HomeServlet?request=1">検索ホーム</a>
<% if(member == null) { %>
<a href = "HomeServlet?request=2">ログイン</a>
<%} else {%>
<a href = "cartconfirm.jsp">買い物かご</a>
<a href = "HistoryServlet2">注文履歴</a>
<form action="HistoryServlet2" method="post">
	<input type = "submit" value = "注文履歴">
</form>
<a href = "HomeServlet?request=3">ログアウト</a>
<%} %>

</h1>



<form action="SearchServlet" method="get">
	<input type = "text" name = "word">
	<input type = "submit" value = "検索"><br><br><br>
</form>