<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link rel="stylesheet" href="/resources/css/myPageStyle.css"> 
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/resources/script/prefer.js"></script>
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
        </header>    
        <section id="leftSection">
            <div class="profile">
                <ul class="userInfo">
                    <li class="userId">
                    	<label><sec:authentication property='principal.party.userId'/>님</label>
                    </li>
                    <li>
                    	<label>환영합니다.</label>
                    </li>
                    <li class="userjoinDate">
                    	<label>가입일</label>
                    </li>
                    <li>
                    	<label><sec:authentication property='principal.party.partyInfo.joindate'/></label>
                    </li>
                    <li class="userAttendance">
                    	<label>출석일수</label>
                    	<label>1일</label>
                    </li>
                    <li class="userlogout">
                    	<label><a href="#" onclick="document.getElementById('logoutForm').submit();">로그아웃</a></label>
                    </li>
                    <li>
            		<form id="logoutForm" action="<c:url value='/customLogout'/>" method='post'>
            			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            		</form>
            		<li>
                </ul>
            </div>
            <nav id="sideMenubar">
                <ul class="mainMenu">
                    <li><a href="#" class="pfrListlink">선호상품관리</a></li>
                    <li><a href="#">작성글관리</a></li>
                    <li><a href="#">개인정보관리</a>
                        <ul class="subMenu">
                            <li><a href="#">개인정보수정</a></li>
                            <li><a href="#">회원탈퇴</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </section>
        <section id="rightSection">
            <div class="preferList">
            </div>
        </section>
    </div>
    <div class="recommendListwindow">
    </div>
</body>
<script type="text/javascript">
//현재 로그인 중인 userId
var userId = '<c:out value="${pageContext.request.userPrincipal.name}"/>';

$(document).ready(function() {
// --------------------------------- 선호상품 목록 조회

	//선호상품이 출력될 곳
	var preferListArea = $(".preferList");
	
	// 선호상품관리 링크를 누르면 
	$(".pfrListlink").on("click", function(){
		showPreferList(userId);
	});
	// 선호상품 목록 조회
	function showPreferList(userId) {
		preferService.getPreferList(
			userId,
			function(listPrefer) {
				var preferArea = "";
				for(var i = 0; i < listPrefer.length; i++) {
					preferArea += "<ul class='preferEA'>";
					preferArea += "<li class='prfStoreBrand'>편의점브랜드</li>";
					preferArea += "<li class='prfStoreBrandText'>"+listPrefer[i].store.storeBrand+"</li>";
					preferArea += "<li class='prfAssort'>상품분류</li>";
					preferArea += "<li class='prfAssortText' id=liTagPrdAssort"+i+">"+listPrefer[i].preferProdc.prdAssort+"</li>";
					preferArea += "<li class='prfName'>상품이름</li>";
					preferArea += "<li class='prfNameText' id=liTagPrdName"+i+">"+listPrefer[i].preferProdc.prdName+"</li>";
					preferArea += "<li class='prfPrice'>상품가격</li>";
					preferArea += "<li class='prfPriceText'>"+listPrefer[i].preferProdc.prdPrice+"</li>";
					preferArea += "<button class='deletePrfBtn'>삭제</button>";
					preferArea += "<button class='recommendBtn' onclick='recommdbtnClick("+i+");'>추천목록보기</button>";
					preferArea += "</ul>";
				}
				// for end
				if (preferArea.length != 0)
					preferListArea.html(preferArea);		
			} // function(listPrefer)
	); // preferService.getPreferList end
	} // showPreferList(userId) end
	
});

//--------------------------------- 추천목록 조회	

// 추천목록이 출력될 곳
var recommendListArea = $(".recommendListwindow");

// 추천 목록 조회 함수
function recommdbtnClick(index) {
	
	// 추천목록의 바탕이 될 선호상품의 분류와 이름을 가지고온다.
	var prfNameForRecmdList = $("#liTagPrdName"+index).text();
	var prfAssortForRecmdList = $("#liTagPrdAssort"+index).text();
	
	//AJAX
	preferService.getRcmdList(
		    prefer = {
	        userId:userId,
	       	preferProdc : {
	       		prdAssort:prfAssortForRecmdList,
	       		prdName:prfNameForRecmdList
	       		}
	       },
		function(recommendList){
			var recommendArea = "";
			recommendArea +="<h2 class='listword'>추천목록</h2>";
			for(var i = 0; i < recommendList.length; i++){
				recommendArea +="<ul class='rcmdEA'>";
				recommendArea +="<li class='rcmdStoreBrand'>편의점브랜드</li>";   
				recommendArea +="<li class='rcmdStoreBrandText'>"+recommendList[i].store.storeBrand+"</li>";  
				recommendArea +="<li class='rcmdAssort'>상품분류</li>";  
				recommendArea +="<li class='rcmdAssortText'>"+recommendList[i].preferProdc.prdAssort+"</li>";  
				recommendArea +="<br>"
				recommendArea +="<li class='rcmdName'>상품이름</li>"; 
				recommendArea +="<li class='rcmdNameText'>"+recommendList[i].preferProdc.prdName+"</li>"; 
				recommendArea +="<li class='rcmdPrice'>상품가격</li>"; 
				recommendArea +="<li class='rcmdPriceText'>"+recommendList[i].preferProdc.prdPrice+"</li>";    
				recommendArea +="</ul>";				
			}
			recommendArea +="<button class='closercmdBtn' onclick='closeRecommend();'>닫기</button>";
			// for end
			if(recommendArea.length != 0){
				recommendListArea.html(recommendArea);
				$(".recommendListwindow").css("display","block");
			}
			// if end
		} // function(recommendList) end
	); // preferService.getRcmdList end
}

function closeRecommend() {
	$(".recommendListwindow").css("display","none");
}

</script>
</html>