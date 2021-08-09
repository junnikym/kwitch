<nav id="communityMenu" >

	<ul class="community_menu">
		<div class="community_mune_cursor m-active"></div>
		
		<li class="community_menu_btn active">
			<a v-on:click="$router.push('/c/'+$store.state.connectedCommunity)" > Home </a> </li>
		
		<li v-for="item in communityMenu" 
			v-on:click="$router.push('/c/m/'+item.id)" 
			class="community_menu_btn">
			<a> {{item.title}} </a>
		</li>
		
	</ul>

</nav>