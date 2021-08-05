const mainPageComponent = {
    template: mainPageTemplate,
    store: gStore,
    data() { return {
	    newChannelList: [],
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
      	.catch(err => console.log(err))

	}
};