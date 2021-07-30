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
				path: 'p/:id',
				component: communityPostComponent,
			},
			{
				path: 'p/:postId/edit',
				component: communityPostEditorComponent
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
				component: ChannelComponent
			},
			
		]
		/**
		 * 	---------- End Of Community Related ----------
		 */
	},
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
		fetch('/api/my', {
            method: 'GET',
            headers: {"Content-Type": "application/json"}
        })
        .then(res => {
        	if(res.status == 200)
	        	return res.json();
        	else
        		return null;
        })
        .then(json => {
        	if(json)
        		this.$store.commit('setMember', {member: json})
        })
        .catch(err => console.log(err))
		
		this.$store.commit('increment')

		console.log(this.$store.state.count);
	}
})
