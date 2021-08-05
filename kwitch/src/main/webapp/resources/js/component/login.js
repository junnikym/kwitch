const LoginComponent = {
    template: loginTemplate,
    store: gStore,
    data() { return {
        emailInput: '',
        pwInput: '',
		wrongInput: 0,
		wrongCaptcha: 0,

		captchaInput: '',
			
		stage: 'login',
    }},
    methods: {

    	modalClose: function() {
		    parent.document.getElementById('model_login_close').click();
	    },
    	
    	resetData: function() {
    		this.emailInput= '';
    		this.pwInput= '';
    		this.captchaInput= '';
    		this.wrongInput= 0;
    		this.wrongCaptcha= 0;
    		
    		if(this.stage == 'captcha') {
    			this.stageSwitch()
    		}
    	},
    	
    	stageSwitch: function() {
            document.getElementsByClassName(this.stage+"_stage")[0].style.display = "none";
            this.stage=(this.stage=='login')?'captcha':'login';
            document.getElementsByClassName(this.stage+"_stage")[0].style.display = "flex";
    	},
    	
        checkAndLogin: function() {

        	/**
			 * Check Cpatcha
			 */
        	
        	fetch('/captcha/check', {
        		method: 'POST',
        		headers: {
        			"Content-Type": "application/json"
        		},
        		body: this.captchaInput
        	}).then(res => {
        		if(res.status == 200) {
        			
        			/**
        			 * Login
        			 */
        			
        			fetch('/api/auth/login', {
    	                method: 'POST',
    	                headers: {
    	                    "Content-Type": "application/json"
    	                },
    	                body: JSON.stringify({
    	                    "email": this.emailInput,
    	                    "pw": this.pwInput
    	                })
    	            })
    	            .then(res => {
    	            	if(res.status == 200) {
    	            		this.resetData();
    	            		
    	            		return res.json();
    	            	}
    	            	else {
    	            		this.wrongInput++;
    						this.resetCaptcha();
    						if(this.stage != 'login')
    							this.stageSwitch();
    						
    						return null;
    	            	}
    	            })
    	            .then(json => {
    	            	if(json != null) {
    	            		this.$store.commit('setMember', {member: json})
    	            		
    	            		const modal = document.getElementById("modal-login");
    	            		modal.classList.remove("open");
    	            		
    	            		window.location.reload();
    	            	}
    	            	else {
    	            		console.log("else - running")
    	            	}
    	            		
    	            })
    	            .catch(err => console.log(err))
        			
        			
        			
        		}
        		else {
        			this.wrongCaptcha++;
        			
        			if(this.wrongCaptcha > 5)
        				this.resetCaptcha();
        		}
        	}) 
        	.catch(err => console.log(err))
        	
        },
        
        back: function() {
        	this.resetData();
        	
	    	if(location.pathname == "/login")
	    		this.$router.back();
	    },
	    
	    resetCaptcha: function() {
	    	var rand = Math.random(); 
	    	var url = '/captcha/image?rand='+rand; 
	    	document.querySelector('.captcha_image').setAttribute('src', url);
	    	
	    	this.captchaInput = '';
	    }
    },
	mounted: function() {
		this.resizeIframe();
	}
};

function press(f){ 
	if(f.keyCode == 13) loginForm.submit();
}

