<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//リクエストスコープから取得
String message = (String)request.getAttribute("message");
String logout = (String)request.getAttribute("logout");
if(logout != null) {
	session.removeAttribute("member");
	logout = "ログアウトしました";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<form action="LoginServlet" method="post">
	ID:	 	<input type = "text" name = "ID"><br>
	パスワード: <input type = "password" name = "パスワード"><br>
	<input type = "submit" value = "ログイン"><br>
</form>
<% if(message != null) {%>
	<%= message %>
<%} %>
<% if(logout != null) {%>
	<%= logout %>
<%} %>


</body>
</html>