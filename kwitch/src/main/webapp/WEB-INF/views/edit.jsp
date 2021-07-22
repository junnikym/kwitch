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
	<link rel="stylesheet" type="text/css" href="/resources/css/header.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/nav.css" />
	
</head>
<body>
	<%@ include file="header.jsp" %>

	<div id="content">
		<div class="app">
			<textarea name="content" id="editor">
				&lt;p&gt;This is some sample content.&lt;/p&gt;
			</textarea>
			<p><input type="submit" id="submitPost" class="btn"></p>
		</div>
	</div>
	
	<!-- Vue.js CDN -->
	<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
	
	<!-- CK Editer CDN -->
	<script src="https://cdn.ckeditor.com/ckeditor5/29.0.0/classic/ckeditor.js"></script>
	
	<script>
		let editor;
	
	    ClassicEditor
	        .create( document.querySelector( '#editor' ), {
//	    		ckfinder: {
//	    			uploadUrl: '/asdf/asdf'
//	    		},
//				alignment: {
//					options: [ 'left', 'center', 'right' ]
//				}
	        } )
	        .then( (newEditer) => {
	        	editor = newEditer;
	        })
	        .catch( (error) => {
	            console.error( error );
	        } );
	    
	    document.querySelector( '#submitPost' ).addEventListener( 'click', (e) => {
	        const editorData = editor.getData();

	        console.log(editorData);
	    } );
	</script>


	<!-- JS -->
	<script type="text/javascript" src="/resources/js/store.js"></script>

	<script type="text/javascript" src="/resources/js/view/index.js"></script>
	<script type="text/javascript" src="/resources/js/view/header.js"></script>
	<script type="text/javascript" src="/resources/js/view/login.js"></script>
	
	<script type="text/javascript" src="/resources/js/util/action.js"></script>
	
	<script src="https://cdn.ckeditor.com/ckeditor5/29.0.0/classic/ckeditor.js"></script>
    
</body>
</html>
