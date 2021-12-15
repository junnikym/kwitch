<div id="channelChat" class="app">
	<div class="channel_chat_header">
		 <h4 style="color:white; margin-left:20px"> chat</h4>
	</div>

	<div id="channel_chat_viewer">
		<channel-chat-item-component 
			v-for="chatItem in chatList"
			v-bind:item="chatItem">
		</channel-chat-item-component>
	</div>

	<div class="channel_chat_input">
		<input type="text" v-model="messageInput" @keyup.enter="send">
		<button type="button" class="btn" v-on:click="send">Send</button>
	</div>
</div>