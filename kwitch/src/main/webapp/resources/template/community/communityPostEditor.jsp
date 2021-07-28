<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home</title>

	<!-- Font -->
	<link rel="stylesheet" href="//cdn.jsdelivr.net/font-nanum/1.0/nanumbarungothic/nanumbarungothic.css"/>
	
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="/resources/css/common.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/editor.css" />
	
</head>
<body>
	<%
        session = request.getSession();
        String memberId = (String)session.getAttribute("member_id");
    %>

	<div id="communityPostEditor">
		<table class="app editor_title">
			<tr>
				<td class="editor_title_latter"><h3>title</h3> </td>
				<td class="editor_title_input">
					<input type="text" class="form__input editor" v-model="postContent.title"/> </td>

				<td class="editor_setting_latter"><h3>menu</h3></td>
				
				<td v-if="postId" class="editor_setting_input">
					{{postContent.menuTitle}}
				</td>
				<td v-else class="editor_setting_input">
					<select class="input form__input from__birth_input w_center"
							v-model="selectedMenuId">
						<option v-for="menu in menuList" v-bind:value="menu.id">{{menu.title}}</option>
					</select>
				</td>
			</tr>
		</table>
	
		<div class="app">			
			<textarea name="content" id="editor">
				{{postContent.content}}
			</textarea>
			<p>
				<button type="button" id="back" class="btn"> Back </button>
				<button type="button" v-on:click="uploadPost" id="submitPost" class="btn"> Upload </button>
			</p>
		</div>
	</div>
	
	<!-- CDN -->
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>
	
	<!-- CK Editer CDN -->
	<script src="https://cdn.ckeditor.com/ckeditor5/29.0.0/classic/ckeditor.js"></script>
	
	<script>
    window.onload = function() {
        const detail = new Vue({
	        el: '#communityPostEditor',
	        data: {
	        	editor: undefined,
	        	postContent: {
	        		title: '',
	        		menuList: null,
	        		menuTitle: null,
	        	},
	        	postId: null,
	        	communityId: null,
	        	menuList: [],
	        	selectedMenuId: '',
	        },
	        methods: {
	        	
	            uploadPost: function() {
					const editorData = this.editor.getData();
					
					if( ! this.postContent.title) {
						alter("title is empty");
						return
					}
					
					if( ! editorData) {
						alter("content is empty");
						return
					}
					
					
						
					if(this.postId) {
						fetch('/api/community/post/' + this.postId, {
		          		  	method: 'PUT',
			          		headers: {
			        			"Content-Type": "application/json"
			        		},
			                body: JSON.stringify({
			                    "title": this.postContent.title,
			                    "content": editorData,
			                    "isCommentBlock": false,
			                })
			        		
		      			})
		              	.then(res => {
		              		if(res.status == 200) {
		              			if(this.postId) {
		              				parent.document.location.href = "/community/post/"+this.postId;
		              			}
		              		}
		              	})
		              	.catch(err => console.log(err))
					}
					
					
					
					if( ! this.selectedMenuId) {
						alter("not select menu");
						return
					}
					
					if(this.communityId) {
						console.log(this.communityId);
						
						fetch('/api/community/post/', {
		          		  	method: 'POST',
			          		headers: {
			        			"Content-Type": "application/json"
			        		},
			                body: JSON.stringify({
			                    "communityId": this.communityId,
			                    "menuId": this.selectedMenuId,
			                    "title": this.postContent.title,
			                    "content": editorData,
			                    "isCommentBlock": false,
			                })
			        		
		      			})
		              	.then(res => {
		              		if(res.status == 200) {
		              			return res.text();
		              		}
		              		else {
		              			alter("error");
		              		}

		              	})
		              	.then(txt => {
		              		parent.document.location.href = "/community/post/"+txt;
		              	})
		              	.catch(err => console.log(err))
					}
	            },
	            
	            createCKEditor: function() {
	            	
	            	ClassicEditor
			        .create( document.querySelector( '#editor' ), {
//			    		ckfinder: {
//			    			uploadUrl: '/asdf/asdf'
//			    		},
//						alignment: {
//							options: [ 'left', 'center', 'right' ]
//						}
			        } )
			        .then( (newEditer) => {
			        	this.editor = newEditer;
			        })
			        .catch( (error) => {
			            console.error( error );
			        } );
	            	
	            }
	            
	        },
	        
	        mounted(){
	        	const communityId = parent.document?.all["communityId"]?.value;
	        	const postId = parent.document?.all["postId"]?.value;
	        	
	        	this.postId = postId?postId:null;
	        	this.communityId = communityId?communityId:null;
	        	this.isExist = postId?true:false;
		        	
	        	if(this.postId) {
		        	fetch('/api/community/post/' + this.postId, {
	          		  	method: 'GET',
		          		headers: {
		        			"Content-Type": "application/json"
		        		}
	      			})
	              	.then(res => res.json())
	              	.then(json => {
						this.postContent = json;
						this.createCKEditor();
	              	})
	              	.catch(err => console.log(err))
	        	} 
	        	
	        	if(this.communityId){
	        		fetch('/api/community/' + this.communityId + '/menu', {
	          		  	method: 'GET',
		          		headers: {
		        			"Content-Type": "application/json"
		        		}
	      			})
	              	.then(res => res.json())
	              	.then(json => {
						this.menuList = json;
						this.createCKEditor();
	              	})
	              	.catch(err => console.log(err))
	        	}
	        }
		})
    }
	</script>


	<!-- JS -->
	<script type="text/javascript" src="/resources/js/util/action.js"></script>
	
	<script src="https://cdn.ckeditor.com/ckeditor5/29.0.0/classic/ckeditor.js"></script>
    
</body>
</html>
