const communityPostListComponent = {
	template: communityPostListTemplate,
	store: gStore,
    data() { return {
    	menuInfo: {},
    	postList: [],
    }},
    methods: {

        uploadProfileImage: function(id) {
			const formData = new FormData();
			const profileImage = document.getElementById('upload_profile_image');

			formData.append('profileImage', profileImage.files[0]);
		},
    	
    	hasRoleWrite: function() {
			if (this.$store.state.communityRole.roleFlag & this.$store.state.communityRoleFlags.CH_ROLE_WRITE)
				return true;
			
			return false;
		}
//        
    },

    mounted() {

   	    fetch('/api/community/menu/' + this.$route.params.menuId, {
 		  	method: 'GET',
     		headers: { "Content-Type": "application/json" }
		})
     	.then(res => res.json())
     	.then(json => {
			this.postList = json;

			console.log(this.postList)
     	})
     	.catch(err => console.log(err))
    }
};