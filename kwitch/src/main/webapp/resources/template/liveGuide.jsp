<div id="LiveGuide">

	<h1> Live Streaming Guide </h1>
	
	<div class="guide_step">
		<div class="guide_num">1</div>
	
		<div class="guide_img">
			<img src="/resources/image/key_icon.png"/></button>
		</div>
		<div class="guide_text">
			<h2> Get your stream key </h2>
			<div class="guide_stream_key">
				<div class="guid_stream_key_item_head"> key </div>
				<div class="guid_stream_key_item_content"> 
					{{ ownLiveStream?.id }} </div>
				<button type="button" 
						class="guid_stream_key_item_btn"
						v-on:click="registLive"> GET </button>
			</div>
			<div v-if="!ownLiveStream?.startAt">
				<br/>
				<h3>Warning</h3>
				<p>press this button after step 4 </p>
				
				<button class="btn"  type="button" v-on:click="startLive"> Start Streaming </button>
			</div>
			
			<div v-else-if="ownLiveStream?.startAt && !ownLiveStream?.endAt">
				<br/>
				<h3>Stop to Live Streaming</h3>
				
				<button class="btn"  type="button" v-on:click="stopLive"> Stop Streaming </button>
			</div>
		</div>
	</div>
	
	<div class="guide_step">
		<div class="guide_num">2</div>
	
		<div class="guide_img">
			<img src="/resources/image/obs_icon.png"/></button>
		</div>
		<div class="guide_text">
			<h2> Install OBS </h2>
			download OBS from <a href="https://obsproject.com/ko/download"> here </a>
		</div>
	</div>
	
	<div class="guide_step">
		<div class="guide_num">3</div>
	
		<div class="guide_img half_size">
			<img src="/resources/image/setting_icon.png"/></button>
		</div>
		<div class="guide_text">
			<h2> OBS Setting </h2>
			<p> 1. Run OBS after install</p>
			<p> 2. File > Setting > Streaming </p>
			<p> 3. change the service to custom</p>
			<p> 4. Server URL : rtmp://13.125.90.40:51312/show/ </p>
			<p> 5. Copy the key at first step and paste it at stream key </p>
		</div>
	</div>
	
	<div class="guide_step">
		<div class="guide_num">4</div>
	
		<div class="guide_img">
			<img src="/resources/image/kwitch_icon.png"/></button>
		</div>
		<div class="guide_text">
			<h2> Start Live Streaming </h2>
			<p> 1. Setting your streaming screen and press the start streaming button on OBS</p>
			<p> 2. Start live streaming by press the start streaming button at first step </p>
			
			<h2> Stop Live Streaming </h2>
			<p> Click the stop streaming button at first step </p>
		</div>
	</div>

</div>