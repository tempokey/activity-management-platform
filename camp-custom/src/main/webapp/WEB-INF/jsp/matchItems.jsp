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
		<div class="jump">
			<div class="inner cover">
				<c:forEach items="${miDtos}" var="item" varStatus="itemIndex">
					<c:if test="${1 == item.flag}">
						<div class="jumbotron">
							<a href="/camp-custom/match/page/matchScore/${item.id + ''}" role="button" class="btn btn-warning btn-lg btn-block"><c:out value="${item.name}" /></a>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	<script src="/camp-custom/plugin/jQuery/jquery.min.js"></script>
	<script src="/camp-custom/plugin/bootstrap-3.3.7/dist/js/bootstrap.min.js"></script>
</body>
</html>