package tv.junnikym.kwitch.channel.service;

import java.util.List;

import tv.junnikym.kwitch.channel.vo.ChannelRoleVO;
import tv.junnikym.kwitch.channel.vo.ChannelVO;
import tv.junnikym.kwitch.community.vo.CommunityMenuVO;

public interface ChannelService {

	void regist(ChannelVO vo) throws Exception;
	
	ChannelRoleVO getRole(ChannelRoleVO vo) throws Exception;
	
}
