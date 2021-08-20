<div class="modal channel_video_upload_modal" id="modal-video-upload">
    <div class="modal-bg modal-exit" v-on:click="reset"></div>

    <div class="h_center h_center_wrapper" id="channelVideoUpload">

        <div class="app form h_center_content">
        	
        	<h4 class="video_upload_head_text">Title</h4>
        	<input class="input form__input"
				   type="text"
				   v-model="videoTitle"/>

            <div class="drag-area">
            	<div class="drag-area-text">
	                <div class="cloud_upload_icon"></div>
	                <b class="drag-text">Drag & Drop to Upload File</b>
	                <p>or</p>
	                <b class="upload-button">Browse File</b>
	            </div>
                <input class="upload-input" type="file" hidden />
            </div>
            
            <h4>Description</h4>
            <textarea class="channel_video_upload_text input form__input" v-model="videoText">	
			</textarea>
			
			<div class="channel_video_upload_btns" >
				<button class="btn" type="button" v-on:click="modalClose"> back </button>
				<button class="btn" type="button" v-on:click="upload"> Upload </button>
			</div>
	
        </div>

    </div>

    <button class="modal-close modal-exit" id="model_video_upload_close" style="display: none;" type="button"></button>
</div>