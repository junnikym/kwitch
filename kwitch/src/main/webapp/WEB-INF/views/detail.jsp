<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="java.util.*"%>

<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>

	<!-- Font -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/font-nanum/1.0/nanumbarungothic/nanumbarungothic.css"/>
	
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="/resources/css/detail.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/common.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/header.css" />

	<link rel="stylesheet" type="text/css" href="/resources/css/nav.css" />

	<!-- CDN -->
	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
	
</head>
<body>
	<%@ include file="header.jsp" %>

	<% Boolean isOwnPage = false; %>

	<div id="detail">

		<table class="detail_header" border=0>
			<tr>
				<td class="detail_header__profile_image" rowspan="2">
					
					<c:choose>
						<c:when test="${empty member.profileImagePath || empty member.profileImageExt}">
							<div class="user_profile_img_wapper__detail">
								<img src="/resources/image/user_icon.png" class="default_user_profile_img" />
							</div>
						</c:when>
						<c:otherwise>
							<div class="user_profile_img_wapper__detail">
								<img src="/api/profile/image/${member.profileImagePath}/${member.profileImageExt}" class="user_profile_img" />
							</div>
						</c:otherwise>
					</c:choose>
					

					<c:set var="member_id" value="<%=member_id%>" />
					<c:if test="${member.id eq member_id}">
						<a class="detail_header__modify" v-on:click="toggleProfileImageSetter">
							<img src="/resources/image/modify_icon.png" class="detail_header_setting_img" />
						</a>
						
						<div class="set_profile_image">
							<div id="set_profile_image__thumb">
								<img />
							</div>
							<div class="set_profile_image__uploader">
								<input type="file" name="profile_image" id="upload_profile_image" @change="setProfileImageThumb"/>
								<button class="btn"  type="button" v-on:click="toggleProfileImageSetter">cancel</button>
								<button class="btn"  type="button" v-on:click="uploadProfileImage('<%=member_id%>')">Finish</button>
							</div>
						</div>
					</c:if>
					
				</td>
				<td class="detail_header__letter"><h1>
					<c:choose>
						<c:when test="${empty member.alias}">
							${member.name}
						</c:when>
						<c:otherwise>
							${member.alias}
						</c:otherwise>
					</c:choose>
				</h1></td>
			</tr>
			<tr><td class="detail_header__letter"> follow </td></tr>
		</table>
	
		<div class="detail_info_content_wapper">
			<nav class="menu" id="myMenu">
				<ul>
					<!-- active link and hover indicator, menu slider -->
					<div class="m-active"></div>

					<!-- menu links -->
					<!--
					<li><a v-on:click="changeNav('home')" >Home</a></li> 
					<li><a v-on:click="changeNav('video')" >Video</a></li>
					-->
					<li><a v-on:click="changeNav('about')" id="about_btn">About</a></li>
					<li><a v-on:click="changeNav('contact')" >Contact</a></li>
				</ul>
			</nav>

			<div class="detail_info_content">
				<div class="detail_home">
					<h1> Home </h1>
				</div>

				<div class="detail_about">
					<h1 class="detail_alias"> About 
						<c:choose>
							<c:when test="${empty member.alias}">${member.name} </c:when>
							<c:otherwise>${member.alias} </c:otherwise>
						</c:choose>
					</h1>

					<c:set var="member_id" value="<%=member_id%>" />
					<c:if test="${member.id eq member_id}">
						<a class="detail_about__modify">
							<img src="/resources/image/setting_icon.png" class="detail_about_setting_img" />
						</a>
					</c:if>
					
					<p class="detail_message">${member.message}</p>
				</div>

				<div class="detail_video">
					<h1> Video </h1>
				</div>

				<div class="detail_contact">
					<h1> Contact </h1>
					
					<table>
						<c:if test="${not empty member.name}">
							<tr>
								<td width='50px'> name </td>
								<td class="detail_contact__right"> ${member.name} </td>
							</tr>
						</c:if>
					
						<c:if test="${not empty member.email}">
							<tr>
								<td width='50px'> email </td>
								<td class="detail_contact__right"> ${member.email} </td>
							</tr>
						</c:if>
						
						<c:if test="${not empty member.phone}">
							<tr>
								<td width='50px'> phone </td>
								<td class="detail_contact__right"> ${member.phone} </td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>

		</div>
	</div>

	<!-- JS -->
	<script type="text/javascript" src="/resources/js/store.js"></script>

	<script type="text/javascript" src="/resources/js/view/index.js"></script>
	<script type="text/javascript" src="/resources/js/view/header.js"></script>
	<script type="text/javascript" src="/resources/js/view/login.js"></script>
	<script type="text/javascript" src="/resources/js/view/detail.js"></script>
	
	<script type="text/javascript" src="/resources/js/util/action.js"></script>
	<script type="text/javascript" src="/resources/js/util/nav.js"></script>

</body>
</html>
