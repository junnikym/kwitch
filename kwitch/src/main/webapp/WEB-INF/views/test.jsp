<!DOCTYPE html>
<html>
<head>
	<meta charset=utf-8 />
	<title>hls.js</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="https://vjs.zencdn.net/7.11.4/video-js.css" rel="stylesheet" />
	<link href="https://unpkg.com/@videojs/themes@1/dist/forest/index.css" rel="stylesheet">

</head>
<body> 

	<video id="my-video"
			class="video-js vjs-theme-forest"
			controls
			preload="auto"
			width="640"
			height="264"
			poster="MY_VIDEO_POSTER.jpg"
			data-setup="{}">
		 
		<source src="http://172.18.228.225/contents/4/0063569984/playlist.m3u8" type="application/x-mpegURL">
	</video>

	<script src="https://unpkg.com/video.js/dist/video.js"></script>
	<script src="https://unpkg.com/videojs-flash/dist/videojs-flash.js"></script>
	<script src="https://unpkg.com/videojs-contrib-hls/dist/videojs-contrib-hls.js"></script>
	
</body>
</html>