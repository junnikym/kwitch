
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

			this.postContent = json

		})
		.catch(err => console.log(err))
	}
};