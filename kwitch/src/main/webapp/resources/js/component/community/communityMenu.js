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
			    isMenuLoadDone = true;

			    if(this.$store.state.connectedMenu) {
				    this.communityMenu.forEach( item => {
					    if(this.$store.state.connectedMenu == item.id)
						    item.isActive = true;
				    });
			    }
			    else {
			    	document.querySelector('.default_menu_btn').classList.add('active');
			    }

		    })
		    .then ( ()=>initMenu("#communityMenu", true) )
		    .catch (err => console.log(err))
	    },

	    gotoOther: function(id) {
	    	console.log(id);
		    this.$router.push('/c/m/'+id)
		    .then(()=>{this.$store.commit('connectMenu', id);})
		    .catch(()=>{this.$store.commit('connectMenu', id);});
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
			console.log("conntected community watch : ", val);
		},

	}

};