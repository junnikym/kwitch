<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html style="background: none;">
<head>
	<meta charset="UTF-8">

	<!-- Font -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/font-nanum/1.0/nanumbarungothic/nanumbarungothic.css"/>
	
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="/resources/css/common.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/header.css" />

	<!-- Vue -->
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>

	<!-- JS -->
	<script type="text/javascript" src="/resources/js/store.js"></script>
	<script type="text/javascript" src="/resources/js/util/pageHandler.js"></script>
	
</head>
<body style="background: none;">
	<div class="h_center h_center_wrapper" id="login">

		<div class="app form h_center_content" id="loginForm">

			<table border="0" class="login_stage" >
				<tr><td colspan="2" class="w_center">
					<h2> Login </h2>
				</td></tr>

				<tr>
					<td><h3> email </h3> </td>
					<td>
						<input class="input form__input"
							   name="email" type="text"
							   v-model="emailInput"/>
					</td>
				</tr>

				<tr>
					<td><h3> password </h3> </td>
					<td>
						<input class="input form__input"
							   name="password" type="password"
							   v-model="pwInput"/>
					</td>
				</tr>

				<tr><td colspan="2" class="w_center">
					<br/>
					<button class="btn" v-on:click="modalClose"> Back </button>
					<button class="btn" v-on:click="stageSwitch"> Login </button>
				</td></tr>

				<tr><td colspan="2" class="w_center">
					<br/>
					<a href="/regist">sign up</a>
					<br/>
					<br/>
					<div class="warning" v-if="wrongInput!=0" v-cloak>Incorrect username or password.</div>
				</td></tr>

			</table>

			<table class="captcha_stage">
				<tr>
					<td> <img src="/captcha/image" class="captcha_image" /> </td>
					<td>
						<button class="btn" type="button" v-on:click="resetCaptcha"> Reset </button>
					</td>
				</tr>
				<tr><td colspan="2" class="w_center">
					<input class="input form__input" type="text" onkeypress="inNumber();" v-model="captchaInput" maxlength="6"/>
				</td></tr>
				<tr><td colspan="2" class="w_center">
					<button class="btn" type="button" v-on:click="stageSwitch"> back </button>
					<button class="btn" type="button" v-on:click="checkAndLogin"> Check </button>
				</td></tr>
				<tr><td colspan="2" class="w_center">
					<div class="warning" v-if="wrongCaptcha!=0" v-cloak>Incorrect Character</div>
				</td></tr>
			</table>

		</div>

		<script>
			window.onload = function() {
				loadScript('/resources/js/view/login.js');
			}
		</script>

	</div>

</body>
</html>