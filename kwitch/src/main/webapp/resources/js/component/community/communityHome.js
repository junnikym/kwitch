const CommunityHomeComponent = {
    template: communityHomeTemplate,
    data() { return {
        communityContent: [],
    }},
    methods: {
    	
        uploadProfileImage: function(id) {
        	const formData = new FormData();
        	const profileImage = document.getElementById('upload_profile_image');
        	
        	formData.append('profileImage', profileImage.files[0]);
        	
        	
        },
        
        goToPost: function(id) {
	        this.$router.push("/p/"+id);
		},
		
		goToPostList: function(id) {
        	parent.document.location.href = "/community/menu/"+id;
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
	
				console.log(this.communityContent);
	
	
	      	})
	      	.catch(err => console.log(err))
	    	
    	}
    
    }
};