/**
 * 
 */
console.log("Prefer Loaded.........");

var preferService = (function() {
	
	function getPreferListFunc(userId,  callback, errorCB) {
			$.getJSON(
				// 정보를 요청할 URL
				"/prefer/preferList/" + userId + ".json",
				// 데이터
				function(listPrefer){
					callback(listPrefer);
				}).fail( // 서버에 데이터를 전송하는 걸 실패했을 때의 동작
				function(xhr, status, errorMsg){
					if (errorCB) {
						errorCB(errorMsg);
					}
				// success() 생략
				});
	}
	
	function addPreferFunc(prefer, callback, errorCB) {
		$.ajax({
			type:'post',
			url:'/prefer/reg',
			data:JSON.stringify(prefer),
			contentType : "application/json; charset=utf-8",
			success : function(preferObj, status, xhr){
				callback(preferObj);
			},
			error : function(xhr, status, errorMsg){
				if(errorCB) {
					errorCB(errorMsg);
				}
			}
		}); // $.ajax({}) end
	} // addPreferFunc End
	
	function getRecommendListFun (prefer,  callback, errorCB) {
		var userId=prefer.userId;
		var prdName=prefer.preferProdc.prdName;
		var prdAssort=prefer.preferProdc.prdAssort;
		
		$.getJSON(
				// 정보를 요청할 URL
				"/prefer/recommendList/" + userId + "/"+ prdName +"/"+prdAssort + ".json",
				// 데이터
				function(recommendlist){
					callback(recommendlist);
				}).fail( // 서버에 데이터를 전송하는 걸 실패했을 때의 동작
				function(xhr, status, errorMsg){
					if (errorCB) {
						errorCB(errorMsg);
					}
				// success() 생략
				});	
	}
	
	return {
		getPreferList:getPreferListFunc,
		addPrefer:addPreferFunc,
		getRcmdList:getRecommendListFun
	}
	
})();