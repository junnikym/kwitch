const HeaderComponent = {
    template: headerTemplate,
    data() { return {
        searchInput: '',
        notificationList: [],
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
        },
        eventSourceConnection: function() {
            const eventSource = new EventSource(`/api/notification/connection`);

            eventSource.onopen = (e) => console.log(e);

            eventSource.onerror = (e) => console.log(e);

            eventSource.onmessage = (e) => {
                this.notificationList.push(e);
            };
        },
        checkNotification: function(item) {
            this.$router.push({ path: item.href });
        }

    },
    mounted() {
    	update_modal();

        fetch('/api/notification', {
            method: 'GET',
            headers: {"Content-Type": "application/json"}
        })
        .then(res => res.json())
        .then(json => {
            if(json)
                this.notificationList = json;

            this.eventSourceConnection();
        });
    }
};