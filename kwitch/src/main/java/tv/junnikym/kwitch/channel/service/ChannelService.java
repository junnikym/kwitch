package tv.junnikym.kwitch.channel.service;

import java.util.List;

import tv.junnikym.kwitch.channel.vo.ChannelRoleVO;
import tv.junnikym.kwitch.channel.vo.ChannelVO;
import tv.junnikym.kwitch.channel.vo.SubscribeVO;
import tv.junnikym.kwitch.community.vo.CommunityMenuVO;

public interface ChannelService {

	void regist(ChannelVO vo) throws Exception;
	
	ChannelRoleVO getRole(ChannelRoleVO vo) throws Exception;
	
	List<ChannelVO> getNewChannel() throws Exception;

	void grantRole(ChannelRoleVO vo) throws Exception;

	/* Subscribe */
	
	int subscribe(SubscribeVO vo) throws Exception;
	
	int unsubscribe(SubscribeVO vo) throws Exception;
	
	Boolean isSubscribed(SubscribeVO vo) throws Exception;
	
	Integer nSubscribe(String channelId) throws Exception;
}
