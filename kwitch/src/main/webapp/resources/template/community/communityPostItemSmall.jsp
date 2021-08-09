<div id="communityPostItemSmall">

	<div class="community_post_item_small_head_info bg_black" v-if="showChannelInfo">
		<div class="community_post_item_small_channel">
			<div class="user_profile_img_wapper">
				<img src="/resources/image/user_icon.png" class="default_user_profile_img" />
			</div>
			<h4 class="community_post_item_small_writer_alias" v-on:click="$router.push('/c/'+item.channelId)">{{item.channelTitle}}</h4>
			&nbsp;-&nbsp; 
			<h4 class="community_post_item_small_writer_alias" v-on:click="$router.push('/c/m/'+item.menuId)">{{item.menuTitle}}</h4> 
		</div>
	</div>
	
	<div v-on:click="$router.push('/c/p/'+item.id)">
		<h3 class="community_post_item_small_title">
			{{ item.isBlock ? 'This post blocked by admin.' : item.title}}
		</h3>
	
		<div class="community_post_item_small_head_info">
			<div class="community_post_item_small_sub_info">
				<div class="sharp"><div class="sharp_icon"></div> 	&nbsp; {{ index }} &nbsp;&nbsp; 					</div>
				<div class="clock"><div class="clock_icon"></div> 	&nbsp; {{ calcTime(item.createdAt) }} &nbsp;&nbsp; 	</div>
				<div class="view"><div class="view_icon"></div> 	&nbsp; {{ item.nView }} &nbsp;&nbsp; 				</div>
			</div>
			<div class="community_post_item_small_writer">
				<h4 class="community_post_item_small_writer_alias">{{item.writerAlias}}</h4>
				
				<div class="user_profile_img_wapper">
					<img src="/resources/image/user_icon.png" class="default_user_profile_img" />
				</div>
			</div>
		</div>
		
		<div class="community_post_item_small_content">
			{{ item.isBlock ? 'This post blocked by admin.' : removeHtmlTag(item.content) }}
		</div>
	</div>
	
</div>