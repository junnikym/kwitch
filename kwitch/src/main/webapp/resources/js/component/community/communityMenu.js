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
    	    	
    	fetch('/api/community/'+this.$route.params.communityId+'/menu', {
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