const communityPostItemSmallComponent = {
    template: communityPostItemSmallTemplate,
    props: ['item', 'index'],
    data() { return {

    }},
    methods: {
        calcTime: function(t) {
            const time = new Date(t.replace(/-/g, "/"));
            
            return timeDifference(Date.now(), time);
        },
        removeHtmlTag: function(text) {
        	text = text.replace(/<br\/>/ig, "\n");
        	text = text.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "");
        	text = text.replace('&nbsp;', ' ');
        	return text;
        }
    },
    mounted() {
    	console.log(this.item);
    }
};