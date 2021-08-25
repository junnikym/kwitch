<div class="modal channel_video_modal" id="modal-video">
    <div class="modal-bg modal-exit" v-on:click="pauseVideo"></div>

    <div class="h_center" id="channelVideo">

        <div class="app form">
        	
        	<div class="channel_video_contents">
	        	<div class="channel_video_wrapper">
				
					<video  id="channelVideoStream" 
							ref="channelVideoStream" 
							class="video-js vjs-theme-forest chat_switched_elem vjs-16-9" 
							controls preload="auto">
							
						<source src="" type="application/x-mpegURL">
					</video>
				</div>
				
				<div class="channel_video_info">
					<h2>{{video?.title}}</h2>

					<div class="horizontal_rule"></div>

					<liked-component v-bind:targetId="this.id"
									 usage="LIKE_USAGE_VIDEO"
									 parentElem="#channelVideo"></liked-component>

					<div class="horizontal_rule"></div>

					<p>{{video?.text}}</p>
				</div>

			</div>
			
			<div class="channel_video_comment"></div>
        </div>

    </div>

    <button class="modal-close modal-exit" id="model_video_close" style="display: none;" type="button"></button>
</div>