
const communityPostComponent = {
	template: communityPostTemplate,
	store: gStore,
	data() { return {
		postContent: {}
	}},
	methods: {

		calcTime: function(t) {
			const time = new Date(t.replace(/-/g, "/"));
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
			fetch('/api/community/post/' + this.$route.params.id, {
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
		}

	},

	mounted() {
		fetch('/api/community/post/' + this.$route.params.id, {
			method: 'GET',
			headers: {
				"Content-Type": "application/json"
			}
		})
		.then(res => res.json())
		.then(json => {

			this.postContent = json;
			
			console.log("time is ", calcTime(this.postContent.createdAt));
			
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