<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>游戏向导</title>
<link rel="stylesheet"
	href="/textbook-custom/plugin/bootstrap-3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="/textbook-custom/plugin/bootstrap-3.3.7/css/signin.css">
</head>
<body>
	<div class="container" style="margin-top: 254px;">
		<form class="form-signin" action="/textbook-custom/page/joinGame">
			<h2 class="form-signin-heading" style="text-align: center;">请输入指导教师姓名</h2>
			<div style="margin-top: 22px;">
				<label for="teacher" class="sr-only">教师姓名</label>
				<div class="row">
					 <div class="col-lg-6">
					   <div class="input-group">
					     <span class="input-group-btn">
					       <button type="button" class="btn btn-default btn-lg" style="margin-left: -50px;">
							<span class="glyphicon glyphicon-user"></span>
						   </button>
					     </span>
					     <input id="teacher" name="teacher" class="form-control .col-md-8" type="text" 
								style="width: 376px; height: 46px;" aria-describedby="basic-addon1" 
								placeholder="请输入教师姓名" required autofocus>
					   </div>
					 </div>
				</div>
				<input id="userId" name="userId" type="hidden" value="${userId > 0?userId:''}"> 
				<input id="url" name="url" type="hidden" value="${url != null?url:''}"> 
				<input id="id" name="id" type="hidden" value="${id > 0?id:''}">
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit"
				style="margin-top: 42px; width: 425px; margin-left: -49px;">保存</button>
		</form>
			<img src="/textbook-custom/image/wjx/teacher_bg.png" style="margin-top: 78px;"/>
	</div>
	<script src="/textbook-custom/plugin/jQuery/jquery.min.js"></script>
	<script
		src="/textbook-custom/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
	<script src="/textbook-custom/js/qrcode/addProductQrcode.js"></script>
</body>
</html>