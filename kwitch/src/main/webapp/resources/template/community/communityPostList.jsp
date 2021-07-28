<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>

<link rel="stylesheet" type="text/css" href="/resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/header.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/community.css" />

</head>
<body style="width: 100%; height: 100%;">

	<%
        session = request.getSession();
        String memberId = (String)session.getAttribute("member_id");
    %>
	
	<div id="communitPostList">

		<div class="app community_post_list">
		
			<h2> {{menuInfo.title}} </h2>
	
			<ul class="communityBasicThumb">
	
				<li v-for="(postItem, index) in postList">
					
					<div class="community_basic_thumb" v-on:click="goToPost(postItem.id)">
						<div class="user_profile_img_wapper">
							<img src="/resources/image/user_icon.png" class="default_user_profile_img" />
						</div>
						<div class="community_basic_thumb__content_wapper">
							<div class="community_basic_thumb__title">
								<h3> {{ postItem.title }} </h3>
							</div>
						
							<div class="community_basic_thumb__content"> 
								<p> 
									{{ postItem.writerAlias }} <br/>
									번호 / {{index+1}} / 작성 일자 / {{postItem.createdAt}} /  {{postItem.menuTitle}} 
								</p> 
								
							</div>
						</div>
						
						
					</div>

				<li>
	
			</ul>
			
			<c:set var="memberId" value="<%=memberId%>" />
			<c:if test="${not empty memberId}">
				<a class="community_post_list__modify"
				   v-on:click="goToPostUpload">
	            	write post
	            </a>
	        </c:if>
	
		</div>
	
	</div>
	
	<script>
    
    window.onload = function() {
        const channel = new Vue({
	        el: '#communitPostList',
	        data: {
	        	menuInfo: {},
	            postList: [],
	        },
	        methods: {
	        	
	            uploadProfileImage: function(id) {
	            	const formData = new FormData();
	            	const profileImage = document.getElementById('upload_profile_image');
	            	
	            	formData.append('profileImage', profileImage.files[0]);
	            	
	            	
	            },
	            
	            goToPost: function(id) {
	            	parent.document.location.href = "/community/post/"+id;
	    		},
	    		
	    		goToPostUpload: function() {
	    			parent.document.location.href = "/community/post/upload/"+this.menuInfo.communityId;
	    		}
	            
	        },
	
	        mounted() {
	        	
	        	fetch('/api/community/menu/' + parent.document.all["menuId"].value + '/info', {
          		  	method: 'GET',
	          		headers: {
	        			"Content-Type": "application/json"
	        		}
      			})
              	.then(res => res.json())
              	.then(json => {

					this.menuInfo = json;

              	})
              	.catch(err => console.log(err))
	        	
	        	
	        	
	        	fetch('/api/community/menu/' + parent.document.all["menuId"].value, {
          		  	method: 'GET',
	          		headers: {
	        			"Content-Type": "application/json"
	        		}
      			})
              	.then(res => res.json())
              	.then(json => {

					this.postList = json;
					console.log(this.postList);

              	})
              	.catch(err => console.log(err))
	        }
        })
    }
    
</script>
 
</body>
</html>