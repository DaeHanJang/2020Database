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
<form action="in" name="fo" method="get">
사용자 ID : <c:out value="${id }"/><br>
비밀 번호 : <input type="password" name="dtpwd" /><br>
<input type="hidden" name="dtid" value="${id }"/>
           <input type="submit" name="pcbtn" value="수정">           
</form>
</body>
</html>