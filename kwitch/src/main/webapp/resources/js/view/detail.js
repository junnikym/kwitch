const detail = new Vue({
    el: '#detail',
    data: {
        navCursor: 'about',
        profileImageSetter: false,
        
        privateState: {},
        sharedState: store.state
    },
    methods: {
    	
    	
        changeNav: function(cursor) {
        	console.log(cursor)
        	
            if(this.navCursor == cursor) return;

            document.getElementsByClassName("detail_"+this.navCursor)[0].style.display = "none";
            this.navCursor=cursor;
            document.getElementsByClassName("detail_"+this.navCursor)[0].style.display = "flex";
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
		}
    },

    mounted() {
        document.getElementsByClassName("detail_"+this.navCursor)[0].style.display = "flex";
        document.getElementById(this.navCursor+'_btn').className += ' active';
    }
    
});