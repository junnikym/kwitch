package tv.junnikym.kwitch.channel.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.channel.dao.ChannelDAO;
import tv.junnikym.kwitch.channel.vo.ChannelRoleVO;
import tv.junnikym.kwitch.channel.vo.ChannelVO;
import tv.junnikym.kwitch.community.dao.CommunityDAO;

@Service("ChannelService")
public class ChannelServiceImpl implements ChannelService {

	@Resource(name="ChannelDAO")
	ChannelDAO channelDAO;
	
	@Resource(name="CommunityDAO")
	CommunityDAO communityDAO;
	
	@Override
	public void regist(ChannelVO vo) throws Exception {
		String channelId = channelDAO.regist(vo);
		
		System.out.println("ID ---- " + channelId);
		
		communityDAO.regist(channelId);
		
		String roleId = channelDAO.setDefaultRole(channelId);
		
		channelDAO.giveRole (
				ChannelRoleVO.builder()
						.memberId(vo.getOwnerId())
						.channelId(channelId)
						.roleId(roleId)
						.build()
		);
	}

}
