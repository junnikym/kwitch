const channelVideoThumbItemComponent = {
    template: channelVideoThumbItemTemplate,
    props: ['item'],
    data() { return {

    }},
    methods: {
        calcTime: function(t) {
            const time = new Date(t.replace(/-/g, "/"));
            
            return timeDifference(Date.now(), time);
        }
    },
    mounted() {
    	
    }
};