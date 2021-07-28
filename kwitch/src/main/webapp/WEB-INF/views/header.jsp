<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal" id="modal-login">
	<div class="modal-bg modal-exit"></div>
	<iframe src="/resources/template/login.jsp" class="modal-container app" id="login_iframe"
			frameborder="0" scrolling="no" style="display: block;" ></iframe>

	<button class="modal-close modal-exit" id="model_login_close" style="display: none;" type="button"></button>
</div>

<%
	session = request.getSession();
	String member_id = (String)session.getAttribute("member_id");
	String member_name = (String)session.getAttribute("member_name");
	String member_alias = (String)session.getAttribute("member_alias");
	String member_profile_image = (String)session.getAttribute("member_profile_image");
	String member_profile_image_ext = (String)session.getAttribute("member_profile_image_ext");
%>
        
<div id="header">

	<div class="head_logo_wapper" onClick="location.href='/'">
		<img src="/resources/image/kwitch_icon.png" class="head_logo head_logo_shadow" />
		<img src="/resources/image/kwitch_icon.png" class="head_logo head_logo_main" />
	</div>
	
	<div class="search_wapper">
		<input class="input form__input from__search_input w_center" type="text" v-model="searchInput"/>
		<button class="btn btn__search h_center_wrapper" type="button"><img src="/resources/image/search_icon.png" class="btn__search_icon h_center_content" /></button>
	</div>

	<c:choose>

		<c:when test="${empty member_id}">
			<div class="container account_btn_wapper">
				<a data-modal="modal-login" class="btn login_btn">Login</a>
				<a class="btn signup_btn" href="/regist"> SignUp </a>
			</div>
		</c:when>

		<c:otherwise>
			<div class="dropdown">
				<button onclick="dp_menu()" class="button">
					<div class="user_profile_img_wapper">
						<c:choose>
							<c:when test="${empty member_profile_image || empty member_profile_image_ext}">
								<img src="/resources/image/user_icon.png" class="default_user_profile_img" />
							</c:when>
							<c:otherwise>
								<img src="/api/profile/image/<%=member_profile_image%>/<%=member_profile_image_ext%>" class="user_profile_img" />
							</c:otherwise>
						</c:choose>
					</div>
				</button>


				<div style="display: none;" id="drop-content">
					<a v-on:click="channel('<%=member_id %>')"> profile </a>
					<a v-on:click="logout" class="logout_btn"> logout </a>
				</div>
			</div>
		</c:otherwise>

	</c:choose>
	
	<% //session.invalidate(); %>
</div>

