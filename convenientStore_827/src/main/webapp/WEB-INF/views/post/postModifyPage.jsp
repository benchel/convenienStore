<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ReviewPostModify</title>
<link href="/resources/css/postWriteStyle.css" rel="stylesheet">
</head>
<body>
    <div class="wrap">
        <section id="contents">
           
            <form action="postModify" method="post" id="postInputForm" accept-charset="utf-8">
            
                <label class="title">번호</label>
                <input type="text" name="hierarchyId" class="pno" value="${post.hierarchyId}" readonly="readonly">
               
               	<label class="write">글쓴이</label>
                <input type="text" readonly="readonly" name="write"
                 class="InputWrite" value="${post.writer}">
                                
                <label class="title">제목</label>
                <input type="text" name="title" class="Inputtitle" 
                value="${post.title}">
                
                <div class="reviewTarget">
                   
                    <label class="ProductReview">리뷰상품</label>
                    <div class="ProductInfo">
                    <label class="storebrand">편의점브랜드명</label>
                    
                    <select name="storeId" class="SelectStorebrand">
                    <!-- store 테이블의 데이터를 가져온다. -->
	                    <c:forEach items="${storelist}" var="store">
	                        <option value="${store.storeId}">
	                        	<c:out value="${store.storeBrand}"></c:out>
	                        </option>
	                    </c:forEach>
                    </select>
                    
                    <label class="prdAssort">상품분류</label>
                    <select name="prdAssort" class="SelectprdAssort">
                        <option value="간편식">간편식</option>
                        <option value="음료">음료</option>
                        <option value="과자">과자</option>
                        <option value="빵">빵</option>
                        <option value="아이스크림">아이스크림</option>
                    </select>
                    
                    </div>           
                    <label class="prdName">상품명</label>
                    <input type="text" name="prdName" class="InputprdName" 
                    value="${post.reviewTarget.prdName}">
                
                    <label class="prdPrice">상품가격</label>
                    <input type="text" name="prdPrice" class="InputprtPrice" 
                    value="${post.reviewTarget.prdPrice}">
                    
                    <label class="prdRate">상품평</label>
                    <ul class="radioPrdRate">
                        <li>
                            <input type="radio" name="prdRate" value=1>&nbsp;1&nbsp;
                        </li>
                        <li>
                            <input type="radio" name="prdRate" 
                            value=2>&nbsp;2&nbsp;
                        </li>
                        <li>
                            <input type="radio" name="prdRate" 
                            value=3>&nbsp;3&nbsp;
                        </li>
                        <li>
                            <input type="radio" name="prdRate" 
                            value=4>&nbsp;4&nbsp;
                        </li>
                        <li>
                            <input type="radio" name="prdRate" 
                            value=5>&nbsp;5&nbsp;
                        </li>
                    </ul>
                </div>
                <label class="content">본문</label>
                <textarea name="content" class="InputText"><c:out value="${post.content}"/></textarea>
                <!-- 정보를 암호화 -->
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <!-- 로그인과 관련된 정보 -->
                <sec:authentication property="principal" var="userinfo"/>
                
                <sec:authorize access="isAuthenticated()">
                <!-- 로그인한 사용자의 id와 게시글 작성자id를 비교한다 -->
                <c:if test="${userinfo.username eq post.writer}">
					<input type="submit" value="등록" class="regbtn">
                </c:if>
                </sec:authorize>
            </form>
        </section>
    </div>
</body>
</html>