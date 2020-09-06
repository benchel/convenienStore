<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="/resources/css/loginStyle.css" rel="stylesheet" >
</head>
<body>
    <div class="wrap">
        <header id="header">
        	<h2><c:out value="${error}"></c:out></h2>
            <div class="logo"><a href="/index">편의점 먹거리 탐색</a></div>
        </header>
        <section id="contents">
            <form id="loginForm" method='post' action='/login'>
                <input type="text" class="loginId" name="username" placeholder="&emsp;아이디 입력">
                <input type="password" class="loginPw" name="password" placeholder="&emsp;비밀번호 입력">
                <input type="submit" class="loginbtn" value="로그인">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <div class="userjoinnav">      
                <span class="userjoin"><b>아직 회원이 아니신가요?</b></span>
                <button class="joinbtn">회원가입</button>
            </div>
        </section>
    </div>
</body>
</html>