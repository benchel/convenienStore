<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<link href="../resources/css/JoinInSiteStyle.css" rel="stylesheet" >
</head>
<body>
    <div class="wrap">
        <header id="header">
            <div class="logo">편의점 먹거리 탐색</div>
        </header>
        <section id="contents">
           <span class="phrase">
               아래의 빈 칸을 채운 후 <b>회원가입</b> 버튼을 눌러주세요.
            </span>
            <!-- 기준선 -->
            <hr class="datumLine">
            <form id="joinForm" method="post" action="/joinUser">
                <!-- 아이디 입력 -->
                <ul class="userInput">
                    <li>
                        <label class="joinIdLabel">아이디</label>
                        <input type="text" name="userId" class="joinId">
                    </li>
                    <!-- 비밀번호 입력 -->
                    <li>
                        <label class="joinPwdLabel">비밀번호</label>
                        <input type="password" name="userPwd" class="joinPwd">
                    </li>
                </ul>
                <ul class="userInfo">  
                    <!-- 회원정보 -->
                    <li>
                        <label class="joinybLabel">생년월일 (예) 1945년 8월 15일</label>
                        <input type="text" readonly value="450815" size="6" class="example">
                    </li>
                    <li>
                        <input type="text" name="partyInfo.yearOfBirth" size="6" class="joinyb" placeholder="YYMMDD">
                    </li>
                    <li>
                        <label class="joinEmailLabel">이메일</label>
                        <input type="email" name="partyInfo.email" class="joinEmail" placeholder="abc@abc.com">
                        <!-- 권한 -->
                        <input type="hidden" name="userRole" value="ROLE_USER">
                    </li>
                </ul>
                <!-- submint button -->
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="submit" value="회원가입" class="joinbtn">
            </form>
        </section>
    </div>
</body>
</html>