<div id="communityMenu">

	<ul class="community_menu">
		<li v-for="item in communityMenu" 
			v-on:click="goToMenu(item.id)" 
			class="community_menu_btn">
			{{item.title}}
		</li>
		
	</ul>
	
	

</div>