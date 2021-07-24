const detail = new Vue({
    el: '#community',
    data: {
        postList: [],
    },
    methods: {
    	
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
        
    },

    mounted() {
        document.getElementsByClassName("detail_"+this.navCursor)[0].style.display = "flex";
        document.getElementById(this.navCursor+'_btn').className += ' active';
    }
    
});