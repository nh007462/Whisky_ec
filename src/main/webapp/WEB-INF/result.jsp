<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List" import="dto.Whisky" %>
<%
//リクエストスコープから取得
List<Whisky> whiskylist = (List<Whisky>) request.getAttribute("whisky");
String word = (String)session.getAttribute("word");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果</title>
</head>
<body>

<jsp:include page="header.jsp"/>

	検索結果一覧
	<br>
	<br>

	<%
	if (whiskylist.size() != 0) {
		for (Whisky whisky : whiskylist) {
	%>
	<a href="DetailServlet?id=<%=whisky.getId()%>"><%=whisky.getName()%></a>
	<%=String.format("%,d", whisky.getPrice())%>円
	<br>
	<%
	}
	} else {
	%>
	指定された商品は存在しませんでした
	<%
	}
	%>


</body>
</html>