<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "himedia.GuestBookDao" %>
<%@ page import = "java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit</title>
</head>
<body>

<%

	String no = request.getParameter("no");
	GuestBookDao dao = new GuestBookDao();
	Map<String, String> entry = dao.getEntry(Integer.parseInt(no));
%>
<form action = "update.jsp" method = "post">
	<input type = "hidden" name = "no" value = "<%= entry.get("no") %>">
	<table border=1 width = 500>
		<tr>
			<td>이름</td><td><input type = "text" name = "name" value = "<%= entry.get("name") %>"></td>
		</tr>
		<tr>
			<td>비밀번호</td><td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td colspan=4><textarea name="content" cols=60 rows=5><%= entry.get("content") %></textarea></td>
		</tr>
		<tr>
			<td colspan=4 align=right><input type="submit" VALUE=" 수정 "></td>
		</tr>
	</table>
</form>

</body>
</html>