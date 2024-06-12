<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "himedia.GuestBookDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset = "UTF-8">
<title>Data Insert</title>
</head>
<body>

	<%
	
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String content = request.getParameter("content");
		
		if (name != null && password != null && content != null) {
			GuestBookDao dao = new GuestBookDao();
			dao.insert(name, password, content);
	%>
	<script>
	alert("게시물이 등록되었습니다.");
	window.location.href = "index.jsp";
	</script>
	<%
		} else {
	%>
	<script>
		alert("모든 필드를 입력하세요.");
		window.history.back();
	</script>
	
	<% } %>

</body>
</html>