
<div id="communityPost">

	<h3> {{postContent.menuTitle}} </h3>

	<div class="sub_horizontal_rule"> </div>

	<h1> {{postContent.title}} </h1>

	<div class="community_post_writer">
		<div class="user_profile_img_wapper">
			<img src="/resources/image/user_icon.png" class="default_user_profile_img" />
		</div>
		<h4 class="community_post_writer_alias">{{postContent.writerAlias}}</h4>
	</div>

	<div class="horizontal_rule"></div>
	<div class="community_post_sub_info">
		<div class="clock_icon"></div> &nbsp; {{ calcTime(postContent.createdAt) }} &nbsp;&nbsp;
		<div class="view_icon"></div> &nbsp; {{ postContent.nView }} &nbsp;&nbsp
	</div>

</div>