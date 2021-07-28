const routes = [
	{
		path: '/',
		component: channelComponent
	},
	{
		/**
		 * Channel Related Routes
		 */
		path: '/channel/:id',
		component: channelComponent
	},
	{
		/**
		 *  Community Related Routes
		 */
		path: '/c/:communityId',
		component: channelComponent,
		children: [

			// Menu Routes
			{
				path: 'm/:menuId',
				component: channelComponent
			}

		]
	},
	{
		/**
		 * Post Related Routes
		 */
		path: '/p/:id',
		component: channelComponent,
		children: [

			// Post Upload & Update
			{
				path: 'Upload/:menuId',
				component: channelComponent
			}

		]
	}
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
		this.$store.commit('increment')

		console.log(this.$store.state.count);
	}
})
