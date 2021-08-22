const CommunityComponent = {
	template: communityTemplate,
	store: gStore,
	data() { return {
		
	}},
	methods: {
		
	},
	mounted() {
		
		const postId 		= this.$route.params.postId;
		const communityId 	= this.$route.params.communityId;
		
		const gStoreRole 	= this.$store.state.member.communityRole;
		
		if( (postId && gStoreRole?.postId != postId) ||
			(communityId && gStoreRole?.communityId != communityId)) {

			let role_vo = {};
			
			if(postId) {
				role_vo = {
					targetId	: postId,
					channelIdBy : 'CHANNEL_ID_TYPE_POST_ID'
				}
			}
			
			else if(communityId) {
				role_vo = {
					targetId	: communityId,
					channelIdBy : 'CHANNEL_ID_TYPE_COMMUNITY_ID'
				}
			}
		
			else {
				return;
			}
		
			fetch('/api/community/auth', {
	  		  	method: 'POST',
	      		headers: { "Content-Type": "application/json" }, 
	    		body:  JSON.stringify(role_vo)
			})
	      	.then(res=>res.json())
	      	.then(json => {
	      		this.$store.commit('setCommunityRole', json)k
	      	})
	      	.catch(err => console.log(err))
		}
	}
};