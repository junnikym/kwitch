
const asideComponent = {
	template: asideTemplate,
	store: gStore,
	props: [],
	data() { return {
		subscribedChannelList:[],
		isAsideFolded: false,
	}},

	methods: {
		foldAside: function() {
			console.log("   ", this.isAsideFolded);

			if(this.isAsideFolded) {
				this.isAsideFolded = false;
				console.log("asdfasdf")

				document.getElementById("aside").classList.remove("aside_fold");
				document.getElementById("home_content").classList.remove("aside_fold_for_home");
				document.getElementById("aside_open_btn").classList.add("hidden");
			}
			else {
				this.isAsideFolded = true;

				document.getElementById("aside").classList.add("aside_fold");
				document.getElementById("home_content").classList.add("aside_fold_for_home");
				document.getElementById("aside_open_btn").classList.remove("hidden");
			}
		}
	},

	mounted() {
		fetch('/api/channel/subscribe', {
			method: 'GET',
			headers: { "Content-Type": "application/json" }
		})
		.then(res=>res.json())
		.then(json => {
			this.subscribedChannelList = json;
		})
		.catch(err => console.log(err))
	}
};