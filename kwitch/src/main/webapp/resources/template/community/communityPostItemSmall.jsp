<div id="communityPostItemSmall" v-on:click="goToPost(postItem.id)">

	<h3 class="community_post_item_small_title">{{item.title}}</h3>

	<div class="community_post_item_small_head_info">
		<div class="community_post_item_small_sub_info">
			<div class="sharp_icon"></div> &nbsp; {{ index }} &nbsp;&nbsp
			<div class="clock_icon"></div> &nbsp; {{ calcTime(item.createdAt) }} &nbsp;&nbsp;
			<div class="view_icon"></div> &nbsp; {{ item.nView }} &nbsp;&nbsp
		</div>
		<div class="community_post_item_small_writer">
			<h4 class="community_post_item_small_writer_alias">{{item.writerAlias}}</h4>
			
			<div class="user_profile_img_wapper">
				<img src="/resources/image/user_icon.png" class="default_user_profile_img" />
			</div>
		</div>
	</div>

	<div class="community_post_item_small_content">
		{{item.content}}
	</div>
	
</div>