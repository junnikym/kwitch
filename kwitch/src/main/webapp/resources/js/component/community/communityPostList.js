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
		},

	    loadPostList: function() {
        	console.log("load!!! ", this.$route.params.menuId);

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
//        
    },
    mounted() {
	    fetch('/api/community/menu/' + this.$route.params.menuId + '/info', {
		    method: 'GET',
		    headers: { "Content-Type": "application/json" }
	    })
	    .then(res => res.json())
	    .then(json => {
		    this.$store.commit('connectCommunity', json.communityId);
		    this.$store.commit('connectMenu', json.id);
	    })
	    .catch(err => console.log(err))
    },

	computed:{
		checkConnectedMenu () {
			return this.$store.state.connectedMenu;
		},
	},

	watch:{
		checkConnectedMenu (val) {
			this.loadPostList();
		},
	}
};