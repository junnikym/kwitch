
<div id="communitHome">

	<button class="community_upload_post btn" v-on:click="$router.push('/c/'+$route.params.communityId+'/upload')"> Write Post </button>

	<div class="community_home_menu" v-for="item in communityContent">
	
		<h2> {{item.title}} </h2>
		
		<div class="horizontal_rule"></div>
		
		<a class="more_btn" v-on:click="goToPostList(item.id)"> more </a>

		<ul>

			<li v-for="(postItem, index) in item.post" v-on:click="goToPost(postItem.id)">
				<community-post-item-small v-bind:item="postItem" v-bind:index="index+1"></community-post-item-small>
				<div class="sub_horizontal_rule"> </div>
			</li>

		</ul>

	</div>

</div>
