
<div id="channel">
	<channel-video-component v-bind:id="cursoredVideoId"></channel-video-component>

	<channel-video-upload-component></channel-video-upload-component>
	
	<div id="live_streaming">
		<div class="live_stream_bg chat_switched_elem">
			<div class="live_stream_wapper">
			
				<video  id="streamingVideo" 
						ref="streamingVideo" 
						width=960 height=540 
						class="video-js vjs-theme-forest chat_switched_elem" 
						controls preload="auto">
						
					<source type="application/x-mpegURL"
					  		crossorigin="use-credentials">
				</video>
			</div>
			<h2 class="no_live_stream">
				NO LIVE STREAMING
			</h2>
		</div>
	</div>
	
	<channel-chat-component v-bind:channelId="member?.ownChannelId"></channel-chat-component>

	<div class="channel_info " >
	
		<!--------------------------------------------------------------
				[ Profile Header ]
		 -------------------------------------------------------------->
	
		<table class="channel_header" border=0>
	
			<tr>
				<td class="channel_header__profile_image" rowspan="2">
	
					<!--  Profile Image  -->
	
					<div class="user_profile_img_wapper__channel">
						<img v-bind:src="profileImageURL" class="default_user_profile_img" />
					</div>
	
					<!--  Profile Setting Button  -->
	
					<a v-if="this.$store.state.member.id == member.id"
					   class="channel_header__modify"
					   v-on:click="toggleProfileImageSetter">
	
						<img src="/resources/image/modify_icon.png" class="channel_header_setting_img" />
					</a>
	
					<!--  Profile Image Setting Modal  -->
	
					<div v-if="this.$store.state.member.id == member.id"
						 class="set_profile_image">
	
						<div id="set_profile_image__thumb"> <img /> </div>
	
						<div class="set_profile_image__uploader">
							<input type="file" name="profile_image" id="upload_profile_image" @change="setProfileImageThumb"/>
							<button class="btn"  type="button" v-on:click="toggleProfileImageSetter">cancel</button>
							<button class="btn"  type="button" v-on:click="uploadProfileImage('this.$store.state.member.id')">Finish</button>
						</div>
					</div>
				</td>

				<!--  Profile Letter Infomation In Header  -->
	
				<td class="channel_header__letter" colspan="2">
					<h1>{{member.alias}}</h1>
				</td>
			</tr>
	
			<tr v-if="this.$store.state.member.id == member.id && !(member?.ownChannelId)">
					<td class="channel_header__letter"> subscriber </td>
					<td><button class="btn" type="button" v-on:click="createChannel">Create Channel</button></td>
			</tr>
			<tr v-else-if="member?.ownChannelId">
			
					<td colspan="2" class="channel_header__letter"> {{member.nsubscribe}} subscriber 
						<button v-if="this.$store.state.member.id  
										&& this.$store.state.member.id != member.id
										&& isSubscribed == false" 
								class="btn" type="button" 
								v-on:click="subscribe">
								Subscribe</button>
								
						<button v-if="this.$store.state.member.id 
										&& this.$store.state.member.id != member.id 
										&& isSubscribed == true" 
								class="btn" 
								type="button" 
								v-on:click="unsubscribe">
								Unsubscribe</button>
					</td>
					<td>
						<button v-if="this.$store.state.member.id  
										&& this.$store.state.member.id == member.id
										&& (member?.ownChannelId)" 
								class="btn" type="button" 
								v-on:click="goToLiveStreaming">
								
								Live Streaming
						</button>
					</td>
			</tr>
	
		</table>
	
		<!--------------------------------------------------------------
				[ Profile Detail Infomation ]
		 -------------------------------------------------------------->
	
		<div class="channel_info_content_wapper chat_switch_none_slide">
	
			<!--  Slider Navigation Menu  -->
	
			<nav class="menu" id="myMenu">
				<ul>
					<div class="m-active"></div>
	
					<li v-if="member.ownChannelId"><a v-on:click="changeNav('home')" id="home_btn">Home</a></li>
					<li class="chat_switched_elem" v-if="member.ownChannelId"><a v-on:click="changeNav('video')" id="video_btn">Video</a></li>
	
					<li class="chat_switched_elem"><a v-on:click="changeNav('about')" id="about_btn">About</a></li>
					<li class="chat_switched_elem"><a v-on:click="changeNav('contact')" id="contact_btn" >Contact</a></li>
					
					<li v-on:click="goToCommunity"  class="menu_sub_btn" id="community_btn" v-if="(member?.ownChannelId)"> Community </li>
					<li v-on:click="chatModeToggle" class="menu_sub_btn" id="chat_btn"> {{isChatMode?"No Chat":"Chat"}} <div class="page_move_icon"></div> </li>
				</ul>
			</nav>
	
			<div class="channel_info_content">
				<div class="channel_home">
					<h1> Home </h1>
					
					<h1 class="channel_alias"> About {{member.alias}} </h1>
				</div>
	
				<div class="channel_video">
					<h1> Video </h1>

					<a v-if="this.$store.state.member.id == member.id"
					   class="video_upload_icon"
					   data-modal="modal-video-upload"></a>
					   
					<a data-modal="modal-video" id="videoModal" style="display:none">video modal</a>
					
					<div class="grid">
						<div v-for="item in videoList"   
							 v-on:click="videoModal(item.id)">
							 
							<channel-video-thumb-item-component v-bind:item="item"></channel-video-thumb-item-component>
						</div>
					</div>
						
				</div>
	
				<div class="channel_community">
					<h1> Community </h1>
				</div>
	
				<div class="channel_about">
					<h1 class="channel_alias"> About {{member.alias}} </h1>
	
					<a v-if="this.$store.state.member.id == member.id"
					   class="channel_about__modify">
	
						<img src="/resources/image/setting_icon.png" class="channel_about_setting_img" />
					</a>
	
					<p class="channel_message">{{member.message}}</p>
				</div>
	
				<div class="channel_contact">
					<h1> Contact </h1>
	
					<table>
	
						<tr v-if="member.name">
							<td width='50px'> name </td>
							<td class="channel_contact__right"> {{member.name}} </td>
						</tr>
	
						<tr v-if="member.email">
							<td width='50px'> email </td>
							<td class="channel_contact__right"> {{member.email}} </td>
						</tr>
	
						<tr v-if="member.phone">
							<td width='50px'> phone </td>
							<td class="channel_contact__right"> {{member.phone}} </td>
						</tr>
	
					</table>
	
				</div>
	
			</div>
	
		</div>
		
	</div>

</div>
