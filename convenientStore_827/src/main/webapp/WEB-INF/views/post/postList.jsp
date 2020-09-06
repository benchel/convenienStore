<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/pListStyle.css">
<title>reviewPostList</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%@include file="../includes/commonheader.jsp" %>
<body>
	<div class="wrap">
		<div class="ctrlVar">
			<button class="writebtn" onclick="moveWritePage();">작성</button>
		</div>
		<section id="listSection">
			<table class="listTable">
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>등록일</td>
					<td>수정일</td>
				</tr>
				<c:forEach items="${plist}" var="post">
					<tr>
						<td><c:out value="${post.hierarchyId}"></c:out></td>
						<!-- postId를 Get방식으로 넘겨준다. -->
						<td>
							<a href='/post/getReviewPost?hierarchyId=<c:out value="${post.hierarchyId}"/>'>
								<c:out value="${post.title}"/>
							</a>
						</td>
						<td><c:out value="${post.writer}"></c:out></td>
						<td><c:out value="${post.regdate}"></c:out></td>
						<td><c:out value="${post.updateDate}"></c:out></td>
					</tr>
				</c:forEach>
			</table>
		</section>
<%@include file="../includes/pagingAndSearch.jsp"%>		
	</div>
<%@include file="../includes/informWindow.jsp" %>
</body>
<script>
	$(document).ready(function() {
		var result = '<c:out value="${ReviewPostId}"/>';

		checkInformWindow(result);
		
		history.replaceState({}, null, null);
		
	});

	function checkInformWindow(result) {

		if (result === '' || result === " " || history.state) {
			return;
		}

		if (result !== '' || result !== " ") {
			$(".informLayer").show();

			$(".informContent").html("게시물" + " "+result + "번이 등록되었습니다.");

			$(".informbtn").on("click", function() {
				$(".informLayer").hide();
			});
		}
	}

	function moveWritePage() {
		location.href = "postWritePage";
	}
</script>
<script type="text/javascript" src="/resources/script/paging.js"></script>
</html>