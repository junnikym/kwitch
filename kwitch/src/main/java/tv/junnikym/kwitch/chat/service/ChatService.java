package tv.junnikym.kwitch.chat.service;

import org.springframework.web.socket.WebSocketSession;

import tv.junnikym.kwitch.chat.vo.ChatVO;

public interface ChatService {
	
	void connection(String channelId, WebSocketSession session) throws Exception;
	
	void disconnection(String channelId, WebSocketSession session) throws Exception;
	
	Integer countParticipant(String channelId) throws Exception;
	
	String sendMessage(ChatVO vo) throws Exception;
	
}
