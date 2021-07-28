const headerComponent = {
    template: headerTemplate,
    store: gStore,
    data() { return {
        searchInput: ''
    }},
    methods: {
        detail: function(id) {
        	location.href="/channel/"+id;
        },
        logout: function() {
            fetch('/api/auth/logout', {
                method: 'GET',
            })
            .then(res => {
            	location.href="/";
            })
            .catch(err => console.log(err))
        }
    }
};