<!--

<div class="modal" id="modal-login">
	<div class="modal-bg modal-exit"></div>
	<iframe src="/resources/template/login.jsp" class="modal-container app" id="login_iframe"
			frameborder="0" scrolling="no" style="display: block;" ></iframe>

	<button class="modal-close modal-exit" id="model_login_close" style="display: none;" type="button"></button>
</div>
-->
<!--  Profile Image  -->

<div id="header">

	<div class="head_logo_wapper" onClick="location.href='/'">
		<img src="/resources/image/kwitch_icon.png" class="head_logo head_logo_shadow" />
		<img src="/resources/image/kwitch_icon.png" class="head_logo head_logo_main" />
	</div>
	
	<div class="search_wapper">
		<input class="input form__input from__search_input w_center" type="text" v-model="searchInput"/>
		<button class="btn btn__search h_center_wrapper" type="button" v-on:click="$router.push('/q/'+searchInput)">
			<img src="/resources/image/search_icon.png" class="btn__search_icon h_center_content" /></button>
	</div>

	<div v-if="this.$store.state.member.id"
		 class="dropdown">

		<div class="notification_icon" v-on:click="notificationToggle"></div>

		<div v-if="isNotificationOn"
			 class="notification_content">

			<div v-for="item in notificationList"
				class="notification_content_item"
				v-on:click="checkNotification(item)">

				<div>
					<div class="user_profile_img_wapper">
						<img v-if="item.senderProfileImagePath && item.senderProfileImageExt"
							 v-bind:src="'/api/profile/image/' + item.senderProfileImagePath + '/' + item.senderProfileImageExt"
							 class="default_user_profile_img" />
						<img v-else src="/resources/image/user_icon.png" class="user_profile_img" />
					</div>
				</div>

				{{item.text}}

			</div>

		</div>

		<button onclick="dp_menu()" class="button">
			<div class="user_profile_img_wapper">
				<img v-if="this.$store.state.member.profileImagePath && this.$store.state.member.profileImageExt"
					 v-bind:src="'/api/profile/image/' + member_profile_image + '/' + member_profile_image_ext"
					 class="default_user_profile_img" />
				<img v-else src="/resources/image/user_icon.png" class="user_profile_img" />
			</div>
		</button>


		<div style="display: none;" id="drop-content">
			<a v-on:click="channel()"> profile </a>
			<a v-on:click="logout" class="logout_btn"> logout </a>
		</div>
	</div>
	<div v-else
		 class="container account_btn_wapper">

		<a data-modal="modal-login" class="btn login_btn">Login</a>
		<a class="btn signup_btn" href="/regist"> SignUp </a>
	</div>
</div>

