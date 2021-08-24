const channelVideoComponent = {
    template: channelVideoTemplate,
    props: ['id'],
    data() { return {
    	video: null,
    	player: null,
    	playerHtml: null,
    }},
    watch: {
    	id() {
    		if(this.id == null)
    			return;
    		
    		this.loadVideo();
    	}
    },
    methods: {
        loadVideo: function() {
        	fetch('/api/video/'+this.id, {
				method: 'GET'
			})
			.then( res => res.json() )
	  		.then( json => {
	  			
	  			document.querySelector('.channel_video_wrapper').innerHTML = this.playerHtml;
	  			this.video = json;
	  			
	  			const splitPath = this.video.path.split('/');
	  			let index = splitPath.length;
	  			if(splitPath[index] == '' || splitPath[index] == null)
	  				index--;
	  			
	  			this.video.path = this.$store.state.streamServer 
	  							+ this.$store.state.videoURL 
	  							+ `/${splitPath[index-1]}/${splitPath[index]}/playlist.m3u8`; 
	  			
	  			document.querySelector('.channel_video_wrapper source').setAttribute('src', this.video.path); 
	  		})
	  		.then(()=>{
	  			this.player = videojs(document.querySelector('#channelVideoStream'));
	  		})
			.catch(err => console.log(err))
        },
        
        pauseVideo: function() {
        	this.player.pause();
        },
        
        deleteVideo: function() {
        	if (this.$refs.channelVideoStream) {
        		this.player.dispose();
    		}
        }
    },
    mounted() {
    	this.playerHtml = document.querySelector('.channel_video_wrapper').innerHTML;
    	console.log(this.playerHtml);
    },
    
    beforeDestroy() {
		this.deleteVideo();
    }
};