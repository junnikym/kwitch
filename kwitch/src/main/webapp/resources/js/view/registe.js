const signupForm = new Vue({
    el: '#signupForm',
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
    },
    methods: {
    	signup: function(e) {
            e.preventDefault();
            
            console.log(this.birthYearInput.number+this.birthMonthInput.number+this.birthDayInput.number);

            fetch('/api/signup', {
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
            		// do login fail
            	}
            	
            	return res.json()
            })
            .then(json => {
            	console.log(json.data);
            })
            .catch(err => console.log(err))
        }
    }
});