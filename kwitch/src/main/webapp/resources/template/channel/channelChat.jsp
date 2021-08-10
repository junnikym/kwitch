<div id="channelChat" class="app">
	<div class="channel_chat_header"> 
	</div>

	<div id="channel_chat_viewer">
		<channel-chat-item-component 
			v-for="chatItem in chatList"
			v-bind:item="chatItem">
		</channel-chat-item-component>
	</div>

	<div class="channel_chat_input">
		<input type="text" v-model="messageInput">
		<button type="button" class="btn" v-on:click="send">Send</button>
	</div>
</div>