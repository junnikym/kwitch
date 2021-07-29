const routes = [
	{
		path: '/',
		component: {template : "<div><h1>home</h1></div>"}
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
		path: '/c/:communityId',
		component: CommunityComponent,
		children: [
			
			{
				path: '',
				component: CommunityHomeComponent
			},

			// Menu Routes
			{
				path: 'm/:menuId',
				component: ChannelComponent
			}

		]
	},
	{
		/**
		 * Post Related Routes
		 */
		path: '/p/:id',
		component: communityPostComponent,
		children: [

			// Post Upload & Update
			{
				path: 'Upload/:menuId',
				component: ChannelComponent
			}

		]
	}
];

Vue.component('header-component', HeaderComponent);
Vue.component('login-component', LoginComponent);

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
