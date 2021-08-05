
const communityPostComponent = {
	template: communityPostTemplate,
	store: gStore,
	data() { return {
		postContent: {}
	}},
	methods: {

		calcTime: function(t) {
			if(!t)
				return;

			console.log(t);
			const time = new Date(t.replace(/\s+/g, 'T'));
			console.log(time);
			return timeDifference(Date.now(), time);
		},

		uploadProfileImage: function(id) {
			const formData = new FormData();
			const profileImage = document.getElementById('upload_profile_image');

			formData.append('profileImage', profileImage.files[0]);
		},

		goToEditor: function(id) {
			parent.document.location.href = "/community/post/"+this.urlId+"/edit";
		},
		
		deletePost: function() {
			fetch('/api/community/post/' + this.$route.params.postId, {
				method: 'DELETE',
				headers: {
					"Content-Type": "application/json"
				}
			}).then(res => {
				if(res.status == 200)
					this.$router.go(-1);
				
				else if(res.status == 401) 
					throw "삭제할 권한이 없습니다.";
				
			}).catch(err => {
				alter(err);
			});
		},
		
		hasRoleOtherDelete() {
			const userFlag 	= this.$store.state.communityRole.roleFlag;
			const checkFlag	= this.$store.state.communityRoleFlags.CH_ROLE_CH_ROLE_DELETE_OTHERS;
			
			if (userFlag & checkFlag)
				return true;
			
			if (this.$store.state.member.id == this.postContent.writerId)
				return true;
			
			return false;
		},
		
		hasRoleUpdate() {
			const userFlag 	= this.$store.state.communityRole.roleFlag;
			const checkFlag	= this.$store.state.communityRoleFlags.CH_ROLE_UPDATE;
			
			if ((this.$store.state.member.id == this.postContent.writerId) && (userFlag & checkFlag))
				return true;
		}

	},

	mounted() {
		fetch('/api/community/post/' + this.$route.params.postId, {
			method: 'GET',
			headers: {
				"Content-Type": "application/json"
			}
		})
		.then(res => res.json())
		.then(json => {

			this.postContent = json;
			
			if(this.$store.state.connectedCommunity != this.postConnect.communityId) {
				this.$store.commit('connectCommunity', this.postConnect.communityId)
			}
			
			if(this.$store.state.connectedMenu != this.postConnect.communityMenu) {
				this.$store.commit('connectMenu', this.postConnect.communityMenu);
			}

		})
		.catch(err => console.log(err))
	}
};