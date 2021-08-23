const channelVideoComponent = {
    template: channelVideoTemplate,
    props: ['item'],
    data() { return {

    }},
    methods: {
        loadVideo: function() {
			fetch('/api/profile/image/'+id, {
				method: 'POST',
				body: formData
			})
			.then(res=>{
			if(res.status == 200)
				location.reload();
			})
			.catch(err => console.log(err))
        }
    },
    mounted() {
    	videojs(this.$refs.channelVideoStream)
    }
};