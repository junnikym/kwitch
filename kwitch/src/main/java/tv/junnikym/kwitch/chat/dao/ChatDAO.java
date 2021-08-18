package tv.junnikym.kwitch.chat.dao;

import tv.junnikym.kwitch.chat.vo.ChatVO;

public interface ChatDAO {
	
	String sendMessage(ChatVO vo) throws Exception;
	
}
