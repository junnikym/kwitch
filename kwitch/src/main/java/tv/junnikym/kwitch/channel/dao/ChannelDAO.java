package tv.junnikym.kwitch.channel.dao;

import tv.junnikym.kwitch.channel.vo.ChannelRoleVO;
import tv.junnikym.kwitch.channel.vo.ChannelVO;

public interface ChannelDAO {
	
	String regist(ChannelVO vo) throws Exception;
	
	String setDefaultRole(String id) throws Exception;
	
	void giveRole(ChannelRoleVO vo) throws Exception;
	
}