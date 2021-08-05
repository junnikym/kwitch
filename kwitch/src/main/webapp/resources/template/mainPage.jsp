<div id="mainPage">
	<br/>
	<h1> Brand New Channel </h1>
	<div class="sub_horizontal_rule"> </div>

	<div class="main_page_new_channel_list">
		<channel-simple-item-component v-for="ch in newChannelList" v-bind:channel="ch"></channel-simple-item-component>
	</div>
	<br/>
	<h1> Hot Post </h1>
	<div class="sub_horizontal_rule"> </div>
	<div class="main_page_hot_post_list">
	</div>
</div>