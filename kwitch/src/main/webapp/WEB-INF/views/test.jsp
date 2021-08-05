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
			
	<source src="https://trainee-rdb-uomfa.run.goorm.io/hls/stream.m3u8" type="application/x-mpegURL">
	</video>

	<script src="https://vjs.zencdn.net/7.11.4/video.min.js"></script>
	
</body>
</html>