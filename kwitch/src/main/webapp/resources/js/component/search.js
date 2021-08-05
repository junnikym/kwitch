const searchComponent = {
    template: searchTemplate,
    store: gStore,
    props: ['searchQuery'],
    data() { return {
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
    			this.postList = json;
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