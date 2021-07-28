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

</head>
<body>
	<%@ include file="header.jsp" %>

	<div id="mainContent">
		<router-view></router-view>
	</div>

	<!-- CDN -->
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>

	<!-- JS -->
	<script type="text/javascript" src="/resources/js/store.js"></script>

	<script type="text/javascript" src="/resources/js/view/index.js"></script>
	<script type="text/javascript" src="/resources/js/view/header.js"></script>

	<script type="text/javascript" src="/resources/js/util/action.js"></script>
	<script type="text/javascript" src="/resources/js/util/pageHandler.js"></script>
    
</body>
</html>
