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
	<link rel="stylesheet" type="text/css" href="/resources/css/channel.css" />
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

		<table class="channel_header" border=0>
			<tr>
				<td class="channel_header__profile_image" rowspan="2">
					
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
						<a class="channel_header__modify" v-on:click="toggleProfileImageSetter">
							<img src="/resources/image/modify_icon.png" class="channel_header_setting_img" />
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
				<td class="channel_header__letter" colspan="2"><h1>
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
			<tr><c:choose>
				<c:when test="${member.id eq member_id and empty member.ownChannelId}">
					<td class="channel_header__letter"> subscriber </td>
					<td><button class="btn" type="button" v-on:click="createChannel">Create Channel</button></td>
				</c:when>
				<c:otherwise>
					<td colspan="2" class="channel_header__letter"> subscriber </td>
				</c:otherwise>
			</c:choose></tr>
		</table>
	
		<div class="channel_info_content_wapper">
			<nav class="menu" id="myMenu">
				<ul>
					<!-- active link and hover indicator, menu slider -->
					<div class="m-active"></div>

					<!-- menu links -->
					<c:if test="${not empty member.ownChannelId}">
					
						<li><a v-on:click="changeNav('home')" id="home_btn">Home</a></li> 
						<li><a v-on:click="changeNav('video')" id="video_btn">Video</a></li>
						
					</c:if>
					<c:if test="${not empty member.ownCommunityId}">
					
						<li><a v-on:click="changeNav('community')" id="community_btn">Community</a></li>
					
					</c:if>
					
					<li><a v-on:click="changeNav('about')" id="about_btn">About</a></li>
					<li><a v-on:click="changeNav('contact')" id="contact_btn" >Contact</a></li>
				</ul>
			</nav>

			<div class="channel_info_content">
				<div class="channel_home">
					<h1> Home </h1>
				</div>
				
				<div class="channel_video">
					<h1> Video </h1>
				</div>
				
				<div class="channel_community">
					<h1> Community </h1>
					
					
				</div>

				<div class="channel_about">
					<h1 class="channel_alias"> About
						<c:choose>
							<c:when test="${empty member.alias}">${member.name} </c:when>
							<c:otherwise>${member.alias} </c:otherwise>
						</c:choose>
					</h1>

					<c:set var="member_id" value="<%=member_id%>" />
					<c:if test="${member.id eq member_id}">
						<a class="channel_about__modify">
							<img src="/resources/image/setting_icon.png" class="channel_about_setting_img" />
						</a>
					</c:if>
					
					<p class="channel_message">${member.message}</p>
				</div>

				<div class="channel_contact">
					<h1> Contact </h1>
					
					<table>
						<c:if test="${not empty member.name}">
							<tr>
								<td width='50px'> name </td>
								<td class="channel_contact__right"> ${member.name} </td>
							</tr>
						</c:if>
					
						<c:if test="${not empty member.email}">
							<tr>
								<td width='50px'> email </td>
								<td class="channel_contact__right"> ${member.email} </td>
							</tr>
						</c:if>
						
						<c:if test="${not empty member.phone}">
							<tr>
								<td width='50px'> phone </td>
								<td class="channel_contact__right"> ${member.phone} </td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>

		</div>
	</div>

	<!-- JS -->
	<script type="text/javascript" src="/resources/js/store.js"></script>

	<script type="text/javascript" src="/resources/js/index.js"></script>
	<script type="text/javascript" src="/resources/js/view/header.js"></script>
	<script type="text/javascript" src="/resources/js/view/login.js"></script>
	<script type="text/javascript" src="/resources/js/componant/channel.js"></script>
	
	<script type="text/javascript" src="/resources/js/util/action.js"></script>
	<script type="text/javascript" src="/resources/js/util/nav.js"></script>

</body>
</html>
