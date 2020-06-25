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
	<%
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String tel = request.getParameter("tel");
	String email = request.getParameter("email");
	String dept = request.getParameter("dept");
	String gender = request.getParameter("gender");
	String birth = request.getParameter("birth");
	String introduction = request.getParameter("introduction");
	String mbid = request.getParameter("mbid");
	String mbname = request.getParameter("mbname");
	%>

    <div id="body">
    
		<div class="content-container clearfix">
		<form action="in" method=get>
		<main class="main">
			<h2 class="main title">DBP 학생 정보</h2>
			
			<div class="notice margin-top">
				<c:choose>
				<c:when test="${mbid != null }">
				ID : <%=mbid %> <br/>
				<input type="hidden" name="dtid" value="<%=mbid %>"/>
				</c:when>
				<c:otherwise>
				ID : <%=id %> <br/>
				<input type="hidden" name="dtid" value="<%=id %>"/>
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${mbname != null }">
				이름 : <%=mbname %> <br/>
				</c:when>
				<c:otherwise>
				이름 : <%=name %> <br/>
				</c:otherwise>
				</c:choose>		
				전화번호 : <%=tel %> <br/>
				이메일 : <%=email %> <br/>
				학부 : <%=dept %> <br/>
				성별 : <%=gender %> <br/>
				태어난 계절 : <%=birth %> <br/>
				자기소개 : <%=introduction %> <br/>
			</div>
			
			<div class="search-form margin-top first align-right">		
			<input type="submit" name="dtbtn" value="목록" />
			<input type="submit" name="dtbtn" value="수정" />		
			</div>
		</main>
		</form>	
		</div>
	</div>

    </body>
    
</html>