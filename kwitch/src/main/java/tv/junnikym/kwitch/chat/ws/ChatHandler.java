package tv.junnikym.kwitch.chat.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import tv.junnikym.kwitch.chat.service.ChatService;
import tv.junnikym.kwitch.chat.vo.ChatVO;

public class ChatHandler extends TextWebSocketHandler {
	
	@Resource(name="ChatService")
	private ChatService chatService;

	private Map<String, List<WebSocketSession>> clients = new HashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
         
        Map<String, Object> map	= session.getAttributes();
        
        String channelId = (String) map.get("channelId");
        
        if( ! clients.containsKey(channelId) ) 
        	clients.put(channelId, new ArrayList<WebSocketSession>());
        
        clients.get(channelId).add(session);
    }

	 @Override
	public void handleMessage(
		WebSocketSession session, 
		WebSocketMessage<?> message
	) throws Exception {
		 
		ObjectMapper 		mapper 		= new ObjectMapper();
		String 				payloadJson = (String) message.getPayload();
		Map<String, String> payloadMap 	= mapper.readValue(payloadJson, Map.class);
		
		Map<String, Object> map = session.getAttributes();
		String memberId  = (String) map.get("memberId");
		String channelId = (String) map.get("channelId");
		
		ChatVO vo;
		
		if(memberId == null) 
			vo = ChatVO.builder().error("need to login").build();
		else {
			vo = ChatVO.builder()
						.channelId(channelId)
						.writerId(memberId)
						.text(payloadMap.get("message"))
						.build();
			
			System.out.println("vo is " + vo.toString());
			
			String service_result = chatService.sendMessage(vo);
			
			System.out.println("service_result : " + service_result);
			
			if(service_result != null) {
				
				for (WebSocketSession it : clients.get(channelId)) {
					it.sendMessage(
							new TextMessage(mapper.writeValueAsString(vo)));
				}
			}
			
		}
	}

	@Override
	public void handleTransportError(
			WebSocketSession session, 
			Throwable exception
	) throws Exception {
		super.handleTransportError(session, exception);
		System.out.println(session.getId() + " ) Exception : " + exception.getMessage());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		Map<String, Object> map	= session.getAttributes();
        
        String channelId = (String) map.get("channelId");
        
        clients.get(channelId).remove(session);
	}

}
