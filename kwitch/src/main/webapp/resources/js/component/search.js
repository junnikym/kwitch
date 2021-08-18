const searchComponent = {
    template: searchTemplate,
    store: gStore,
    props: ['searchQuery'],
    data() { return {
    	memberList: [],
        postList: [],
    }},
    methods: {
    	update: function() {
    		console.log(this.searchQuery)
        	
        	fetch('/api/q/' + this.searchQuery, {
      		  	method: 'GET',
          		headers: {
        			"Content-Type": "application/json"
        		}
    		})
          	.then(res=>res.json())
          	.then(json => {
    			this.memberList = json["member"];
    			this.postList = json["community"];
          	})
          	.catch(err => console.log(err))
    	}
    },

    watch: {
    	searchQuery: function() { this.update(); }
    },
    mounted() {
    	this.update();
    }
};