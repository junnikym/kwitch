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

	<!-- CDN -->
	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
	<script src="https://unpkg.com/vue-router@2.6.0/dist/vue-router.js"></script>
	
	<style>
      .iframe_00 {
         /*
         높이 넓이를 자식창에 맞도록 변경
         height 값의 경우 100vh가 맞지만 top: -150px 을 해주었기 때문에 비율 만큼 더했다!
         */
         position: absolute;
         display: block;
         border: none;
         top: 65px;
         height: calc(100vw - 65px);
         width: 100vw;
         /*상단 숨김 코드*/
         position: relative;
      }
      </style>
	
</head>
<body style="position: relative; margin : 0;overflow-x : hidden;overflow-y : hidden;">
	<%@ include file="../../resources/template/header.jsp" %>
	<iframe src="/resources/template/community/communityPost.jsp" name="thumb" class="iframe_00"></iframe>

	<!-- JS -->
	<script type="text/javascript" src="/resources/js/store.js"></script>

	<script type="text/javascript" src="/resources/js/index.js"></script>
	<script type="text/javascript" src="/resources/js/view/header.js"></script>
	
	<script type="text/javascript" src="/resources/js/util/action.js"></script>
    
</body>
</html>
