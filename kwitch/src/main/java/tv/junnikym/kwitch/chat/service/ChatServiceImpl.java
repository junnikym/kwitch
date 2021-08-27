package tv.junnikym.kwitch.chat.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import tv.junnikym.kwitch.chat.dao.ChatDAO;
import tv.junnikym.kwitch.chat.vo.ChatVO;

@Service("ChatService")
public class ChatServiceImpl implements ChatService {
	
	@Resource(name="ChatDAO")
	ChatDAO chatDAO;
	
	private static Map<String, List<WebSocketSession>> clients = new HashMap<>();
	
	@Override
	public void connection(String channelId, WebSocketSession session) throws Exception {
        
        if( ! clients.containsKey(channelId) ) 
        	clients.put(channelId, new ArrayList<WebSocketSession>());
        
        clients.get(channelId).add(session);
	}
	
	@Override
	public void disconnection(String channelId, WebSocketSession session) throws Exception{
		
		clients.get(channelId).remove(session);
	}
	
	@Override
	public Integer countParticipant(String channelId) throws Exception {
		
		return clients.get(channelId).size();
	}

	@Override
	public String sendMessage(ChatVO vo) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String result = chatDAO.sendMessage(vo);
		if(result != null) {
			for (WebSocketSession it : clients.get(vo.getChannelId())) {
				it.sendMessage(
						new TextMessage(mapper.writeValueAsString(vo)));
			}
		}
		
		return result;
	}
	
}
