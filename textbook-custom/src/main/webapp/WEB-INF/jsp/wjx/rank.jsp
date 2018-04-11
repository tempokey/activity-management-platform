<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>排行榜</title>
<link rel="stylesheet" href="/textbook-custom/plugin/bootstrap-3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="/textbook-custom/plugin/bootstrap-3.3.7/css/signin.css">
<link rel="stylesheet" href="/textbook-custom/plugin/bootstrap-3.3.7/css/sticky-footer.css">
<link rel="stylesheet" href="/textbook-custom/css/wjx/rank.css">
</head>
<body>
	<div class="row" style="background-color: #6F71F3;color: white;margin-top: -40px;
		 height: 70px;font-size: 40px;margin-bottom: 15px;width: 103%;">
    	 	<span style="margin-left: 173px;">排行榜</span>
    	 	<img src="/textbook-custom/image/wjx/bk_black.png" 
			style="margin-left: 40px;margin-top: 24px;float: left;"
			onclick="javascript:history.back(-1);">
    	 </div>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div align="center">
					<div class="btn-group" role="group" aria-label="...">
						<button type="button" class="btn btn-default btn-lg" style="width: 170px;" onclick="refreshRank(3);">上次</button>
						<button type="button" class="btn btn-default btn-lg" style="width: 170px;" onclick="refreshRank(2);">本周</button>
						<button type="button" class="btn btn-default btn-lg" style="width: 170px;" onclick="refreshRank(1);">本赛季</button>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div align="center">
					<table class="table">
						<tbody id="rankboard">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<footer class="footer" style="height: 100px;">
    	<div class="container" style="margin-top: 10px;">
    		<div align="center">
    			<div id="myRankBord"></div>
				<div id="pagination" class="pagination" ></div>
				<input id="userId" type="hidden" value="${userId}">
			</div>
		</div>
	</footer>
	<script src="/textbook-custom/plugin/jQuery/jquery.min.js"></script>
	<script src="/textbook-custom/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
	<script src="/textbook-custom/plugin/jqpaginator/jqpaginator.min.js"></script>
	<script type="text/javascript">
		function thisOnChange(a,b,c,d){
			window.location.href = "/textbook-custom/mob/game/listRank" + "?gameGroupNo=" + a + "&flag=" + b + "&page=" + c + "&size=" + d ;
		}
		var total = 50;
		var flag = ${flag};
		var gameGroupNo = ${gameGroupNo};
		var userId = ${userId};
		var size = ${size};
		var page = ${page};
		$('#pagination')
				.jqPaginator(
						{
							totalPages : total ,
							visiblePages : 5,
							currentPage : page,

							first : '<li class="first"><a href="javascript:void(0);">First</a></li>',
							prev : '<li class="prev"><a href="javascript:void(0);">Previous</a></li>',
							next : '<li class="next"><a href="javascript:void(0);">Next</a></li>',
							last : '<li class="last"><a href="javascript:void(0);">Last</a></li>',
							page : '<li class="page"><a href="javascript:void(0);">{{page}}</a></li>',
							onPageChange : function(num,type) {
								if("change" === type){
									thisOnChange(gameGroupNo,flag,num,size);
								}
							}
						});
	</script>
	<script src="/textbook-custom/js/wjx/rank.js"></script>
</body>
</html>