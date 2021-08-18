const channelChatComponent = {
    template: channelChatTemplate,
    store: gStore,
    props: ['channelId'],
    data() { return {
	    ws: undefined,
	    messageInput: '',
	    chatList: [],
    }},
    methods: {
    	
		send: function() {
			this.ws.send (JSON.stringify({
            	message: this.messageInput, 
            }));
            
            this.messageInput = '';
		},
		
    },
    watch: {
    	channelId: function(val) {
    		if(val) {
    			if(this.ws!==undefined && this.ws?.readyState!==WebSocket?.CLOSED) 
    				this.ws.close();
    			
    	        this.ws=new WebSocket("ws://localhost:8080/ws/chat?channel="+this.channelId);
    	        
    	        this.ws.onopen=function(event){
    	            if(event.data===undefined) return;
    	        };
    	        
    	        console.log(this.chatList);
    	        this.ws.onmessage = ({data}) => {
    	        	json = JSON.parse(data);
    	        	
    	        	console.log(json);
    	        	if(json?.error) {
    	        		alert("you need login")
    	        	}
    	        	else {
    	        		this.chatList.push(json);
    	        	}
    	        	
    	        	console.log(this.chatList);
    	        };
    		}
    	}
    },
    
	beforeDestroy() { ws.close(); }
    
};