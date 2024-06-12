<%@ page import ="himedia.GuestBookDao" %>
<%@ page import = "java.util.List" %>
<%@ page import = "java.util.Arrays" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>

<script type = "text/javascript"> 
	function deleteData(no) {
		if (confirm("정말로 삭제합니까?")) {
			let password = prompt("비밀번호를 입력하세요:");
			if (password != null) {
			window.location.href = "delete.jsp?no=" + no + "&password=" + encodeURIComponent(password);
			}
		}
	}
</script>
</head>
<body>
	<form action="add.jsp" method="post">
	
	<table border=1 width=500>
		<tr>
			<td>이름</td><td><input type="text" name="name"></td>
			<td>비밀번호</td><td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td colspan=4><textarea name="content" cols=60 rows=5></textarea></td>
		</tr>
		<tr>
			<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
		</tr>
	</table>
	</form>
	<br/>

	<table width=510 border=1>
	
	<% 
		GuestBookDao dao = new GuestBookDao();
		List<String[]> list = dao.getList();
		for (String [] entry : list) {
		
	%>
		<tr>
			<td>[<%= entry[0] %>]</td>
			<td><%= entry[1] %></td>
			<td><%= entry[2] %></td>
			<td>
			<a href = "javascript:deleteData('<%= entry[0]  %>')">삭제</a>
			<a href = "edit.jsp?no=<%= entry[0] %>">수정</a>
			</td>
		</tr>
		<tr>
			<td colspan=4><%= entry[3] %></td>
		</tr>
		
		<% } %>
	</table>
        <br/>
</body>
</html>