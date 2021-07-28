package tv.junnikym.kwitch.channel.dao;

import tv.junnikym.kwitch.channel.vo.ChannelRoleVO;
import tv.junnikym.kwitch.channel.vo.ChannelVO;

public interface ChannelDAO {
	
	String regist(ChannelVO vo) throws Exception;
	
	String setDefaultRole(String id) throws Exception;
	
	void giveRole(ChannelRoleVO vo) throws Exception;
	
	ChannelRoleVO getRole(ChannelRoleVO vo) throws Exception;
	
	
	
	String getChannelIdByCommunityId(String id) throws Exception;
	
	String getChannelIdByOwnerId(String id) throws Exception;
	
	String getChannelIdByMenuId(String id) throws Exception;
	
	String getChannelIdByPostId(String id) throws Exception;
	
}