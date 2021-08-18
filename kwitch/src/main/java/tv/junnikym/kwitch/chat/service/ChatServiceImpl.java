package tv.junnikym.kwitch.chat.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.chat.dao.ChatDAO;
import tv.junnikym.kwitch.chat.vo.ChatVO;

@Service("ChatService")
public class ChatServiceImpl implements ChatService {
	
	@Resource(name="ChatDAO")
	ChatDAO chatDAO;

	@Override
	public String sendMessage(ChatVO vo) throws Exception {
		return chatDAO.sendMessage(vo);
	}
	
}
