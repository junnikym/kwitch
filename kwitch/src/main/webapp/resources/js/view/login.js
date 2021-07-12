// computed: {
//     emailValidation() {
//         let re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
//         return re.test(this.userItems.id);
//     }
// }

const loginForm = new Vue({
    el: '#loginForm',
    data: {
        emailInput: '',
        pwInput: ''
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