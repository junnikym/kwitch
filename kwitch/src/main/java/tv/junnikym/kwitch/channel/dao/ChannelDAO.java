package tv.junnikym.kwitch.channel.dao;

import java.util.List;

import tv.junnikym.kwitch.channel.vo.ChannelRoleVO;
import tv.junnikym.kwitch.channel.vo.ChannelVO;
import tv.junnikym.kwitch.channel.vo.SubscribeVO;

public interface ChannelDAO {
	
	String regist(ChannelVO vo) throws Exception;
	
	List<ChannelVO> getNewChannel() throws Exception;
	
	
	
	String setDefaultRole(String id) throws Exception;
	
	void giveRole(ChannelRoleVO vo) throws Exception;
	
	ChannelRoleVO getRole(ChannelRoleVO vo) throws Exception;
	
	String getDefaultRole(String channelId) throws Exception;
	
	
	
	int subscribe(SubscribeVO vo) throws Exception;
	
	int unsubscribe(SubscribeVO vo) throws Exception;
	
	Boolean isSubscribed(SubscribeVO vo) throws Exception;
	
	
	
	String getChannelIdByCommunityId(String id) throws Exception;
	
	String getChannelIdByOwnerId(String id) throws Exception;
	
	String getChannelIdByMenuId(String id) throws Exception;
	
	String getChannelIdByPostId(String id) throws Exception;
	
}