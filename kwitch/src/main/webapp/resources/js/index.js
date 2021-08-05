const routes = [
	{
		path: '/',
		component: mainPageComponent
	},
	{
		path: '/q/:searchQuery',
		component: searchComponent,
		props: true
	},
	{
		/**
		 * Channel Related Routes
		 */
		path: '/channel/:id',
		component: ChannelComponent
	},
	{
		/**
		 *  Community Related Routes
		 */
		path: '/c',
		component: CommunityComponent,
		children: [
			
			{
				path: ':communityId',
				component: CommunityHomeComponent,
			},
			
			/**
			 *  Post Related Routes
			 */
			
			{
				path: 'p/:postId',
				component: communityPostComponent,
			},
			{
				path: 'p/:postId/edit',
				component: communityPostEditorComponent,
			},
			{	
				path: ':communityId/upload/',
				component: communityPostEditorComponent,
			},
			
			
			/**
			 * 	---------- End Of Post Related ----------
			 */
			
			// < Menu >
			{
				path: 'm/:menuId',
				component: communityPostListComponent,
			},
			
		]
		/**
		 * 	---------- End Of Community Related ----------
		 */
	},
];

const router = new VueRouter({
	routes: routes,
	mode: 'history',
	base: '/'
});

const homeVue = new Vue({
	el: '#home',
	router: router,
	store: gStore,

	mounted() {	
		fetch('/api/my', {
            method: 'GET',
            headers: {"Content-Type": "application/json"}
        })
        .then(res => res.json())
        .then(json => {
        	if(json)
        		this.$store.commit('setMember', {member: json})
        })
        .catch(err => console.log(err))
		
//		this.$store.commit('increment')
//
//		console.log(this.$store.state.count);
	}
})
