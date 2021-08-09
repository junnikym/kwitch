const CommunityMenuComponent = {
    template: communityMenuTemplate,
    data () { return {
        communityMenu: [],
    }},
    methods: {

	    getMenuContent: function (coummunityId) {
		    fetch('/api/community/'+coummunityId+'/menu', {
			    method: 'GET',
			    headers: {
				    "Content-Type": "application/json"
			    }
		    })
		    .then (res => res.json())
		    .then (json => {
			    this.communityMenu = json;
		    })
		    .then ( ()=>initMenu("#communityMenu", true) )
		    .catch (err => console.log(err))
	    }

    },

	computed:{
    	checkConnectedCommunity () {
    		return this.$store.state.connectedCommunity;
    	}
	},
	watch:{
		checkConnectedCommunity (val) {
			this.getMenuContent (val)
			console.log("asdfasdfasdfasdf : ", val);
		}
	},

	mounted () {
    	if (this.$store.state.connectedCommunity) {
		    this.getMenuContent(this.$store.state.connectedCommunity);
	        console.log("asdfasdfasdfasdf : ", this.$store.state.connectedCommunity);
    	}
	}

};