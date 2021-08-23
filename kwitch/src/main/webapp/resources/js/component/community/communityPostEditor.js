
const communityPostEditorComponent = {
	template: communityPostEditorTemplate,
	store: gStore,
    data() { return {
    	editor: undefined,
    	postContent: {
    		title: '',
    		menuList: null,
    		menuTitle: null,
    	},
    	prePostContent: {},
    	postId: null,
    	communityId: null,
    	menuList: [],
    	selectedMenuId: '',
    }},
    methods: {
    	
        uploadPost: function() {
			const editorData = this.editor.getData();
				
			if(this.postId) {
				if( this.prePostContent.title === this.postContent.title
					&& this.prePostContent.menuId === this.postContent.menuId
					&& this.prePostContent.content === editorData
				) {
					alert("not changed content");
					return
				}

				console.log(editorData);
				
				fetch('/api/community/post/' + this.postId, {
          		  	method: 'PUT',
	          		headers: {
	        			"Content-Type": "application/json"
	        		},
	                body: JSON.stringify({
	                	"menuId": this.selectedMenuId,
	                    "title": this.postContent.title,
	                    "content": editorData,
	                    "isCommentBlock": false,
	                })
	        		
      			})
              	.then(res => {
              		if(res.status == 401) {
              			throw "글을 등록할 권한이 없습니다."
              		}
              		if(res.status == 200) {
              			if(this.postId) {
              				this.$router.push("/c/p/"+this.postId);
              			}
              		}
              	})
              	.catch(err => {
              		alert(err);
              		this.$router.push('/c/'+this.postId);
              	})
			}
			
			
			if(this.communityId) {

				if( ! this.postContent.title) {
					alert("title is empty");
					return
				}

				if( ! editorData) {
					alert("content is empty");
					return
				}

				if( ! this.selectedMenuId) {
					alert("not select menu");
					return
				}

				fetch('/api/community/post/', {
          		  	method: 'POST',
	          		headers: {
	        			"Content-Type": "application/json"
	        		},
	                body: JSON.stringify({
	                    "communityId": this.communityId,
	                    "menuId": this.selectedMenuId,
	                    "title": this.postContent.title,
	                    "content": editorData,
	                    "isCommentBlock": false,
	                })
	        		
      			})
              	.then(res => {
              		if(res.status == 401) {
              			throw "글을 등록할 권한이 없습니다."
              		}
              		
              		if(res.status == 200) {
              			return res.text();
              		}
              		else {
              			alert("error");
              		}

              	})
              	.then(txt => {
              		parent.document.location.href = "/c/p/"+txt;
              	})
              	.catch(err => {
              		alert("글을 등록할 권한이 없습니다.");
              		this.$router.push('/c/'+this.communityId);
              	})
			}
        },
        
        createCKEditor: function() {
        	
        	ClassicEditor
	        .create( document.querySelector( '#editor' ), {
//			    		ckfinder: {
//			    			uploadUrl: '/asdf/asdf'
//			    		},
//						alignment: {
//							options: [ 'left', 'center', 'right' ]
//						}
	        } )
	        .then( (newEditer) => {
	        	this.editor = newEditer;
	        })
	        .catch( (error) => {
	            console.error( error );
	        } );
        	
        	this.editor.config.resize_enabled = false;
        	
        }

    },
    
    mounted(){
    	const postId = this.$route.params.postId;
    	const communityId = this.$route.params.communityId;
    	
    	this.postId = postId?postId:null;
    	this.communityId = communityId?communityId:null;
    	this.isExist = postId?true:false;
        	
    	if(this.postId) {
        	fetch('/api/community/post/' + this.postId, {
      		  	method: 'GET',
          		headers: {
        			"Content-Type": "application/json"
        		}
  			})
          	.then(res => res.json())
          	.then(json => {
          		this.$store.commit('connectCommunity', json.communityId);
          		
				this.postContent = json;
				this.prePostContent = copyObj(this.postContent);
				console.log(this.prePostContent);
				
				if(json.writerId != this.$store.state.member.id) {
					throw "게시글 작성자가 아닙니다."
					this.$router.push('/c/p/'+this.$route.params.postId);
				}
				
				fetch('/api/community/' + json.communityId + '/menu', {
	      		  	method: 'GET',
	          		headers: {
	        			"Content-Type": "application/json"
	        		}
	  			})
	          	.then(res => res.json())
	          	.then(json => {
					this.menuList = json;
					this.selectedMenuId = this.postContent.menuId;
					this.createCKEditor();
	          	})
	          	.catch(err => console.log(err))
          	})
          	.catch(err => {
          		alert(err);
          		this.$router.replace('/c/p/'+this.$route.params.postId);
          	})
    	} 
    	
    	if(this.communityId){
    		
    		this.$store.commit('connectCommunity', this.$route.params.communityId);
    		
    		fetch('/api/community/' + this.communityId + '/menu', {
      		  	method: 'GET',
          		headers: {
        			"Content-Type": "application/json"
        		}
  			})
          	.then(res => res.json())
          	.then(json => {
				this.menuList = json;
				this.createCKEditor();
          	})
          	.catch(err => console.log(err))
    	}
    }
}
