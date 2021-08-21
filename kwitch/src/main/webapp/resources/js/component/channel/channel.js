const ChannelComponent = {
    template: channelTemplate,
	store: gStore,
    data() { return {
        navCursor: 'video',
        profileImageSetter: false,
	    profileImageURL: '/resources/image/user_icon.png',
	    member: {},
	    isChatMode: true,
	    preChatModeMenu: null,
	    isSubscribed: false,
	    videoList:[],
    }},
    methods: {
    	
    	
        changeNav: function(cursor) {

            if(this.navCursor == cursor)
            	return;

			if(cursor == "video" && ! this.videoList.length)
				this.videoLoad();

            document.getElementsByClassName("channel_"+this.navCursor)[0].style.display = "none";
            this.navCursor=cursor;
            document.getElementsByClassName("channel_"+this.navCursor)[0].style.display = "flex";
        },

	    videoLoad: function () {
		    fetch(`/api/${this.$route.params.id}/video`, { method: 'GET' })
		    .then( res => res.json() )
		    .then( json => {
		    	this.videoList = json;
		    	console.log(this.videoList)
		    })
		    .catch(err => console.log(err))
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
		},
		
		setChatMode: function(io) {
			this.isChatMode = io;
			
			if(this.isChatMode) {
				this.preChatModeMenu = this.navCursor;
				this.changeNav("home");
				
				document.querySelectorAll("#channel .chat_switched_elem").forEach(elem => {
					elem.classList.add("chat_mode")
				});
				
				document.querySelector(".m-active").classList.add("hidden");
				
				document.querySelector(".channel_info").classList.add("chat_switch_slide");
				document.querySelector(".video-js").classList.add("chat_switch_video_slide");
				document.querySelector("#channelChat").classList.add("chat_switch_chat_slide");
			}
			else {
				console.log(this.preChatModeMenu)
				
				if(this.preChatModeMenu != null) 
					this.changeNav(this.preChatModeMenu);
				
				this.changeNav(this.navCursor);
				
				document.querySelectorAll("#channel .chat_switched_elem").forEach(elem => {
					elem.classList.remove("chat_mode")
				});
				
				document.querySelector(".m-active").classList.remove("hidden");
				
				document.querySelector(".channel_info").classList.remove("chat_switch_slide");
				document.querySelector(".video-js").classList.remove("chat_switch_video_slide");
				document.querySelector("#channelChat").classList.remove("chat_switch_chat_slide");
				
				initMenu("#myMenu");
				update_modal();
			}
		},
		
		chatModeToggle: function() {
			this.setChatMode(this.isChatMode?false:true);
		},
		
		updateSubscribeNumber: function() {
			fetch(`/api/channel/${this.member.ownChannelId}/subscribe/`, { method: 'GET' })
	  		.then( res => res.json() )
	  		.then( json => {
	  			this.member.nsubscribe = json;
	  		})
			.catch(err => console.log(err))
		},
		
		subscribe: function() {
			fetch(`/api/channel/${this.member.ownChannelId}/subscribe/`, { method: 'POST' })
	  		.then( res => {
				if(res.status == 200) {
					this.isSubscribed = true;
					
					this.updateSubscribeNumber();
				}
			})
			.catch(err => console.log(err))
		},
		
		unsubscribe: function() {
			fetch(`/api/channel/${this.member.ownChannelId}/subscribe/`, { method: 'DELETE' })
	  		.then( res => {
				if(res.status == 200) {
					this.isSubscribed = false;
					
					this.updateSubscribeNumber();
				}
			})
			.catch(err => console.log(err))
		}
		
    },

    mounted() {
    	
	    fetch('/api/user/' + this.$route.params.id, {
		    method: 'GET',
		    headers: {
			    "Content-Type": "application/json"
		    }
	    })
	    .then(res=>res.json())
	    .then(json => {
		    this.member = json;
		    console.log(this.member);

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
		    
		    if(this.$store.state.member.id != this.member.id) {
		    	fetch(`/api/channel/${this.member.ownChannelId}/is_subscribe/`, { method: 'GET' })
		  		.then( res => res.json())
		  		.then( json => this.isSubscribed = json )
				.catch(err => console.log(err))
		    }
		    
	    })
	    .then(() => {
	    	initMenu("#myMenu");
	    	
	    	document.getElementsByClassName("channel_"+this.navCursor)[0].style.display = "flex";
	        document.getElementById(this.navCursor+'_btn').className += ' active';
	        
		    videojs(this.$refs.streamingVideo);
		    
		    this.setChatMode(this.isChatMode);
	    })
	    .catch(err => console.log(err));
    },
    
	beforeDestroy() {
		if (this.$refs.streamingVideo) {
			this.$refs.streamingVideo.dispose()
		}
		
		document.querySelectorAll("#channel .chat_switched_elem").forEach(elem => {
			elem.classList.remove("chat_mode");
		})
	}
    
};