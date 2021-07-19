const loginForm = new Vue({
    el: '#loginForm',
    data: {   	
        emailInput: '',
        pwInput: '',
		wrongInput: 0,
		wrongCaptcha: 0,

		captchaInput: '',
			
		stage: 'login',
    },
    methods: {
    	
    	resetData: function() {
    		emailInput= '';
            pwInput= '';
    		captchaInput= '';
    		stage= 'login';
    	},
    	
    	stageSwitch: function() {
            document.getElementsByClassName(this.stage+"_stage")[0].style.display = "none";
            this.stage=(this.stage=='login')?'captcha':'login';
            document.getElementsByClassName(this.stage+"_stage")[0].style.display = "flex";
    	},
    	
        checkAndLogin: function() {

        	fetch('/captcha/check', {
        		method: 'POST',
        		headers: {
        			"Content-Type": "application/json"
        		},
        		body: this.captchaInput
        	}).then(res => {
        		if(res.status == 200) {
        			
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
    	            	if(res.status != 200) {
    						this.wrongInput++;
    						this.resetCaptcha();
    						if(this.stage != 'login')
    							this.stageSwitch();
    	            	}
    	            	else {
    	            		this.resetData();
    	            		location.reload();
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
	    		back();
	    },
	    
	    resetCaptcha: function() {
	    	var rand = Math.random(); 
	    	var url = '/captcha/image?rand='+rand; 
	    	document.querySelector('.captcha_image').setAttribute('src', url);
	    	
	    	this.captchaInput = '';
	    }
    }
});

function press(f){ 
	if(f.keyCode == 13) loginForm.submit();
}

