<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>活动主页</title>
<link rel="stylesheet" href="/textbook-custom/plugin/bootstrap-3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="/textbook-custom/css/wjx/game.css">
</head>
<body>
	<div class="container">
	<img src="/textbook-custom/image/wjx/rank_bg.png">
		<div class="row" id="matchRule">
			<div>
				<a href="/textbook-custom/page/getGameRules" style="color: white;">游戏规则</a>
			</div>
		</div>
		<div id="matchRank">
			<div class="row" id="matchPhase">				
				<span class="rank" >上次排名</span>
				<span class="bestRank">赛季排名</span>
			</div>
			<div class="row" id="specificRank">
				<span class="rank"></span>
				<span class="bestRank"></span>
			</div>
		</div>
		<div class="row" id="beginTime">
			<span>下期开始时间</span>
			<span id="startTime"><span id="time"></span></span>		
		</div>
		<div class="row">
			<input id="url" type="hidden" name="url">
		</div>
		<div class="row" id="beginMatch">
			<div>
				<button class="btn" type="button" onclick="ask();">开始答题</button>
			</div>
		</div>
	</div>
	<script src="/textbook-custom/plugin/jQuery/jquery.min.js"></script>
	<script src="/textbook-custom/plugin/bootstrap-3.3.7/js/bootstrap.min.js"></script>
	<script src="/textbook-custom/js/wjx/game.js"></script>
</body>
</html>