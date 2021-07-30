
<div id="channel">

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
		<tr v-else>
				<td colspan="2" class="channel_header__letter"> subscriber </td>
				<td><button class="btn" type="button" v-on:click="goToCommunity">Go Community</button></td>
		</tr>

	</table>

	<!--------------------------------------------------------------
			[ Profile Detail Infomation ]
	 -------------------------------------------------------------->

	<div class="channel_info_content_wapper">

		<!--  Slider Navigation Menu  -->

		<nav class="menu" id="myMenu">
			<ul>
				<div class="m-active"></div>

				<li v-if="member.ownChannelId"><a v-on:click="changeNav('home')" id="home_btn">Home</a></li>
				<li v-if="member.ownChannelId"><a v-on:click="changeNav('video')" id="video_btn">Video</a></li>

				<li v-if="member.ownCommunityId"><a v-on:click="changeNav('community')" id="community_btn">Community</a></li>

				<li><a v-on:click="changeNav('about')" id="about_btn">About</a></li>
				<li><a v-on:click="changeNav('contact')" id="contact_btn" >Contact</a></li>
			</ul>
		</nav>

		<div class="channel_info_content">
			<div class="channel_home">
				<h1> Home </h1>
			</div>

			<div class="channel_video">
				<h1> Video </h1>
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
