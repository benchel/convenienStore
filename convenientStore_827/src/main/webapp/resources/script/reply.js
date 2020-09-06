/**
 * [reply.js]
 * AJAX 방식으로 ReplyService를 지원한다.
 * 
 * AJAX란?
 * Asynchronous JavaScript And XML(비동기화 자바스크립트와 XML)를 의미한다.
 * 자바스크립트로 서버와 통신하기 위해 XMLHttpRequest 객체를 사용하는 방식.
 * JSON, XML, HTML 그리고 일반 텍스트 형식 등을 포함한 다양한 포맷을 주고 받는다.
 * 
 * AJAX의 특징
 * 페이지 전체를 재실행하지 않고서도 수행된다(비동기성)
 * 사용자의 Event가 있을때 전체 페이지가 아닌 일부분만을 업데이트 할 수 있다.
 * 
 * 자바스크립트에서는 함수도 객체로 취급. 다시말해서 일종의 값. 
 * replyService에 댓글 작성 및 수정, 삭제에 필요한 함수를 담았다.
 * 
 * JSONP(JSON with Padding)으로 서로 다른 도메인에 대한 요청을 지원한다.
 * 
 * JSONP이란?
 * 
 * <script/> 태그는 same-origin-policy(SOP) 정책에 속하지 않는다는 사실을 이용하여
 * 서로 다른 도메인간의 스크립트를 호출한다.
 * 
 * 서버가 요청에 대한 응답을 json형태로 만들어 callback 파라미터로 전달한다.
 */
console.log("Reply Loaded.........");

var replyService = (function() {

	function getListFunc(postId, callback, errorCB) {
	/**
	 * jQuery.getJSON( url [, data] [, success(data, textStatus, jqXHR)] ) 
	 * url : 정보를 요청할 URL 
	 * data : 서버에게 요청한 data
	 * success(data, textStatus, jqXHR) 요청이 성공하면 실행될 콜백 함수
	 * jsonpCallback : Specifies a name for the callback function in a jsonp request
	 */
		$.getJSON(
			// 정보를 요청할 URL 
			"/replies/rlist/" + postId + ".json",
			// 데이터
			function(listReply){
				callback(listReply);
			}).fail( // 서버에 데이터를 전송하는 걸 실패했을 때의 동작
			function(xhr, status, errorMsg){
				if (errorCB) {
					errorCB(errorMsg);
				}
			// success() 생략
			});
	}
	
	function addFunc(reply, callback, errorCB) { 
		// 이 ajax() 메소드는 AJAX request를 실행하는데 사용된다.
		$.ajax({
			type :'post',	//ReplyController.insertReply @PostMapping
			url : '/replies/new',
			// JSON.stringify() : JavaScript 값이나 객체를 JSON 문자열로 변환
			data : JSON.stringify(reply),
			//서버에 데이터를 보낼 때 사용 content - type 헤더의 값
			contentType : "application/json; charset=utf-8",
			success : function(replyObj, status, xhr){
				//insertReply(@RequestBody ReplyVO reply);
				callback(replyObj);
			},
			error : function(xhr, status, errorMsg){
				if (errorCB) {
					errorCB(errorMsg);
				}
			}
		});
	}
	
	return {
		getList:getListFunc, // 키와 값으로 표현
		addReply:addFunc
		};
})();
