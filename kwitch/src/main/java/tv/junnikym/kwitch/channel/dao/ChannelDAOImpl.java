package tv.junnikym.kwitch.channel.dao;

import org.springframework.stereotype.Repository;

import tv.junnikym.kwitch.channel.vo.ChannelRoleVO;
import tv.junnikym.kwitch.channel.vo.ChannelVO;
import tv.junnikym.kwitch.util.AbstractMapper;

@Repository("ChannelDAO")
public class ChannelDAOImpl extends AbstractMapper implements ChannelDAO {

	@Override
	public String regist(ChannelVO vo) throws Exception {
		insert("ChannelDAO.regist", vo);
		return vo.getId();
	}
	
	@Override
	public String setDefaultRole(String id) throws Exception {
		String channelId = new String(id);
		
		insert("ChannelDAO.setDefaultRole", channelId);
		return channelId;
	}
	
	@Override
	public void giveRole(ChannelRoleVO vo) throws Exception {
		insert("ChannelDAO.giveRole", vo);
	}

	@Override
	public ChannelRoleVO getRole(ChannelRoleVO vo) throws Exception {
		return selectOne("ChannelDAO.getRole", vo);
	}
	
	/**
	 *  etc
	 * --------------------------------------------------
	 */
	
	public String getChannelIdByCommunityId(String id) throws Exception {
		return selectOne("ChannelDAO.getChannelIdByCommunityId", id);
	}
	
	public String getChannelIdByOwnerId(String id) throws Exception {
		return selectOne("ChannelDAO.getChannelIdByOwnerId", id);
	}
	
	public String getChannelIdByMenuId(String id) throws Exception {
		return selectOne("ChannelDAO.getChannelIdByMenuId", id);
	}
	
	public String getChannelIdByPostId(String id) throws Exception {
		return selectOne("ChannelDAO.getChannelIdByPostId", id);
	}
}
