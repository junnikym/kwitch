const HeaderComponent = {
    template: headerTemplate,
    data() { return {
        searchInput: ''
    }},
    methods: {
        channel: function() {
        	this.$router.push("/channel/"+this.$store.state.member.id);
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
    },
    mounted() {
    	update_modal();
    }
};