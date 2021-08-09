const ChannelComponent = {
    template: channelTemplate,
	store: gStore,
    data() { return {
        navCursor: 'about',
        profileImageSetter: false,
	    profileImageURL: '/resources/image/user_icon.png',
	    member: {},
    }},
    methods: {
    	
    	
        changeNav: function(cursor) {

            if(this.navCursor == cursor) return;

            document.getElementsByClassName("channel_"+this.navCursor)[0].style.display = "none";
            this.navCursor=cursor;
            document.getElementsByClassName("channel_"+this.navCursor)[0].style.display = "flex";
        },
        
        
        toggleProfileImageSetter: function() {
        	if(this.profileImageSetter) 
        		document.getElementsByClassName("set_profile_image")[0].style.display = "none";
        	else 
        		document.getElementsByClassName("set_profile_image")[0].style.display = "flex";
        	
        	this.profileImageSetter = !this.profileImageSetter;
        },
        
        
        setProfileImageThumb: function(e) {
        	const reader = new FileReader(); 
        	reader.onload = function(event) { 
        		const img = document.createElement("img");
        		document.querySelector("div#set_profile_image__thumb img")
        			.setAttribute("src", event.target.result); 
        	}; 
        	
        	reader.readAsDataURL(event.target.files[0]);

        },
        
        
        uploadProfileImage: function(id) {
        	const formData = new FormData();
        	const profileImage = document.getElementById('upload_profile_image');
        	
        	formData.append('profileImage', profileImage.files[0]);
        	
        	fetch('/api/profile/image/'+id, {
        		  method: 'POST',
        		  body: formData
    		})
            .then(res=>{
            	if(res.status == 200)
            		location.reload();
            })
            .catch(err => console.log(err))
        },
        
        
        createChannel: function() {
        	
        	fetch('/api/channel/regist/', {
      		  	method: 'POST',
      		  	headers: {
      		  		"Content-Type": "application/json"
      		  	},
      		  	body: JSON.stringify({
      		  		"title": "title"
      		  	})
	  		})
	  		.then( res => {
				if(res.status == 200) {
					//@TODO success logic
				}
			})
			.catch(err => console.log(err))
		},
		
		
		goToCommunity: function() {
			this.$router.push("/c/"+this.member.ownCommunityId);
		}
    },

    mounted() {
        document.getElementsByClassName("channel_"+this.navCursor)[0].style.display = "flex";
        document.getElementById(this.navCursor+'_btn').className += ' active';

	    fetch('/api/user/' + this.$route.params.id, {
		    method: 'GET',
		    headers: {
			    "Content-Type": "application/json"
		    }
	    })
	    .then(res=>res.json())
	    .then(json => {
		    this.member = json;

		    if(this.member?.profileImagePath != null && this.member?.profileImageExt)
		        this.profileImageURL = '/api/profile/image/'+this.member.profileImagePath+'/'+this.ember.profileImageExt;
		    
		    const navMenu = document.querySelector('#myMenu > ul')[0];
		    if(this.member?.ownCommunityId == null) {
		    	
	        }
		    
		    if(this.member?.ownChannelId == null) {
		    	let target = document.querySelector(".channel_menu_li");
		    	target.parentNode.removeChild(target);
		    	
		    	target = document.querySelector(".channel_menu_li");
		    	target.parentNode.removeChild(target);
	        }
		    
	    })
	    .then(() => {
	    	initMenu("#myMenu");
		    videojs(this.$refs.streamingVideo);
	    })
	    .catch(err => console.log(err));
    },

	beforeDestroy() {
		if (this.$refs.streamingVideo) {
			this.$refs.streamingVideo.dispose()
		}
	}
    
};