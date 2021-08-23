<div class="modal channel_video_modal" id="modal-video">
    <div class="modal-bg modal-exit" v-on:click="reset"></div>

    <div class="h_center" id="channelVideo">

        <div class="app form">
        	
        	<div class="channel_video_contents">
	        	<div class="channel_video_wrapper">
				
					<video  id="channelVideoStream" 
							ref="channelVideoStream" 
							class="video-js vjs-theme-forest chat_switched_elem vjs-16-9" 
							controls preload="auto">
							
						<source src="http://172.18.235.107/contents/5/0038275005/playlist.m3u8" type="application/x-mpegURL">
					</video>
				</div>
				
				<div class="channel_video_info">
					<h2> Title </h2>
					<p>말 말 말 말 말 말 말 말 말 말 말 말 말 말 말 말 말 </p>
				</div>
			</div>
			
			<div class="channel_video_comment"></div>
        </div>

    </div>

    <button class="modal-close modal-exit" id="model_video_close" style="display: none;" type="button"></button>
</div>