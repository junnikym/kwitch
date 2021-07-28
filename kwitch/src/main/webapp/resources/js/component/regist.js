const signupForm = new Vue({
    el: '#registForm',
    data: {
        emailInput: '',
        pwInput: '',
    	nameInput: '',
    	aliasInput: '',
    	
    	phoneMidInput: '',
    	phoneLastInput: '',
    	
    	birthYearInput: '',
    	birthMonthInput: '',
    	birthDayInput: '',
    	
    	profileImage: null,
    	
    	stage: 'setting'
    },
    methods: {
    	stageSwitch: function(stage) {
    		console.log(stage);
    		
    		if(this.stage == stage) return;

            document.getElementsByClassName(this.stage+"_stage")[0].style.display = "none";
            this.stage=(stage=='regist')?'regist':'setting';
            document.getElementsByClassName(this.stage+"_stage")[0].style.display = "flex";
    	},
    	
    	onSubmit: function(e) {
            e.preventDefault();
            
            if(this.stage=='regist') {
	            fetch('/api/regist', {
	                method: 'POST',
	                headers: {
	                    "Content-Type": "application/json"
	                },
	                body: JSON.stringify({
	                    "email": this.emailInput,
	                    "pw": this.pwInput,
	                    "name": this.nameInput,
	                    "alias": this.aliasInput,
	                    "phone": "010"+this.phoneMidInput+this.phoneLastInput,
	                    "birth": 
	                    	this.birthYearInput.number+"-"+
	                    	this.birthMonthInput.number+"-"+
	                    	this.birthDayInput.number,
	                    
	                })
	            })
	            .then(res => {
	            	if(res == null) {
	            		// do regist fail
	            	}
	            	
	            	return res.json()
	            })
	            .then(json => {
	            	this.stageSwitch('setting');
	            })
	            .catch(err => console.log(err))
            }
            
            else if(this.stage=='setting') {
            	const formData = new FormData();
            	const profileImage = document.getElementById('upload_profile_image');
            	
            	formData.append('profileImage', profileImage.files[0]);
            	
            	console.log(profileImage.files[0] );
            	
            	fetch('/api/upload/profile/image/', {
            		  method: 'POST',
            		  body: formData
        		})
   	            .then()
   	            .catch(err => console.log(err))
            }
        },
        
        uploadImage: function(e) {
        	 e.preventDefault();
        },
        
        back: function() { back(); }
    }
});