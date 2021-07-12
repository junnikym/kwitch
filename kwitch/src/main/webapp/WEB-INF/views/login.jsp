<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>

	<!-- Font -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro&display=swap" rel="stylesheet">

	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="../../resources/css/common.css">

	<!-- Vue -->
	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>

</head>
<body>
	<div class="app h_center h_center_wrapper">
		<form class="app form h_center_content"
			  name="loginForm" id="loginForm"
			  v-on:submit="login">
	
			<table border="0">
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
					<button class="btn" type="submit"> Login </button>
				</td></tr>
	
			</table>
	
		</form>
	</div>
	
	<script type="text/javascript" src="../../resources/js/view/login.js"></script>
</body>
</html>
