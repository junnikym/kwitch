package tv.junnikym.kwitch.channel.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.channel.dao.ChannelDAO;
import tv.junnikym.kwitch.channel.vo.ChannelRoleVO;
import tv.junnikym.kwitch.channel.vo.ChannelRoleVO.ChannelIdBy;
import tv.junnikym.kwitch.channel.vo.ChannelVO;
import tv.junnikym.kwitch.community.dao.CommunityDAO;
import tv.junnikym.kwitch.community.service.CommunityService;

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
	public List<ChannelVO> getNewChannel() throws Exception {
		return channelDAO.getNewChannel();
	}
	
	
	

	@Override
	public ChannelRoleVO getRole(ChannelRoleVO vo) throws Exception {
		if(vo.getChannelIdBy() != null) {
			String channelId = getChannelId(vo.getTargetId(), vo.getChannelIdBy());
			vo.setChannelId(channelId);
			
			System.out.println(vo.toString());
		}
		
		return channelDAO.getRole(vo);
	}
	
	
	
	private String getChannelId(
			String id, 
			ChannelIdBy idBy 
	) throws Exception {
		
		switch(idBy) {
			case CHANNEL_ID_TYPE_CHANNEL_ID:
				return id;
		
			case CHANNEL_ID_TYPE_COMMUNITY_ID:
				return channelDAO.getChannelIdByCommunityId(id);
				
			case CHANNEL_ID_TYPE_OWNER_ID:
				return channelDAO.getChannelIdByOwnerId(id);
				
			case CHANNEL_ID_TYPE_MENU_ID:
				return channelDAO.getChannelIdByMenuId(id);
				
			case CHANNEL_ID_TYPE_POST_ID:
				return channelDAO.getChannelIdByPostId(id);
				
			default:
				break;
		}
		
		return null;
	}

}
