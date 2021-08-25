
const likedComponent = {
	template: likedTemplate,
	store: gStore,
	props: ['targetId', 'usage', 'parentElem'],
	data() { return {
		isLike: null,
	}},
	watch: {
		targetId: function() {
			this.likeInit();
		}
	},
	methods: {

		likeToggle: function(isUnlike) {
			if(!this.$store.state.member.id) {
				alert("Need to login");
				return;
			}

			const reqBody = JSON.stringify({
				usage	 : this.usage,
				targetId : this.targetId,
				isUnliked: isUnlike,
			});

			// Wanna delete like(or unlike)
			if(this.isLike == !isUnlike) {

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

			// Wanna regist like(or unlike)
			else {

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
				usage	 : this.usage,
				targetId : this.targetId,
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
					if(!document.querySelector(this.parentElem+" .like_icon").classList.contains("hidden"))
						document.querySelector(this.parentElem+" .like_icon").classList.add("hidden");
					if(document.querySelector(this.parentElem+" .liked_color_item").classList.contains("hidden"))
						document.querySelector(this.parentElem+" .liked_color_item").classList.remove("hidden");
					if(document.querySelector(this.parentElem+" .unlike_icon").classList.contains("hidden"))
						document.querySelector(this.parentElem+" .unlike_icon").classList.remove("hidden");
					if(!document.querySelector(this.parentElem+" .unlike_color_icon").classList.contains("hidden"))
						document.querySelector(this.parentElem+" .unlike_color_icon").classList.add("hidden");
				}
				else if(this.isLike == false) { // UNLIKE
					if(document.querySelector(this.parentElem+" .like_icon").classList.contains("hidden"))
						document.querySelector(this.parentElem+" .like_icon").classList.remove("hidden");
					if(!document.querySelector(this.parentElem+" .liked_color_item").classList.contains("hidden"))
						document.querySelector(this.parentElem+" .liked_color_item").classList.add("hidden");
					if(!document.querySelector(this.parentElem+" .unlike_icon").classList.contains("hidden"))
						document.querySelector(this.parentElem+" .unlike_icon").classList.add("hidden");
					if(document.querySelector(this.parentElem+" .unlike_color_icon").classList.contains("hidden"))
						document.querySelector(this.parentElem+" .unlike_color_icon").classList.remove("hidden");
				}
				else {                  // NOT EXIST BOTH
					if(document.querySelector(this.parentElem+" .like_icon").classList.contains("hidden"))
						document.querySelector(this.parentElem+" .like_icon").classList.remove("hidden");
					if(!document.querySelector(this.parentElem+" .liked_color_item").classList.contains("hidden"))
						document.querySelector(this.parentElem+" .liked_color_item").classList.add("hidden");
					if(document.querySelector(this.parentElem+" .unlike_icon").classList.contains("hidden"))
						document.querySelector(this.parentElem+" .unlike_icon").classList.remove("hidden");
					if(!document.querySelector(this.parentElem+" .unlike_color_icon").classList.contains("hidden"))
						document.querySelector(this.parentElem+" .unlike_color_icon").classList.add("hidden");
				}
			})
			.catch(err => console.log(err))
		},
	},

	mounted() {
		this.likeInit();
	}
};