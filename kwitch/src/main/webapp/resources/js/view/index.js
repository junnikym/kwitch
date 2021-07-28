const Home = {
	template: "<div><h1>Home</h1><p>This is home page</p></div>"
};

const Detail = {
	template: '	<iframe src="/resources/template/detail.jsp" name="detail" class="mainFrame">\
					<input type="hidden" name="memberId" v-bind:value="$route.params.id" >\
				</iframe>'
};

const CommunityHome = {
	template: '	<iframe src="/resources/template/community/communityHome.jsp" name="communityHome" class="mainFrame">\
					<input type="hidden" name="communityId" v-bind:value="$route.params.id" >\
				</iframe>'
};

const CommunityPost = {
	template: '	<iframe src="/resources/template/community/communityPost.jsp" name="communityPost" class="mainFrame">\
					<input type="hidden" name="postId" v-bind:value="$route.params.id" >\
				</iframe>'
};

const CommunityPostList = {
	template: '	<iframe src="/resources/template/community/communityPostList.jsp" name="communityPostList" class="mainFrame">\
					<input type="hidden" name="menuId" v-bind:value="$route.params.id" >\
				</iframe>'
};

const CommunityPostUpdate = {
	template: '	<iframe src="/resources/template/community/communityPostEditor.jsp" name="communityPostEditor" class="mainFrame">\
					<input type="hidden" name="postId" v-bind:value="$route.params.id" >\
				</iframe>'
};

const CommunityPostUpload = {
		template: '	<iframe src="/resources/template/community/communityPostEditor.jsp" name="communityPostEditor" class="mainFrame">\
						<input type="hidden" name="communityId" v-bind:value="$route.params.id" >\
					</iframe>'
	};

const routes = [
  { path: '/', component: Home },
  { 
	  path: '/detail/:id', 
	  component: Detail
  },
  { 
	  path: '/community/:id', 
	  component: CommunityHome 
  },
  {
	  path: '/community/post/:id',
	  component: CommunityPost 
  },
  {
	  path: '/community/menu/:id',
	  component: CommunityPostList
  },
  {
	  path: '/community/post/:id/edit',
	  component: CommunityPostUpdate
  },
  {
	  path: '/community/post/upload/:id',
	  component: CommunityPostUpload
  }
];

const router = new VueRouter({
  routes: routes,
  mode: 'history',
  base: '/'
});

const mainContent = new Vue({
  router: router
}).$mount('#mainContent')
