/**
 * 페이징처리를 위한 자바스크립트
 * get방식으로 <input>의 value값을 넘겨줌
 */
$(document).ready(function() {
   
    var actionForm = $("#actionForm");
    
    //a태그를 눌러도 페이지 이동이 없도록 처리
    $(".paginumber_btn a").on("click", function(e) {
    	
        e.preventDefault();
        
        console.log('click');
             
        //여기서 this는 .paginumber_btn a의 href의 값
        actionForm.find("input[name='pageNum']").val($(this).attr("href"));
        actionForm.find("input[name='keyword']").val($(".inputText").val());
        actionForm.submit();
    });

});