const LoginComponent = {
    template: '<div>login</div>'
}
const MainComponent = {
    template: '<div>main</div>'
}

const appRouter = new VueRouter({
    routes: [
        {
            path:'/test_a',
            component:
        }
    ]
});

const app = new Vue({
    el: '#app',
    router: appRouter,
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
                // .then(function(response) {
                //     console.log(response);
                // })
                .catch(err => console.log(err))
        }
    }
});