// computed: {
//     emailValidation() {
//         let re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
//         return re.test(this.userItems.id);
//     }
// }
//(function() {
//    const send = XMLHttpRequest.prototype.send
//    XMLHttpRequest.prototype.send = function() { 
//        this.addEventListener('load', function() {

        	const loginForm = new Vue({
        	    el: '#loginForm',
        	    data: {
        	        emailInput: '',
        	        pwInput: '',
					wrongInput: 0,

					privateState: {},
					sharedState: store.state
        	    },
        	    methods: {
        	        login: function(e) {
        	            e.preventDefault();
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
        	            	if(res.status != 200)
								this.wrongInput++;
        	            	else {
        	            		location.reload();
        	            	}
        	            })
        	            .catch(err => console.log(err))
        	        },
        	        back: function() {
            	    	if(location.pathname == "/login") 
            	    		back();
            	    }
        	    }
        	});
        	
        	function press(f){ 
        		if(f.keyCode == 13) loginForm.submit();
        	}
        	
//        })
//        return send.apply(this, arguments)
//    }
//})()
