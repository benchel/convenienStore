<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/commonheaderStyle.css">
</head>
<body>
<div class="wrap">
    <header id="header">
        <div class="logo"><a href='/index'>편의점 먹거리 탐색</a></div>
        <nav class="navigation">
            <ul class="menubar">
                <li><a href='/notice/noticeList'>공지사항</a></li>
                <li><a href='/post/postList'>리뷰게시판</a></li>
            </ul>
        </nav>
        <div class="loginUI">
            <ul class="loginul">
            <c:if test="${pageContext.request.userPrincipal.name != null}">
            	<li>
            		<a href="/myPage"><sec:authentication property='principal.party.userId'/></a>
					
					<!-- 클릭하면 폼에서 설정한 url로 submit() -->
					<a href="#" onclick="document.getElementById('logoutForm').submit();">로그아웃</a>            		
            		<form id="logoutForm" action="<c:url value='/customLogout'/>" method='post'>
            			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            		</form>
            	</li>
            </c:if>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <li><a href='/customLogin'>로그인</a></li>
                <li><a href='/joinInSite'>회원가입</a></li>
            </c:if>
            </ul>
        </div>
    </header>
</div>
</body>
</html>