<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home</title>

	<!-- Font -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/font-nanum/1.0/nanumbarungothic/nanumbarungothic.css"/>
	
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="/resources/css/common.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/header.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/nav.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/channel.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/community.css" />

</head>
<body>

	<div id="home">
		<login-component></login-component>
	
		<header-component></header-component>

		<div id="home_content">
			<router-view></router-view>
		</div>
	</div>

	<!-- CDN -->
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script type="text/javascript" src="https://unpkg.com/vue-router/dist/vue-router.js"></script>
	<script type="text/javascript" src="https://unpkg.com/vuex"></script>

	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/es6-promise@4/dist/es6-promise.auto.js"></script>

	<!-- Vuex Stroe -->
	<script type="text/javascript" src="/resources/js/store.js"></script>

	<!-- templates -->
	<script>
		const headerTemplate 	= `<%@ include file="/resources/template/header.jsp" %>`;
		const channelTemplate 	= `<%@ include file="/resources/template/channel.jsp" %>`;
		const loginTemplate		= `<%@ include file="/resources/template/login.jsp" %>`;
		
		
		
		const communityTemplate		= `<%@ include file="/resources/template/community/community.jsp" %>`;
		const communityHomeTemplate	= `<%@ include file="/resources/template/community/communityHome.jsp" %>`;
		const communityMenuTemplate	= `<%@ include file="/resources/template/community/communityMenu.jsp" %>`;
		const communityPostTemplate	= `<%@ include file="/resources/template/community/communityPost.jsp" %>`;

		const communityPostItemSmallTemplate	= `<%@ include file="/resources/template/community/communityPostItemSmall.jsp" %>`;
	</script>

	<script type="text/javascript" src="/resources/js/component/login.js"></script>

	<script type="text/javascript" src="/resources/js/component/header.js"></script>
	<script type="text/javascript" src="/resources/js/component/channel.js"></script>
	
	<script type="text/javascript" src="/resources/js/component/community/communityPostItemSmall.js"></script>

	<script type="text/javascript" src="/resources/js/component/community/communityPost.js"></script>
	<script type="text/javascript" src="/resources/js/component/community/communityMenu.js"></script>
	<script type="text/javascript" src="/resources/js/component/community/communityHome.js"></script>
	<script type="text/javascript" src="/resources/js/component/community/community.js"></script>

	<script type="text/javascript" src="/resources/js/index.js"></script>

	<!-- JS Utils -->
	<script type="text/javascript" src="/resources/js/util/action.js"></script>
	<script type="text/javascript" src="/resources/js/util/time.js"></script>
	<script type="text/javascript" src="/resources/js/util/nav.js"></script>
	<script type="text/javascript" src="/resources/js/util/pageHandler.js"></script>

</body>
</html>
