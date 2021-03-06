package tv.junnikym.kwitch.channel.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.channel.dao.ChannelDAO;
import tv.junnikym.kwitch.channel.vo.ChannelRoleVO;
import tv.junnikym.kwitch.channel.vo.ChannelRoleVO.ChannelIdBy;
import tv.junnikym.kwitch.channel.vo.ChannelVO;
import tv.junnikym.kwitch.channel.vo.SubscribeVO;
import tv.junnikym.kwitch.community.dao.CommunityDAO;
import tv.junnikym.kwitch.community.service.CommunityService;
import tv.junnikym.kwitch.notification.service.NotificationService;

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
		this.grantRole (
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
	public void grantRole(ChannelRoleVO vo) throws Exception {
		ChannelRoleVO isExist = channelDAO.getRole(vo);

		if(isExist == null) {
			channelDAO.grantRole(vo);
		}
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
	
	
	
	/**
	 * Subscribe
	 */

	@Override
	public int subscribe(SubscribeVO vo) throws Exception {
		
		if(!channelDAO.isSubscribed(vo)) { 
			channelDAO.subscribe(vo);
			
			String roleId = channelDAO.getDefaultRole(vo.getChannelId());
			
			this.grantRole(
				ChannelRoleVO.builder()
					.memberId(vo.getSubscriberId())
					.roleId(roleId)
					.channelId(vo.getChannelId())
					.build()
			);
			
			return 1;
		}
		
		return -1;
	}

	@Override
	public int unsubscribe(SubscribeVO vo) throws Exception {
		return channelDAO.unsubscribe(vo)
				+ channelDAO.revokeRole(
					ChannelRoleVO.builder()
						.memberId(vo.getSubscriberId())
						.channelId(vo.getChannelId())
						.build()
				);
	}

	@Override
	public Boolean isSubscribed(SubscribeVO vo) throws Exception {
		return channelDAO.isSubscribed(vo);
	}
	
	@Override
	public Integer nSubscribe(String channelId) throws Exception {
		return channelDAO.nSubscribe(channelId);
	}

	@Override
	public List<ChannelVO> getSubscribeChannelList (String subscriberId) throws Exception {
		return channelDAO.getSubscribeChannelList(subscriberId);
	}


	/**
	 * etc
	 */
	
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

			case CHANNEL_ID_TYPE_TARGET_ID:
			case CHANNEL_ID_TYPE_POST_ID:
				return channelDAO.getChannelIdByPostId(id);
				
			default:
				break;
		}
		
		return null;
	}

}
