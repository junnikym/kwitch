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
	
	<div id="communitPost">
		
		<div class="community_basic_thumb">
			<div class="user_profile_img_wapper">
				<img src="/resources/image/user_icon.png" class="default_user_profile_img" />
			</div>
			<div class="community_basic_thumb__content_wapper">
				<div class="community_basic_thumb__title">
					<h3> {{ postContent.title }} </h3>
				</div>
			
				<div class="community_basic_thumb__content"> 
					<p> 
						{{ postContent.writerAlias }} <br/>
						/ 작성 일자 / {{postContent.createdAt}} /  {{postContent.menuTitle}} 
					</p> 
					
				</div>
			</div>
			
			
		</div>
		
		<div class="app community_post__content">
			<a v-if="postContent.writerId == '<%=memberId%>'"
               class="community_post__modify"
               v-on:click="goToEditor">
            	수정
            </a>
            
            <div v-html="postContent.content"></div>
			
			<div>
				<button> 좋아요 </button>
				<button> 싫어요 </button>
			</div>
		</div>
	</div>
	
	<script>
    
    window.onload = function() {
        const channel = new Vue({
	        el: '#communitPost',
	        data: {
	            postContent: {},
	            urlId: ''
	        },
	        methods: {
	        	
	            uploadProfileImage: function(id) {
	            	const formData = new FormData();
	            	const profileImage = document.getElementById('upload_profile_image');
	            	
	            	formData.append('profileImage', profileImage.files[0]);
	            	
	            	
	            },
	        
	            goToEditor: function(id) {
	            	parent.document.location.href = "/community/post/"+this.urlId+"/edit";
	            }
	            
	        },
	
	        mounted() {
	        	this.urlId = parent.document.all["postId"].value;
	        	
	        	fetch('/api/community/post/' + this.urlId, {
          		  	method: 'GET',
	          		headers: {
	        			"Content-Type": "application/json"
	        		}
      			})
              	.then(res => res.json())
              	.then(json => {

					this.postContent = json

              	})
              	.catch(err => console.log(err))
	        }
        })
    }
    
</script>
 
</body>
</html>