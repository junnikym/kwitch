
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
		
		likeToggle: function(isUnlike) {
			if(!this.$store.state.member.id) {
				alert("Need to login");
				return;
			}

			console.log("it running !!!!!!!!");

			const reqBody = JSON.stringify({
				usage	 : 'LIKE_USAGE_POST',
				targetId : this.$route.params.postId,
				isUnliked: isUnlike,
			});

			// User wanna delete like(or unlike)
			if(this.isLike == !isUnlike) {
				console.log("it running !!!!!!!! 111111111111111");

				fetch('/api/like', {
					method: 'PUT',
					headers: {
						"Content-Type": "application/json"
					},
					body: reqBody
				})
				.then(res => {
					if(res.status == 200) {
						this.likeInit();
					}
				})
				.catch(err => console.log(err))
			}
			else {
				console.log("it running !!!!!!!! 22222222222222");

				fetch('/api/like', {
					method: 'POST',
					headers: {
						"Content-Type": "application/json"
					},
					body: reqBody
				})
				.then(res => {
					if(res.status == 200) {
						this.likeInit();
					}
				})
				.catch(err => console.log(err))
			}
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
			.then(res=> {
				if(res.status == 204)
					return null;
				else if(res.status == 200)
					return res.json();
			})
			.then(json=> {
				if(json == null)
					this.isLike = null;
				else
					this.isLike = json;

				if(this.isLike == true) {       // LIKE
					if(!document.querySelector("#communityPost .like_icon").classList.contains("hidden"))
						document.querySelector("#communityPost .like_icon").classList.add("hidden");
					if(document.querySelector("#communityPost .liked_color_item").classList.contains("hidden"))
						document.querySelector("#communityPost .liked_color_item").classList.remove("hidden");
					if(document.querySelector("#communityPost .unlike_icon").classList.contains("hidden"))
						document.querySelector("#communityPost .unlike_icon").classList.remove("hidden");
					if(!document.querySelector("#communityPost .unlike_color_icon").classList.contains("hidden"))
						document.querySelector("#communityPost .unlike_color_icon").classList.add("hidden");
				}
				else if(this.isLike == false) { // UNLIKE
					if(document.querySelector("#communityPost .like_icon").classList.contains("hidden"))
						document.querySelector("#communityPost .like_icon").classList.remove("hidden");
					if(!document.querySelector("#communityPost .liked_color_item").classList.contains("hidden"))
						document.querySelector("#communityPost .liked_color_item").classList.add("hidden");
					if(!document.querySelector("#communityPost .unlike_icon").classList.contains("hidden"))
						document.querySelector("#communityPost .unlike_icon").classList.add("hidden");
					if(document.querySelector("#communityPost .unlike_color_icon").classList.contains("hidden"))
						document.querySelector("#communityPost .unlike_color_icon").classList.remove("hidden");
				}
				else {                  // NOT EXIST BOTH
					if(document.querySelector("#communityPost .like_icon").classList.contains("hidden"))
						document.querySelector("#communityPost .like_icon").classList.remove("hidden");
					if(!document.querySelector("#communityPost .liked_color_item").classList.contains("hidden"))
						document.querySelector("#communityPost .liked_color_item").classList.add("hidden");
					if(document.querySelector("#communityPost .unlike_icon").classList.contains("hidden"))
						document.querySelector("#communityPost .unlike_icon").classList.remove("hidden");
					if(!document.querySelector("#communityPost .unlike_color_icon").classList.contains("hidden"))
						document.querySelector("#communityPost .unlike_color_icon").classList.add("hidden");
				}
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