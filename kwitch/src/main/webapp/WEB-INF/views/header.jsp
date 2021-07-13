<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal" id="modal-one">
  <div class="modal-bg modal-exit"></div>
  <div class="modal-container">
    <div data-include-path="login"></div>
  </div>
</div>

<%
	session = request.getSession();
	String id = (String)session.getAttribute("member_id");
%>
        
<div id="header">

	<div class="head_logo_wapper">
		<img src="/resources/image/kwitch_icon.png" class="head_logo head_logo_shadow" />
		<img src="/resources/image/kwitch_icon.png" class="head_logo head_logo_main" />
	</div>
	
	<div class="search_wapper">
		<input class="input form__input from__search_input w_center" type="text" v-model="searchInput"/>
		<button class="btn btn__search h_center_wrapper" type="button"><img src="/resources/image/search_icon.png" class="btn__search_icon h_center_content" /></button>
	</div>

	<c:choose>
		<c:when test="${empty id}">
			<p>
				<%=id%>
			</p>
		</c:when>
		<c:otherwise>
		<div v-else class="container">
			<a data-modal="modal-one">Open Modal</a>
		</div>
		</c:otherwise>
	</c:choose>
</div>

