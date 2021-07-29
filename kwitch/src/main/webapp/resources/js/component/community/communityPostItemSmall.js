const communityPostItemSmallComponent = {
    template: communityPostItemSmallTemplate,
    props: ['item', 'index'],
    data() { return {

    }},
    methods: {
        calcTime: function(t) {
            const time = new Date(t.replace(/-/g, "/"));
            return timeDifference(Date.now(), time);
        }
    },
    mounted() {
    	console.log(this.item);
    }
};