/**
 * 알림창을 컨트롤하는 자바스크립트
 */
$(document).ready(function() {
	// controller에서 보내주는 postId
	var result = '<c:out value="${ReviewPostId}"/>';
   checkInformWindow(result);
   
});

function checkInformWindow(result) {
    if (result== '' && result== null) {
        // 반환되는 postid가 없으면 그냥 목록을 보여준다.
    	return;
    }
    
    if(result !=null && result !='') {
        $(".informContent").html("게시물"+ result+"번이 등록되었습니다.");
    }
    
    $(".informLayer").show();
    
    $(".informbtn").on("click", function(){
        $(".informLayer").hide();
    });
}

