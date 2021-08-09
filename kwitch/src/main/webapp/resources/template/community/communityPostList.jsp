<div id="communitPostList">

	<button class="community_upload_post btn"
			v-if="hasRoleWrite()"
			v-on:click="$router.push('/c/'+$route.params.communityId+'/upload')">

		Write Post
	</button>

	<div class="community_home_menu">

		<h2> test </h2>

		<div class="horizontal_rule"></div>

		<ul>

			<li v-for="(postItem, index) in postList">
				<community-post-item-small v-bind:item="postItem" v-bind:index="index+1"></community-post-item-small>
				<div class="sub_horizontal_rule"> </div>
			</li>

		</ul>

	</div>

</div>