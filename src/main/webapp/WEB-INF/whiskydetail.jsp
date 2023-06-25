<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dto.Whisky" import="dto.Member" %>
<%
// リクエストスコープから取得
Whisky whisky = (Whisky)request.getAttribute("whisky");
//セッションスコープから取得
String word = (String)session.getAttribute("word");
Member member = (Member)session.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>詳細画面</title>
</head>
<body>

<jsp:include page="header.jsp"/>


<h2><%= whisky.getName() %></h2>
ID: <%= whisky.getId() %><br>
価格: <%= whisky.getPrice() %>円<br>
在庫: <%= whisky.getStock() %>個<br>
蒸留所ID: <%= whisky.getDistid() %><br>
説明: <%= whisky.getDiscription() %><br>
<br>

<% if(member != null) {%>
<form action="OrderServlet" method="post">
	<input type = "text" name = "amount">
	<input type = "hidden" name = "ID" value = <%= whisky.getId() %>>
	<input type = "hidden" name = "type" value = "0">
	<input type = "submit" value = "かごに入れる"><br>
</form>

<form action="OrderServlet" method="post">
	<input type = "text" name = "amount">
	<input type = "hidden" name = "ID" value = <%= whisky.getId() %>>
	<input type = "hidden" name = "type" value = "1">
	<input type = "submit" value = "購入する"><br>
</form>

<a href="SearchServlet?word=<%=word%>">戻る</a>
<%} %>

</body>
</html>