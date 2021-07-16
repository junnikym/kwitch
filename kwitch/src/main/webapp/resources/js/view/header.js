const header = new Vue({
    el: '#header',
    data: {
        searchInput: '',

        privateState: {},
        sharedState: store.state
    },
    methods: {
        detail: function(id) {
        	location.href="/detail/"+id;
        },
        logout: function() {
            fetch('/api/auth/logout', {
                method: 'GET',
            })
            .then(res => {
            	store.clearLoginUser();
            	location.href="/";
            })
            .catch(err => console.log(err))
        }
    }
});