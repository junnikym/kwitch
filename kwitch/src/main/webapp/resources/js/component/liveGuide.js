const liveGuideComponent = {
    template: liveGuideTemplate,
    data() { return {
    	ownLiveStream: null,
    }},
    methods: {
    	registLive: function() {
    		fetch('/api/live', { 
    			method: 'POST',
    			headers: {
        			"Content-Type": "application/json"
        		},
                body: JSON.stringify({})
    		})
	  		.then( res => res.json() )
	  		.then( json => {
	  			this.ownLiveStream = json();
	  		})
			.catch(err => console.log(err))
    	},
    
    	startLive: function() {
    		if(this.ownLiveStream == null)
    			alert("get your stream key before start streaming");
    		
    		fetch('/api/live/start', { method: 'GET' })
	  		.then( res => {
	  			if(res.status == 200)
	  				this.ownLiveStream.startAt = true;
	  		})
			.catch(err => console.log(err))
    	},
    	
    	stopLive: function() {
    		fetch('/api/live/stop', { method: 'GET' })
	  		.then( res => {
	  			if(res.status == 200) {
	  				this.ownLiveStream = null;
	  			}
	  		})
			.catch(err => console.log(err))
    	}
    },
    mounted() {
    	fetch('/api/live', { method: 'GET' })
  		.then( res => res.json() )
  		.then( json => {
  			this.ownLiveStream = json;
  		})
		.catch(err => console.log(err))
    }
};