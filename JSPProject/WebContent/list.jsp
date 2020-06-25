<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>    
        #visual .content-container{	
            height:inherit;
            display:flex; 
            align-items: center;
        }
    </style>
</head>

<body>
    <!-- --------------------------- <body> --------------------------------------- -->
	<div id="body">
		<div class="content-container clearfix">
		<main class="main">
			<h2 class="main title">DBP 학생 목록</h2>
			
			<div class="notice margin-top">				
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>ID</th>
							<th>&nbsp;이름</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="n" begin="1" end="${size }">
						<tr>
							<td>${n }</td>
							<td><a href="detail.jsp?id=${id[n-1] }&name=${name[n-1] }&tel=${tel[n-1] }&email=${email[n-1] }&dept=${dept[n-1] }&gender=${gender[n-1] }&birth=${birth[n-1] }&introduction=${introduction[n-1] }"><c:out value="${id[n-1] }"/> </a></td>
							<td>&nbsp;&nbsp; <c:out value="${name[n-1] }"/></td>												
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div class="search-form margin-top first align-right">
			<br>	
				<form action="in" method=get>
					<fieldset>
						<legend class="hidden">학생 분류</legend>
						<label class="hidden">학과</label>
						<select name="f">
							<option  value="default">전체</option>
							<option  value="Computer">컴퓨터공학부</option>
							<option  value="Communications" ">정보통신학부</option>
							<option  value="Contents">IT콘텐츠학과</option>
							<option  value="Korean">국어국문학과</option>							
						</select> 
						<input type="submit" name="listbtn" value="검색" />
					</fieldset>
				</form>
			</div>
		</main>
		
			
		</div>
	</div>

</body>
    
</html>