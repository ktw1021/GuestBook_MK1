<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "himedia.GuestBookDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update</title>
</head>
<body>

<%

	String no = request.getParameter("no");
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String content = request.getParameter("content");
	
	if (no != null && name != null && password != null && content != null) {
		
		GuestBookDao dao = new GuestBookDao();
		boolean isUpdated = dao.updateEntry(Integer.parseInt(no), name, password, content);
		
		if (isUpdated) {
%>
		<script>
			alert("수정되었습니다.");
			window.location.href = "index.jsp";
		</script>
<%
		} else {
	
%>
		<script>
			alert("비밀번호가 일치하지 않습니다.");
			window.history.back();
		</script>
<%
		}
	} else {
		 
%>
		<script>
			alert("잘못된 접근입니다.");
			window.history.back();
		</script>
<% } %>
</body>
</html>