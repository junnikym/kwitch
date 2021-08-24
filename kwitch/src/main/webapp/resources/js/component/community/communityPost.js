
const communityPostComponent = {
	template: communityPostTemplate,
	store: gStore,
	data() { return {
		postContent: {},
		isLike: null,
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

		deleteOthersPost: function() {
			if (this.$store.state.member.id == this.postContent.writerId) {
				console.log("delete!!!!!!!!")
				// this.deletePost();
			}
			else {
				fetch('/api/community/post/' + this.$route.params.postId + '/block', {
					method: 'DELETE',
					headers: {
						"Content-Type": "application/json"
					}
				}).then(res => {
					if (res.status == 200)
						window.location.reload();

					else if (res.status == 401)
						throw "삭제할 권한이 없습니다.";

				}).catch(err => {
					alter(err);
				});
			}
		},
		
		hasRoleDeleteOthers() {
			const userFlag 	= this.$store.state.communityRole.roleFlag;
			const checkFlag	= this.$store.state.communityRoleFlags.CH_ROLE_DELETE_OTHERS;
			
			if (userFlag & checkFlag)
				return true;
			
			return false;
		},

		hasRoleDelete() {
			const userFlag 	= this.$store.state.communityRole.roleFlag;
			const checkFlag	= this.$store.state.communityRoleFlags.CH_ROLE_DELETE;

			const hasRole = userFlag & checkFlag;
			if ((this.$store.state.member.id == this.postContent.writerId) && hasRole > 0)
				return true;

			return false;
		},
		
		hasRoleUpdate() {
			const userFlag 	= this.$store.state.communityRole.roleFlag;
			const checkFlag	= this.$store.state.communityRoleFlags.CH_ROLE_UPDATE;
			
			if ((this.$store.state.member.id == this.postContent.writerId) && (userFlag & checkFlag))
				return true;
		},
		
		likeToggle: function(isUnlike=false) {
			const reqBody = JSON.stringify({
				usage	 : 'LIKE_USAGE_POST',
				targetId : this.$route.params.postId,
				isUnliked: isUnlike,
			});
			
			fetch('/api/like', {
				method: 'POST',
				headers: {
					"Content-Type": "application/json"
				},
				body: reqBody
			})
			.then(res=>{})
			.catch(err => console.log(err))
			
			
		},
		
		likeInit() {
			const reqBody = JSON.stringify({
				usage	 : 'LIKE_USAGE_POST',
				targetId : this.$route.params.postId,
			});
			
			fetch('/api/like/is', {
				method: 'POST',
				headers: {
					"Content-Type": "application/json"
				},
				body: reqBody
			})
			.then(res=>json()) 
			.then(json=> {
				console.log("like!!!!!!!!!!!! ", json);
			})
			.catch(err => console.log(err))
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
			console.log(this.postContent);
			
			if(this.$store.state.connectedCommunity != this.postContent.communityId) {
				this.$store.commit('connectCommunity', this.postContent.communityId)
			}
			
			if(this.$store.state.connectedMenu != this.postContent.communityMenu) {
				this.$store.commit('connectMenu', this.postContent.communityMenu);
			}
			
			this.likeInit();

		})
		.catch(err => console.log(err))
	}
};