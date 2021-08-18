package tv.junnikym.kwitch.chat.dao;

import org.springframework.stereotype.Repository;

import tv.junnikym.kwitch.chat.vo.ChatVO;
import tv.junnikym.kwitch.util.AbstractMapper;

@Repository("ChatDAO")
public class ChatDAOImpl extends AbstractMapper implements ChatDAO {

	@Override
	public String sendMessage(ChatVO vo) throws Exception {
		insert("ChatDAO.sendMessage", vo);
		return vo.getId();
	}

}
