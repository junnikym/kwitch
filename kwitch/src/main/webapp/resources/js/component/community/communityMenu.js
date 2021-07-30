const CommunityMenuComponent = {
    template: communityMenuTemplate,
    data() { return {
        communityMenu: [],
    }},
    methods: {
    	goToMenu: function(id) {
    		console.log(id);
    	}
    },
    mounted() {
    	const id = 
    		this.$store.state.connectedCommunity 
    			? this.$store.state.connectedCommunity 
    			: this.$route.params.communityId;
		
    	    	
    	fetch('/api/community/'+id+'/menu', {
            method: 'GET',
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then(res => res.json())
        .then(json => {
        	this.communityMenu = json;
        })
        .catch(err => console.log(err))
    }
};