<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="informLayer">
	<div class="informWindow">
		<div class="informContent">알림내용</div>
		<button class="informbtn" onclick="">확인</button>
	</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	// controller에서 보내주는 postId
	var result = '<c:out value="${ReviewPostId}"/>';
   	checkInformWindow(result);
});

function checkInformWindow(result) {
	console.log(result);

    if (result == '' || result == null) {
        // 반환되는 postid가 없으면 그냥 목록을 보여준다.
    	return;
    }
    
    if(result !=null || result !='') {
        $(".informContent").html("게시물"+ result+"번이 등록되었습니다.");
    }
    
    $(".informLayer").show();
    
    $(".informbtn").on("click", function(){
        $(".informLayer").hide();
    });
}
</script>