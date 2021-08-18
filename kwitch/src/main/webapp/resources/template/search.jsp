
<div id="search" class="app">
	
		<h2 class="search_title"> Search </h2>
		
		<div class="horizontal_rule"></div>
		
	<div class="search_contents">
		<h3> User </h3>
		
		<div class="sub_horizontal_rule"> </div>
		
		<div class="grid">
			<channel-simple-item-component v-for="item in memberList" v-bind:channel="item"></channel-simple-item-component>			
		</div>

		<h3> Community Post </h3>
		
		<div class="sub_horizontal_rule"> </div>

		<ul>

			<li v-for="(item, index) in postList">
				<community-post-item-small v-bind:item="item" v-bind:index="index+1" v-bind:showChannelInfo=true></community-post-item-small>
			</li>

		</ul>

	</div>

</div>
