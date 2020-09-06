<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/pListStyle.css">
<title>noticeList</title>
</head>
<%@include file="../includes/commonheader.jsp" %>
<body>
	<div class="wrap">
		<section id="listSection">
			<table class="listTable">
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>등록일</td>
					<td>수정일</td>
				</tr>
				<c:forEach items="${nlist}" var="notice">
					<tr>
						<td><c:out value="${notice.hierarchyId}"></c:out></td>
						<td><c:out value="${notice.title}"></c:out></td>
						<td><c:out value="${notice.writer}"></c:out></td>
						<td><c:out value="${notice.regdate}"></c:out></td>
						<td><c:out value="${notice.updateDate}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
		</section>
		<div class="paging_div">
			<ul class="pagination">
				<!-- pageMaker.prev가 존재하면 if문 안의 문장을 수행-->
				<c:if test="${pageMaker.prev}">
					<li class="paginumber_btn pre">
						<a href="${pageMaker.startPage -1}">이전</a>
					</li>
				</c:if>
				<!-- Page Numbering -->
				<c:forEach var="num" begin="${pageMaker.startPage}"
				 end="${pageMaker.endPage}">
				 <!-- 삼항연산자 (판별식 ? true일 때의 결과값 : false일때의 값)
				  현재 조회하고 있는 페이지와 num이 동일하면 active, 동일하지 않으면 "" [a:active : 클릭 했을 때 링크 상태]
				  -->
					<li class="paginumber_btn ${pageMaker.cri.pageNum == num ? 'active':''} ">
						<a href="${num}">${num}</a>
					</li>
				</c:forEach>
				<!-- pageMaker.next가 존재하면 if문 안의 문장을 수행 -->
				<c:if test="${pageMaker.next}">
					<li class="paginumber_btn next">
						<a href="${pageMaker.endPage + 1}">다음</a>
					</li>
				</c:if>
			</ul>
			<form id='actionForm' action="/notice/noticeList" method='get'>
				<input type="hidden" name='pageNum' value='${pageMaker.cri.pageNum}'> 
				<input type="hidden" name='amount' value='${pageMaker.cri.amount}'>
			</form>
		</div>		
		<div class="searchAndSelectlocal">
			<form>
				<input type="text" class="inputText">
				<button class="searchbtn">검색</button>
			</form>
		</div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	function moveWritePage() {
		location.href = "postWritePage";
	}
</script>
<script type="text/javascript" src="/resources/script/paging.js"></script>
</html>