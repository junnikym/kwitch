const communityPostListComponent = {
	template: communityPostListTemplate,
	store: gStore,
    data() { return {
    	menuInfo: {},
    	postList: [],
    }},
    methods: {
//    	
//        uploadProfileImage: function(id) {
//        	const formData = new FormData();
//        	const profileImage = document.getElementById('upload_profile_image');
//        	
//        	formData.append('profileImage', profileImage.files[0]);
//        	
//        	
//        },
//        
//        goToPost: function(id) {
//        	parent.document.location.href = "/community/post/"+id;
//		},
//		
//		goToPostUpload: function() {
//			parent.document.location.href = "/community/post/upload/"+this.menuInfo.communityId;
//		}
    	
    	hasRoleWrite() {
			if (this.$store.state.communityRole.roleFlag & this.$store.state.communityRoleFlags.CH_ROLE_WRITE)
				return true;
			
			return false;
		}
//        
    },

    mounted() {
//    	
//    	fetch('/api/community/menu/' + parent.document.all["menuId"].value + '/info', {
//  		  	method: 'GET',
//      		headers: {
//    			"Content-Type": "application/json"
//    		}
//		})
//      	.then(res => res.json())
//      	.then(json => {
//
//			this.menuInfo = json;
//
//      	})
//      	.catch(err => console.log(err))
//    	
//    	
//    	
//    	fetch('/api/community/menu/' + parent.document.all["menuId"].value, {
//  		  	method: 'GET',
//      		headers: {
//    			"Content-Type": "application/json"
//    		}
//		})
//      	.then(res => res.json())
//      	.then(json => {
//
//			this.postList = json;
//			console.log(this.postList);
//
//      	})
//      	.catch(err => console.log(err))
    }
};