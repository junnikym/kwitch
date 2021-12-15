<div class="channel_chat_item" style="width:100%">

	<!-- Own Chat -->
	<div v-if="this.$store.state.member.id == item.writerId"
		 style="width:100%;" align="right">
		<h3> {{item.writerAlias}} </h3>
		<p>{{item.text}}</p>
	</div>

	<!-- Not Mine -->
	<div v-else
		 style="width:100%;" align="left">
		<h3> {{item.writerAlias}} </h3>
		<p>{{item.text}}</p>
	</div>

</div>