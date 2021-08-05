
<div id="search" class="app">

	<div class="community_home_menu">
	
		<h2> Search </h2>
		
		<div class="horizontal_rule"></div>
		
		<a class="more_btn" v-on:click=""> more </a>

		<ul>

			<li v-for="(item, index) in postList">
				<community-post-item-small v-bind:item="item" v-bind:index="index+1" v-bind:showChannelInfo=true></community-post-item-small>
			</li>

		</ul>

	</div>

</div>
