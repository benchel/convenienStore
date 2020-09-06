<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ReviewPostWrite</title>
<link href="/resources/css/getReviewPostStyle.css" rel="stylesheet">
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/resources/script/reply.js"></script>
<script src="/resources/script/prefer.js"></script>
<body>
    <div class="wrap">
        <section id="contents">
           
            <form action="postWrite" method="post" id="postInputForm" accept-charset="utf-8">
               
                <label class="title">번호</label>
                <input type="text" name="hierarchyId" class="pno" value="${post.hierarchyId}" readonly="readonly">             
               
                <label class="title">제목</label>
                <input type="text" name="title" class="Inputtitle" value="${post.title}" readonly="readonly">

                <label class="write">글쓴이</label>
                <input type="text" name="write" class="InputWrite" value="${post.writer}" readonly="readonly">                
                
                <label class="regdate">등록일</label>
                <input type="text" name="regdate" class="Inputregdate" value="${post.regdate}" readonly="readonly">                
                                     
                <label class="updateDate">수정일</label>
                <input type="text" name="updateDate" class="InputupdateDate" value="${post.updateDate}" readonly="readonly">                
                
                <div class="reviewTarget">
                   
                    <label class="ProductReview">리뷰상품</label>
                    <div class="ProductInfo">
                    
                    <input type="hidden" name="storeId" class="SelectStorebrand"
                     value="${post.reviewTarget.storeId}" readonly="readonly">
                    
                    <label class="storebrand">편의점브랜드명</label>
                    <!-- StoreId에 따라 storeBrand을 다르게 출력 -->
					<c:choose>
					
					<c:when test="${post.reviewTarget.storeId eq 1}">
						<input type="text" name="storebrand" 
						class="SelectStorebrand" value="GS25" readonly="readonly">
					</c:when>
					<c:when test="${post.reviewTarget.storeId eq 2}">
						<input type="text" name="storebrand" 
						class="SelectStorebrand" value="CU" readonly="readonly">
					</c:when>
					<c:when test="${post.reviewTarget.storeId eq 3}">
						<input type="text" name="storebrand" 
						class="SelectStorebrand" value="7ELEVEN" readonly="readonly">
					</c:when>
					<c:when test="${post.reviewTarget.storeId eq 4}">
						<input type="text" name="storebrand" 
						class="SelectStorebrand" value="emart24" readonly="readonly">
					</c:when>										
					<c:otherwise>
						<input type="text" name="storebrand" 
						class="SelectStorebrand" value="기타" readonly="readonly">
					</c:otherwise>
					</c:choose>
                    
                    <label class="prdAssort">상품분류</label>
                    <input type="text" name="prdAssort" class="SelectprdAssort" value="${post.reviewTarget.prdAssort}" readonly="readonly">
                    
                    </div>
                    <label class="prdName">상품명</label>
                    <input type="text" name="prdName" class="InputprdName" value="${post.reviewTarget.prdName}" readonly="readonly">
                
                    <label class="prdPrice">상품가격</label>
                    <input type="text" name="prdPrice"class="InputprtPrice" value="${post.reviewTarget.prdPrice}" readonly="readonly">
                    
                    <label class="prdRate">상품평</label>
                    <ul class="radioPrdRate">
                        <li><input type="text" name="prdRate" value="${post.reviewTarget.prdRate}" readonly="readonly"></li>
                    </ul>
	                <!-- 선호상품 등록 -->
		            <input type="button" class="preferBtn" value="선호상품등록">
                </div>
                <label class="content">본문</label>
                <textarea name="content" class="InputText" readonly="readonly"><c:out value="${post.content}"/></textarea>
                </form>
                <input type="button" value="목록" class="listbtn">
            <!-- 로그인과 관련된 정보 -->
            <sec:authentication property="principal" var="userinfo"/>
            
			<sec:authorize access="isAuthenticated()">
                
                <!-- 로그인한 사용자의 id와 게시글 작성자id를 비교한다 -->
                <c:if test="${userinfo.username eq post.writer}">
                	<input type="button" value="삭제" class="deletebtn">
                	<input type="button" value="수정" class="modifybtn">
                </c:if>            
        	</sec:authorize>
        </section>
        <section id="replySection">
            <div class="replyList">
                <ul class="replyWindow">
                </ul>
                <ul class="pageHandler">
                </ul>
            </div>
            <div class="replyinputWindow">
            <!-- 로그인과 관련된 정보 -->
            <sec:authorize access="isAuthenticated()">
                <form id="replyForm">
                    <label class="writerI">${userinfo.username}</label>
                    <input type="text" name="replyInputcontent" value="댓글내용" class="rplyContI">
                    <input type="button" value="등록" class="rplybtnI">
                </form>
            </sec:authorize>
            </div>
        </section>
    </div>
</body>
<script>
// 현재 조회 중인 게시글의 id
var postId = '<c:out value="${post.hierarchyId}"/>';

$(document).ready(function() {

	//목록버튼을 누르면 목록페이지로 이동
    $(".listbtn").on("click" , function(e){
        location.href ="/post/postList";
    });
	
	//수정버튼을 누르면 수정페이지로 이동. 조회하고 있는 리뷰포스트의 id를 get방식으로 넘겨줌
    $(".modifybtn").on("click" , function(e){
        location.href ="/post/postModifyPage?hierarchyId=<c:out value='${post.hierarchyId}'/>";
    });
	
	//삭제버튼을 누르면 삭제 컨트롤러로 이동. 
    $(".deletebtn").on("click" , function(e){
        location.href ="/post/postDelete?hierarchyId=<c:out value='${post.hierarchyId}'/>";
    });
	
	// 리뷰포스트 폼
	var postInputForm = $("#postInputForm");
	
    // 선호상품 등록에 필요한 정보를 정의함
    var preferuserId = "${userinfo.username}";
    var storeBrandval = postInputForm.find("input[name='storebrand']").val();
    var preferPrdAssort = postInputForm.find("input[name='prdAssort']").val();
   	var preferPrdName = postInputForm.find("input[name='prdName']").val();
   	var preferPrdPrice = postInputForm.find("input[name='prdPrice']").val();
 
    // 선호상품등록 버튼을 누르면 
    $(".preferBtn").on("click", function(){
        //alert(storeBrandval);
        var prefer = {
        	userId:preferuserId,
        	store:{storeBrand:storeBrandval},
        	preferProdc : {prdAssort:preferPrdAssort,
        			prdName:preferPrdName,
        			prdPrice:preferPrdPrice
        	}
        };
        
        preferService.addPrefer(prefer, function(preferObj){
        	showReplyList(postId);
        });
        alert("해당 상품이 선호상품으로 등록되었습니다.");
    });
	
    // 댓글 목록을 출력할 영역
    var replyListArea = $(".replyList");
    
    showReplyList(postId);

	// 댓글 입력폼
	var ReplyInputForm = $("#replyForm");
    // 댓글 입력폼의 input 태그의 속성
    var txtContent = ReplyInputForm.find("input[name='replyInputcontent']");
    // 댓글 작성자
    var replyer = null;
    
    <sec:authorize access="isAuthenticated()">
    	replyer = '<sec:authentication property='principal.party.userId'/>';
    </sec:authorize>
    
    // 암호화
    var csrfHeaderName = "${_csrf.headerName}";
    var csrfTokenValue = "${_csrf.token}";
    
	// Ajax spring security header
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
	});
    
	// 등록버튼을 누르면 reply에 댓글 내용을 json형식으로 저장한다.
    $(".rplybtnI").on("click", function(){
    	//alert(parentReplyId);
    
    	var reply;
    
    	// 대댓글이 아니면 위에서 선언해놓은 postId를 참고하여 hierarchyId를 작성한다.
    	if(parentReplyId == null || parentReplyId == "") {
			reply = {hierarchyId:postId,
					content:txtContent.val(),
					writer:replyer
					};
		// 대댓글이면
    	} else {
			reply = {hierarchyId:parentReplyId,
					content:txtContent.val(),
					writer:replyer
					};
    	}
        replyService.addReply(reply ,function(replyObj) {
        	// 리플입력폼 초기화 
        	ReplyInputForm.find("input[name='replyInputcontent']").val("");
        	showReplyList(postId);
        });
    });
	
 // 댓글 목록 조회 함수
    function showReplyList(postId) {
    	replyService.getList(
    		// 게시글 아이디를 json 형식으로 매핑 {key:value, key:value, ... , key:value} 
    		postId,
    		// ReplyController.getReplyList()의 return값을 출력하는 함수 
    		function(listReply) {
    			var replyArea = "";
    			for (var i = 0; i < listReply.length; i++) {
    				// 대댓글의 경우에는 전용 프레임을 씌워준다.
    				if( listReply[i].depth >=2) {
    					replyArea += "<div class='rereplyFrame'>";
    				}
    				replyArea += "<ul class='replyWindow'>";
    				replyArea += "<li id='replyId"+i+"'class='hid'>"+listReply[i].hierarchyId+"</li>";
    				replyArea += "<li class='writerV' onclick='openReplyInputWindow("+(i+1)+","+i+");'>"+listReply[i].writer+"</li>";
    				replyArea += "<li class='regdateV'>"+listReply[i].regdate+"</li>";
    				replyArea += "<li class='updateV'>"+listReply[i].updateDate+"</li>";
    				replyArea += "<li class='contentV'>";
    				// 대댓글의 경우에만 부모의 댓글 작성자 이름을 표시한다.
    				if( listReply[i].depth >=2) {
    					replyArea += "<label class='parentW'>"+listReply[i-1].writer+"</label>";
    				}
    				replyArea += listReply[i].content+"</li>";
    				replyArea += "</ul>";
    				if( listReply[i].depth >=2) {
    					replyArea += "</div>";
    				}
    			}
    			if (replyArea.length != 0)
    				replyListArea.html(replyArea);
    			}
    )}
    // 댓글 목록 조회 함수 종료
});

var parentReplyId="";

// 댓글 입력창 오픈
function openReplyInputWindow(childnumber, listindex) {
	
	// 부모댓글의 Id를 가지고 온다.
	// 부모댓글의 아이디를 가져오는 방식은 부모댓글의 아이디를 담고 있는 태그에 접근하여 텍스트를 가져오는 걸로 해결함
	parentReplyId = $('#replyId'+listindex).text();
	
    var parentReply = $(".replyList>:nth-child("+childnumber+")");
    
    var rereply = $(".replyinputWindow");
    parentReply.append(rereply);
}
</script>
</html>