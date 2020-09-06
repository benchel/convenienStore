<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>	
<div class="paging_div">
	<ul class="pagination">
		<!-- pageMaker.prev가 존재하면 if문 안의 문장을 수행-->
		<c:if test="${pageMaker.prev}">
			<li class="paginumber_btn pre"><a
				href="${pageMaker.startPage -1}">이전</a></li>
		</c:if>
		<!-- Page Numbering -->
		<c:forEach var="num" begin="${pageMaker.startPage}"
			end="${pageMaker.endPage}">
			<!-- 삼항연산자 (판별식 ? true일 때의 결과값 : false일때의 값)
				  현재 조회하고 있는 페이지와 num이 동일하면 active, 동일하지 않으면 "" [a:active : 클릭 했을 때 링크 상태]
				  -->
			<li
				class="paginumber_btn ${pageMaker.cri.pageNum == num ? 'active':''} ">
				<a href="${num}">${num}</a>
			</li>
		</c:forEach>
		<!-- pageMaker.next가 존재하면 if문 안의 문장을 수행 -->
		<c:if test="${pageMaker.next}">
			<li class="paginumber_btn next"><a
				href="${pageMaker.endPage + 1}">다음</a></li>
		</c:if>
	</ul>
	<form id='actionForm' action="/post/postList" method='get'>
		<input type="hidden" name='pageNum' value='${pageMaker.cri.pageNum}'>
		<input type="hidden" name='amount' value='${pageMaker.cri.amount}'>
		<input type="hidden" name='keyword' value='${pageMaker.cri.keyword}'>
	</form>
</div>
<div class="searchAndSelectlocal">
	<form id='searchForm' action="/post/postList" method='get'>
		<input type="text" class="inputText" name='keyword' value="${pageMaker.cri.keyword}">
		<input type="submit" value="검색" class="searchbtn">
	</form>
</div>