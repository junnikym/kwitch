package tv.junnikym.kwitch.chat.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatHandler extends TextWebSocketHandler {

	private Map<String,WebSocketSession> clients = new ConcurrentHashMap<String,WebSocketSession>();

	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(session.getId() + " connected ");
		clients.put(session.getId(), session);
	}
	
	@Override
	protected void handleTextMessage(
			WebSocketSession session, 
			TextMessage message
	) throws Exception {
		
		System.out.println(session.getId() + "로부터 메시지 수신 : " + message.getPayload());
		for(WebSocketSession s : clients.values()) {
			s.sendMessage(message);
			System.out.println(s.getId() + "에 메시지 발송 : " + message.getPayload());
		}
	}

	@Override
	public void handleTransportError(
			WebSocketSession session, 
			Throwable exception
	) throws Exception {
		System.out.println(session.getId() + " ) Exception : " + exception.getMessage());
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println(session.getId() + " exit ");
		clients.remove(session.getId());
	}
	
}

