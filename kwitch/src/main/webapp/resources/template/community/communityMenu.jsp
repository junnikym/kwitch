<nav id="communityMenu" >

	<ul class="community_menu">
		<div class="community_mune_cursor m-active"></div>
		
		<li class="community_menu_btn default_menu_btn">
			<a v-on:click="$router.push('/c/'+$store.state.connectedCommunity)" > Home </a> </li>
		
		<li v-for="item in communityMenu"
			class="community_menu_btn"
			v-bind:class="{ active : item?.isActive }">
			<a v-on:click="gotoOther(item.id)"> {{item.title}} </a>
		</li>

	</ul>

</nav>