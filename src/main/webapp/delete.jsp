<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "himedia.GuestBookDao"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>deleteWithCheck</title>
</head>
<body>
<%

	String no = request.getParameter("no");
	String password = request.getParameter("password");
	
	if (no != null && password != null) {
		GuestBookDao dao = new GuestBookDao();
		boolean isDeleted = dao.deleteWithPasswordCheck(Integer.parseInt(no),password);
		
		if (isDeleted) {
%>
			<script>
			
			alert("삭제되었습니다.");
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
<%
		}
%>
		
	</body>
	</html>