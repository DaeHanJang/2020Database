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
<h3>본인 여부 확인</h3>
<hr>
<c:if test="${msg == 'logf' }">
	존재하지 않는 id거나 비밀번호가 맞지않습니다.
</c:if>
<c:if test="${msg == 'joinf' }">
	존재하는 id입니다.
</c:if>
<c:if test="${msg == 'joint' }">
	가입하셨습니다.
</c:if>
<c:if test="${msg == 'pcf' }">
	비밀번호가 일치하지 않습니다.
</c:if>
</body>
</html>