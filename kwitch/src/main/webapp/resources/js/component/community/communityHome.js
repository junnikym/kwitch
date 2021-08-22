const CommunityHomeComponent = {
    template: communityHomeTemplate,
    store: gStore,
    data() { return {
        communityContent: [],
		role: 0,
    }},
    methods: {
    	
        uploadProfileImage: function(id) {
        	const formData = new FormData();
        	const profileImage = document.getElementById('upload_profile_image');
        	
        	formData.append('profileImage', profileImage.files[0]);
        	
        },
		
		hasRoleWrite() {
			if (this.$store.state.communityRole.roleFlag & this.$store.state.communityRoleFlags.CH_ROLE_WRITE)
				return true;
			
			return false;
		}
        
    },

    mounted() {
    	
    	if(this.$route.params.communityId) {
    		
	    	fetch('/api/community/' + this.$route.params.communityId, {
	  		  	method: 'GET',
	      		headers: {
	    			"Content-Type": "application/json"
	    		}
			})
	      	.then(res=>res.json())
	      	.then(json => {
	
				json['menu'].forEach ( menu_it => {
						this.communityContent.push(
								Object.assign(
									menu_it,
									{'post': json['post'].filter ( post_it => post_it.menuId === menu_it.id ) }
								)
						)
				});
	
	      	}).then(() => {
	    		this.$store.commit('connectCommunity', this.$route.params.communityId);
			    this.$store.commit('connectMenu', null);
	    		console.log("router : " + this.$store.state.connectedCommunity);
		    }).then(()=>{})
	      	.catch(err => console.log(err))
	    	
    	}
    
    }
};