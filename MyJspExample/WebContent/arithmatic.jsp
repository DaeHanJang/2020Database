<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	int start = 1;
	int end = 10;
	int step = 2;
	%>
	<c:forEach var="i" begin="<%=start %>" end="<%=end %>" step="<%=step %>">
		 ${i }
	</c:forEach>
</body>
</html>