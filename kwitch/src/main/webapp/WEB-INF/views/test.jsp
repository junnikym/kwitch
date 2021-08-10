<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
    <div>
        <input type="text" id="sender" style="display: none;">
        <input type="text" id="messageinput">
    </div>
    <div>
        <button type="button" onclick="openSocket();">Open</button>
        <button type="button" onclick="send();">Send</button>
        <button type="button" onclick="closeSocket();">Close</button>
    </div>
    <!-- Server responses get written here -->
    <div id="messages">
    
    </div>
    
    <script type="text/javascript">
        var ws;
        var messages=document.getElementById("messages");
        
        function openSocket(){
            if(ws!==undefined && ws.readyState!==WebSocket.CLOSED){
                writeResponse("WebSocket is already opened.");
                return;
            }
            //웹소켓 객체 만드는 코드
            ws=new WebSocket("ws://localhost:8080/ws/chat");
            
            ws.onopen=function(event){
                if(event.data===undefined) return;
                
                writeResponse(event.data);
            };
            
            ws.onmessage=function(event){
                writeResponse(event.data);
            };
        }
        
        function send(){
        	console.log("send function");
            
            
        }
        
        function closeSocket(){
            ws.close();
        }
        
        function writeResponse(text){
            messages.innerHTML+="<br/>"+text;
        }
  </script>
</body>
</html>