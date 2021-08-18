<div id="mainPage">

	<div class="live_stream_bg">
		<div class="live_stream_wapper">
			<video id="streamingVideo" ref="streamingVideo" width=960 height=540 class="video-js vjs-theme-forest" controls preload="auto">
				<source
						src="https://trainee-rdb-uomfa.run.goorm.io/hls/stream.m3u8"
						type="application/x-mpegURL"
						crossorigin="use-credentials">

				<a target="_blank"> Not Streaming Right Now </a>
			</video>
		</div>
	</div>

	<div class="app main_page_content">
		<h1 class="main_page_header"> Brand New Channel </h1>

		<div v-if="newChannelList.length"
			 class="main_page_new_channel_list">
			<channel-simple-item-component v-for="ch in newChannelList" v-bind:channel="ch"></channel-simple-item-component>
		</div>
		<div v-else
			 class="main_page_not_found" >

			<h3> Not Found </h3>
		</div>

		<h1 class="main_page_header"> Hot Clip </h1>
		<div class="main_page_hot_post_list">
		</div>

		<div>adsfasdfasdfasdfsa</div>
		
		<h1 class="main_page_header"> New Video </h1>
		<div class="main_page_hot_post_list">
		</div>

		<div>adsfasdfasdfasdfsa</div>
	</div>

</div>