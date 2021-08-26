const commentComponent = {
	template: commentTemplate,
	store: gStore,
	props: ['targetId', 'usage'],
	data() { return {
		editor: null,
		commentList: [],
	}},

	watch: {
		targetId() {
			if(this.targetId == null)
				return;

			this.init();
		}
	},

	methods: {
		regist: function() {
			const editorData = this.editor.getData();

			const url = this.usage == "COMMENT_USAGE_POST" ? '/api/community/comment' : '/api/comment/';

			fetch(url, {
				method: 'POST',
				headers: {
					"Content-Type": "application/json"
				},
				body: JSON.stringify({
					"targetId": this.targetId,
					"usage": this.usage,
					"text": editorData
				})

			})
			.then(res => {
				if(res.status == 401) {
					throw "댓글을 남길 권한이 없습니다."
				}

				if(res.status == 200) {
					return res.json();
				}

			})
			.then(json => {
				this.commentList.unshift(json);
			})
			.catch(err => {
				console.log(err);
			})
		},

		init: function() {
			fetch('/api/comment/list', {
				method: 'POST',
				headers: {
					"Content-Type": "application/json"
				},
				body: JSON.stringify({
					"targetId": this.targetId,
					"usage": this.usage,
				})
			})
			.then(res => res.json())
			.then(json => {
				this.commentList = json;
			})
			.catch(err => {
				console.log(err);
			})
		}
	},

	mounted() {
		ClassicEditor.create( document.querySelector( '#commentEditor' ), {
			on : {
				// maximize the editor on startup
				'instanceReady' : function( evt ) {
					evt.editor.resize("100%", "75px");
				}
			},
			toolbar: [ 'bold', 'italic', 'link', 'undo', 'redo', 'numberedList', 'bulletedList' ]
		} )
		.then( (newEditer) => {
			this.editor = newEditer;
		})
		.catch( (error) => {
			console.error( error );
		} );

	}
};