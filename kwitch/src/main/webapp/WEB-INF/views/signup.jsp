<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<%
	Date today = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy");
	int year =  Integer.parseInt(sf.format(today));
%>

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
	<link rel="stylesheet" type="text/css" href="../../resources/css/signin.css">

	<!-- Vue -->
	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>

</head>
<body>

	<div class="app h_center h_center_wrapper">
		
		<form class="app form h_center_content"
			  name="signupForm" id="signupForm"
			  v-on:submit="signup">
		
			<table border="0">
				<tr><td colspan="2" class="w_center">
					<h2> Sign Up </h2>
					<hr/>
				</td></tr>
	
				<tr>
					<td><h3> email </h3> </td>
					<td>
						<input class="input form__input"
							   name="email" type="email"
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
				
				<tr>
					<td><h3> name </h3> </td>
					<td>
						<input class="input form__input"
							   name="name" type="text"
							   v-model="nameInput"/>
					</td>
				</tr>
				
				<tr>
					<td><h3> alias </h3> </td>
					<td>
						<input class="input form__input"
							   name="alias" type="text"
							   v-model="aliasInput"/>
					</td>
				</tr>
				
				<tr>
					<td><h3> phone </h3> </td>
					<td>
						<div class="w_center">
							<b class="form__phone_input"> 010 &nbsp; - &nbsp; </b>
							<input class="input form__input form__phone_input w_center"
								   name="phone_mid" type="text"
								   v-model="phoneMidInput"/>
							<b> &nbsp; - &nbsp; </b>
							<input class="input form__input form__phone_input w_center"
								   name="phone_last" type="text"
								   v-model="phoneLastInput"/>
						</div>
					</td>
				</tr>
				<tr><td colspan='2' class='hr_light'><hr/></td></tr>
				
				<tr>
					<td><h3> birth </h3> </td>
					<td>
						<select name='birthYear' v-model="birthYearInput"
								class="input form__input from__birth_input w_center">
							<option v-bind:value="{ number:'<%=year%>' }" selected><%=year%></option>
							<% for(int i=year-1; i>=year-100; i--){%>	
							<option v-bind:value="{ number:'<%=i%>' }"><%=i%></option>		 
							<%}%>
						</select>
						<b> &nbsp; Year &nbsp; </b>
						
						<select name='birthYear' v-model="birthMonthInput"
								class="input form__input from__birth_input w_center">
							<option v-bind:value="{ number:'01' }" selected>1</option>
							<% for(int i=2; i<=12; i++){%>	
							<option v-bind:value="{ number:'<%=String.format("%02d", i)%>'  }"><%=i%></option>		 
							<%}%>
						</select>
						<b> &nbsp; Month &nbsp; </b>
						
						<select name='birthYear' v-model="birthDayInput"
								class="input form__input from__birth_input w_center">
							<option v-bind:value="{ number:'01' }" selected>1</option>
							<% for(int i=2; i<=31; i++){%>	
							<option v-bind:value="{ number:'<%=String.format("%02d", i)%>'  }"><%=i%></option>		 
							<%}%>
						</select>
						<b> &nbsp; Day &nbsp; </b>
					</td>
				</tr>
				
				<tr><td colspan='2' class='hr_light'><br/></td></tr>
	
				<tr><td colspan="2" class="w_center">
					<button class="btn" v-on:click="back()"> Back </button>
					<button class="btn" type="submit"> Submit </button>
				</td></tr>
	
			</table>
		
		</form>
	
	</div>
	
	<script type="text/javascript" src="../../resources/js/view/signup.js"></script>
</body>
</html>
