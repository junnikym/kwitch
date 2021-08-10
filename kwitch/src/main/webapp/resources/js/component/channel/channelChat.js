const channelChatComponent = {
    template: channelChatTemplate,
    store: gStore,
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
	mounted() { 
    	
    	if(this.ws!==undefined && this.ws?.readyState!==WebSocket?.CLOSED) 
			this.ws.close();
		
        this.ws=new WebSocket("ws://localhost:8080/ws/chat");
        
        this.ws.onopen=function(event){
            if(event.data===undefined) return;
    		
        };
        
        console.log(this.chatList);
        this.ws.onmessage = ({data}) => {
        	this.chatList.push(JSON.parse(data));
        	console.log(this.chatList);
        };
    	
    },
	beforeDestroy() { ws.close(); }
    
};