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
<body>
	
	<div id="communitHome">

		<div class="app community_home_menu" v-for="item in communityContent">
		
			<h2> {{item.title}} </h2>
			
			<a class="more_btn" v-on:click="goToPostList(item.id)"> more </a>
	
			<ul class="communityBasicThumb">
	
				<li v-for="postItem in item.post" v-on:click="goToPost(postItem.id)">
					<div class="community_basic_thumb" border="0">
						<div class="user_profile_img_wapper">
							<img src="/resources/image/user_icon.png" class="default_user_profile_img" />
						</div>
	
						<div class="community_basic_thumb__content_wapper">
							<div class="community_basic_thumb__title"> <h3> {{ postItem.title }} </h3> </div>
	
							<div class="community_basic_thumb__content"> <p> {{ postItem.content }} </p> </div>
						</div>
	
						<div rowspan="2"
							class="commnutiy_basic_thumb__image">
							<img src="/resources/image/kwitch_background_icon.png" class="community_basic_thumb__background_image" />
						</div>
	
					</div>
				<li>
	
			</ul>
	
		</div>
	
	</div>
	
	<script>
    
    window.onload = function() {
        const detail = new Vue({
	        el: '#communitHome',
	        data: {
	            communityContent: [],
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
	    		
	    		goToPostList: function(id) {
	            	parent.document.location.href = "/community/menu/"+id;
	    		}
	            
	        },
	
	        mounted() {
	        	
	        	fetch('/api/community/' + parent.document.all["communityId"].value, {
          		  	method: 'GET',
	          		headers: {
	        			"Content-Type": "application/json"
	        		}
      			})
              	.then(res=>res.json())
              	.then(json => {

					json['menu'].forEach ( menu_it => {
							this.communityContent.push(
									Object.assign(
										menu_it,
										{'post': json['post'].filter ( post_it => post_it.menuId === menu_it.id ) }
									)
							)
					});

					console.log(this.communityContent);


              	})
              	.catch(err => console.log(err))
	        }
        })
    }
    
</script>
 
</body>
</html>