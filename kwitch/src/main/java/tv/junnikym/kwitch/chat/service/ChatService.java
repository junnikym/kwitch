package tv.junnikym.kwitch.chat.service;

import tv.junnikym.kwitch.chat.vo.ChatVO;

public interface ChatService {
	
	String sendMessage(ChatVO vo) throws Exception;
	
}
