const mainPageComponent = {
    template: mainPageTemplate,
    store: gStore,
    data() { return {
	    newChannelList: [],
	    newVideoList:[],
	    hotVideoList: [],
    }},
    methods: {
		
    },
	mounted: function() {
		
		fetch('/api/channel/new', {
  		  	method: 'GET',
      		headers: { "Content-Type": "application/json" }
		})
      	.then(res=>res.json())
      	.then(json => {
      		this.newChannelList = json;
      	})
		.then(() => {
			videojs(this.$refs.streamingVideo);
		})
      	.catch(err => console.log(err))



		fetch('/api/video/new', {
			method: 'GET',
			headers: { "Content-Type": "application/json" }
		})
		.then(res=>res.json())
		.then(json => {
			this.newVideoList = json;
		})
		.catch(err => console.log(err))



		fetch('/api/video/hot', {
			method: 'GET',
			headers: { "Content-Type": "application/json" }
		})
		.then(res=>res.json())
		.then(json => {
			this.hotVideoList = json;
		})
		.catch(err => console.log(err))
	},

	beforeDestroy() {
		if (this.$refs.streamingVideo) {
			this.$refs.streamingVideo.dispose()
		}
	}
};