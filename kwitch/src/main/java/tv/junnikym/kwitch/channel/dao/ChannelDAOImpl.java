package tv.junnikym.kwitch.channel.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tv.junnikym.kwitch.channel.vo.ChannelVO;
import tv.junnikym.kwitch.util.AbstractMapper;

@Repository("ChannelDAO")
public class ChannelDAOImpl extends AbstractMapper implements ChannelDAO {

	@Override
	@Transactional
	public int regist(ChannelVO vo) throws Exception {
		insert("ChannelDAO.regist", vo);
		return insert("CommunityDAO.regist", vo.getId());
	}
	
}
