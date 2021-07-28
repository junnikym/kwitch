package tv.junnikym.kwitch.channel.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.channel.dao.ChannelDAO;
import tv.junnikym.kwitch.channel.vo.ChannelRoleVO;
import tv.junnikym.kwitch.channel.vo.ChannelVO;
import tv.junnikym.kwitch.community.dao.CommunityDAO;
import tv.junnikym.kwitch.community.service.CommunityService;
import tv.junnikym.kwitch.community.vo.CommunityMenuVO;

@Service("ChannelService")
public class ChannelServiceImpl implements ChannelService {

	@Resource(name="ChannelDAO")
	ChannelDAO channelDAO;
	
	@Resource(name="CommunityDAO")
	CommunityDAO communityDAO;
	
	@Resource(name="CommunityService")
	CommunityService communityService;
	
	@Override
	public void regist(ChannelVO vo) throws Exception {
		String channelId 	= channelDAO.regist(vo);
		
		String communityId 	= communityDAO.regist(channelId);
		communityService.setDefaultMenu(communityId);
		
		String roleId 		= channelDAO.setDefaultRole(channelId);
		channelDAO.giveRole (
				ChannelRoleVO.builder()
						.memberId(vo.getOwnerId())
						.channelId(channelId)
						.roleId(roleId)
						.build()
		);
		
	}

	@Override
	public ChannelRoleVO getRole(ChannelRoleVO vo) throws Exception {
		return channelDAO.getRole(vo);
	}

}
