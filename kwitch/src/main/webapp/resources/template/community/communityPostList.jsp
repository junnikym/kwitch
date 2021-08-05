<div id="communitPostList">

	<div class="app community_post_list">
	
		<h2> {{menuInfo.title}} </h2>
		
		<ul class="communityBasicThumb">

		</ul>
		
		<button class="community_upload_post btn"
			v-if="hasRoleWrite()" 
			v-on:click="$router.push('/c/'+$route.params.communityId+'/upload')"> 
		
			Write Post 
		</button>

	</div>

</div>