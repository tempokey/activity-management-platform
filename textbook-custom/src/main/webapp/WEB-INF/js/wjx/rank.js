/*<tr>
	<td class="col-xs-2 col-sm-2">1</td>
	<td class="col-xs-3 col-sm-3">赵四</td>
	<td class="col-xs-5 col-sm-5">大连商业学校</td>
	<td class="col-xs-2 col-sm-2">1000</td>
</tr>*/
var baseUrl = "/textbook-custom/";
var requestUrl = baseUrl + "mob/game/listRank";
var myRankUrl = baseUrl + "mob/game/myRank";
function refreshRank(flag_) {
	if (undefined == flag_ || null == flag_) {
		flag_ = 3;
	}
	flag = flag_;
	$.ajax({
		type : "GET",
		url : requestUrl,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
			gameGroupNo : 1,
			page : 1,
			size : 10,
			flag : flag_
		},
		success : function(res, status, req) {
			if (10000 == res.code) {
				var param = JSON.parse(res.data)[0];
				drawTable(param.dtos,param.total,flag);
			} else {
				drawTable(null);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
		}
	});
}

function drawTable(a,t,f) {
	var userId = document.getElementById("userId").value;
	$('#rankboard').empty();
	$('#myRankBord').empty();
	if (undefined != a && null != a && a.length > 0) {
		for (var i = 0; i < a.length; i++) {
			if(0 == i){ 
		         $('#rankboard').append( 
		         '<div class="tb_list_tr_img row"><div class="col-xs-2 col-sm-3" style="color: white;">' 
		                     + 'NO.'+(i+1) 
		                     + '</div><div class="col-xs-3 col-sm-3" style="margin-left: 25px;">' + a[i].name 
		                     + '</div><div class="col-xs-4 col-sm-3">' + a[i].school 
		                     + '</div><div class="col-xs-1 col-sm-3">' + a[i].score 
		                     + '</div></div>');
		      }else{ 
		         $('#rankboard').append( 
		               '<div class="tb_list_tr"><div class="col-xs-2 col-sm-3">' + 'NO.'+(i+1) 
		                     + '</div><div class="col-xs-3 col-sm-3" style="margin-left: 35px;">' + a[i].name 
		                     + '</div><div class="col-xs-4 col-sm-3">' + a[i].school 
		                     + '</div><div class="col-xs-1 col-sm-3">' + a[i].score 
		                     + '</div></div>');
		      }  
		}
		refreshMyselfRank(f,userId);
		var p = Math.ceil(t / size);
		total = p;
		$('#pagination').jqPaginator('option', { totalPages : p , totalCounts : t });
	}
}

function refreshMyselfRank(flag_,userId){
	$.ajax({
		type : "GET",
		url : myRankUrl,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		data : {
			gameGroupNo : 1,
			userId : userId,
			flag : flag_
		},
		success : function(res, status, req) {
			if (10000 == res.code) {
				var param = JSON.parse(res.data)[0];
		        $('#myRankBord').append(
				        '<div class="row"><div class="col-xs-2 col-sm-3">' 
				                    + param.rank
				                    + '</div><div class="col-xs-4 col-sm-3">' + param.name 
				                    + '</div><div class="col-xs-4 col-sm-3">' + param.school 
				                    + '</div><div class="col-xs-1 col-sm-3">' + param.score 
				                    + '</div></div>'); 
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
		}
	});
}

window.onload = function() {
	refreshRank();
}