var phone = null;
var adCode = null;
var userId = null;
var teacher = null;
var gameId = null;
var url = null;

var gameGroupNo = 1;
var baseUrl = "http://47.97.23.45:8401/textbook-custom/";
// var baseUrl = "http://localhost:8401/textbook-custom/";
var requestUrl = baseUrl + "mob/game/getInitInfo";
var hrefUrl = baseUrl + "page/joinGame";
var rankUrl = baseUrl + "page/getRank";
function userinfo(v) {

	if (v == undefined || v == null || v.length < 11) {
		return false;
	}

	var arr = v.split(/[\u4e00-\u9fa5]+/g);
	adCode = arr[0];
	phone = arr[1];

	$.ajax({
		type : "GET",
		url : requestUrl,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		dataType : "jsonp",
		jsonp : "data", // 这里定义了callback的参数名称，以便服务获取callback的函数名即getMessage
		jsonpCallback : "callback", // 这里定义了jsonp的回调函数
		data : {
			account : phone,
			adCode : adCode,
			gameGroupNo : gameGroupNo,
			callback : "callback"
		},
		success : function(res, status, req) {
			if (10000 == res[0].code) {
				var param = JSON.parse(res[0].data);

				userId = param.userId;
				teacher = param.teacher;
				gameId = param.gameId;
				url = param.url;

				var rt = '';

				if (null != param.gameStartTime) {
					var date = new Date(param.gameStartTime.time);
					var mth = date.getMonth() + 1;
					var d = date.getDate();
					var h = date.getHours();
					var m = date.getMinutes();
					var t = date.getTime();

					var now = new Date();
					var t_ = now.getTime();

					var c = t - t_;

					if (c < 86400000) {
						rt += "今日 ";
					} else if (c < 172800000) {
						rt += "明日 ";
					} else {
						rt += mth + "月" + d + "日 ";
					}

					rt += h + " : " + m;

					$('#startTime').html(rt);
				}

				$('#rank').html(param.rank);
				$('#bestRank').html(param.bestRank);
				$('#url').val(url);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
				
		},
		callback : function(data) {

		}
	});

}

function ask() {
	if (null != teacher) {
		window.location.href = hrefUrl + "?ack=1&userId=" + userId + "&id="
				+ gameId + "&url=" + url + "&teacher=" + teacher;
	} else {
		window.location.href = hrefUrl + "?ack=1&userId=" + userId + "&id="
				+ gameId + "&url=" + url;
	}
}

$("#matchRank").click(
		function() {
			window.location.href = rankUrl + "?userId=" + userId
					+ "&gameGroupNo=" + gameGroupNo + "&flag=3";
		})
//window.onload = function() {
//	userinfo('12345强哥15904267828'); 
//}
