package tv.junnikym.kwitch.channel.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tv.junnikym.kwitch.channel.vo.ChannelRoleVO;
import tv.junnikym.kwitch.channel.vo.ChannelVO;
import tv.junnikym.kwitch.channel.vo.SubscribeVO;
import tv.junnikym.kwitch.util.AbstractMapper;

@Repository("ChannelDAO")
public class ChannelDAOImpl extends AbstractMapper implements ChannelDAO {

	@Override
	public String regist(ChannelVO vo) throws Exception {
		insert("ChannelDAO.regist", vo);
		return vo.getId();
	}
	
	@Override
	public List<ChannelVO> getNewChannel() throws Exception {
		return selectList("ChannelDAO.getNewChannel");
	}
	
	
	
	@Override
	public String setDefaultRole(String id) throws Exception {
		String channelId = new String(id);
		
		insert("ChannelDAO.setDefaultRole", channelId);
		return channelId;
	}
	
	@Override
	public void grantRole(ChannelRoleVO vo) throws Exception {
		insert("ChannelDAO.grantRole", vo);
	}

	@Override
	public ChannelRoleVO getRole(ChannelRoleVO vo) throws Exception {
		return selectOne("ChannelDAO.getRole", vo);
	}
	
	@Override
	public String getDefaultRole(String channelId) throws Exception {
		return selectOne("ChannelDAO.getDefaultRole", channelId);
	}

	@Override
	public int revokeRole(ChannelRoleVO vo) throws Exception {
		return delete("ChannelDAO.revokeRole", vo);
	}
	
	/**
	 * Subscribe
	 * --------------------------------------------------
	 */
	
	@Override
	public int subscribe(SubscribeVO vo) throws Exception {
		return insert("ChannelDAO.subscribe", vo);
	}

	@Override
	public int unsubscribe(SubscribeVO vo) throws Exception {
		return delete("ChannelDAO.unsubscribe", vo);
	}

	@Override
	public Boolean isSubscribed(SubscribeVO vo) throws Exception {
		return selectOne("ChannelDAO.isSubscribed", vo);
	}
	
	@Override
	public Integer nSubscribe(String channelId) throws Exception {
		return selectOne("ChannelDAO.nSubscribe", channelId);
	}

	@Override
	public List<String> getSubscriberList(String channelId) throws Exception {
		return selectList("ChannelDAO.getSubscriberList", channelId);
	}

	@Override
	public List<ChannelVO> getSubscribeChannelList(String subscriberId) throws Exception {
		return selectList("ChannelDAO.getSubscribeChannelList", subscriberId);
	}
	
	/**
	 *  etc
	 * --------------------------------------------------
	 */
	
	@Override
	public String getChannelIdByCommunityId(String id) throws Exception {
		return selectOne("ChannelDAO.getChannelIdByCommunityId", id);
	}
	
	@Override
	public String getChannelIdByOwnerId(String id) throws Exception {
		return selectOne("ChannelDAO.getChannelIdByOwnerId", id);
	}
	
	@Override
	public String getChannelIdByMenuId(String id) throws Exception {
		return selectOne("ChannelDAO.getChannelIdByMenuId", id);
	}
	
	@Override
	public String getChannelIdByPostId(String id) throws Exception {
		return selectOne("ChannelDAO.getChannelIdByPostId", id);
	}
	
}
