<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>夏令营</title>
<link href="/camp-custom/plugin/bootstrap-3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="/camp-custom/css/matchItems.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>排名</th>
					<th>分数</th>
					<th>姓名</th>
					<th>专业类别</th>
					<th>专业名称</th>
					<th>入学年份</th>
					<th>班级</th>
					<th>学号</th>
					<th>挑战次数</th>
					<th>最后上传时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${msDtos}" var="item" varStatus="itemIndex">
					<tr>
						<td>${item.stuNum}</td>
						<td>${item.score}</td>
						<td>${item.stuName}</td>
						<td>${item.majorType}</td>

						<td>${item.majorName}</td>
						<td>${item.beginYear}</td>
						<td>${item.className}</td>
						<td>${item.stuId}</td>

						<td>${item.chalgNum}</td>
						<td>${item.lastTime}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="pagination" class="pagination"></div>
	</div>
	<script src="/camp-custom/plugin/jQuery/jquery.min.js"></script>
	<script src="/camp-custom/plugin/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
	<script src="/camp-custom/plugin/jqpaginator/jqpaginator.min.js"></script>
	<script type="text/javascript">
		function thisOnChange(a,b,c){
			window.location.href = "/camp-custom/match/page/matchScore/" + a + "?page=" + b + "&size=" + c ;
		}
		var total = ${msTotal};
		var itemid = ${itemId};
		var size = ${size};
		var page = ${page};
		$('#pagination')
				.jqPaginator(
						{
							totalPages : total ,
							visiblePages : size,
							currentPage : page,

							first : '<li class="first"><a href="javascript:void(0);">First</a></li>',
							prev : '<li class="prev"><a href="javascript:void(0);">Previous</a></li>',
							next : '<li class="next"><a href="javascript:void(0);">Next</a></li>',
							last : '<li class="last"><a href="javascript:void(0);">Last</a></li>',
							page : '<li class="page"><a href="javascript:void(0);">{{page}}</a></li>',
							onPageChange : function(num,type) {
								if("change" === type){
									thisOnChange(itemid,num,size);
								}
							}
						});
	</script>
</body>
</html>