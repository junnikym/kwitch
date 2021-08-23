
<div id="communityPost">
	
	<div class="community_post_menu_title">
		<button class="btn" v-on:click="$router.go(-1)"> Back </button>
		<h3> {{postContent.menuTitle}} </h3>
	</div>

	<div class="sub_horizontal_rule"> </div>

	<h1 class="community_post_title"> {{ postContent.isBlock ? 'This post blocked by admin.' : postContent.title}}  </h1>

	<div class="community_post_writer">
		<div class="user_profile_img_wapper">
			<img src="/resources/image/user_icon.png" class="default_user_profile_img invert" />
		</div>
		<h4 class="community_post_writer_alias">{{postContent.writerAlias}}</h4>
	</div>

	<div class="horizontal_rule"></div>
	<div class="community_post_sub_info">
		<div class="clock_icon" v-if="postContent.createdAt"></div> &nbsp; {{ calcTime( postContent.createdAt ) }} &nbsp;&nbsp;
		<div class="view_icon"></div> &nbsp; {{ postContent.nView }} &nbsp;&nbsp;
		
		<button v-if="hasRoleUpdate()"
				class="community_post_edit"
				v-on:click="$router.push('/c/p/'+$route.params.postId+'/edit')">
			Edit
		</button>
		
		<button v-if="hasRoleDelete()"
				class="community_post_delete"
				v-on:click="deletePost">
			Delete
		</button>

		<button v-else-if="hasRoleDeleteOthers()"
				class="community_post_delete"
				v-on:click="deleteOthersPost">
			Delete
		</button>
	</div>
	<div v-if="postContent?.updatedAt" class="community_post_update_info">
		<div class="clock_icon" v-if="postContent.updatedAt"></div> &nbsp; Updated at {{ calcTime( postContent.updatedAt ) }} &nbsp;&nbsp;
	</div>
	
	<div class="community_post_content" v-html="postContent.isBlock ? '<p>This post blocked by admin.</p>' : postContent.content"></div>

</div>