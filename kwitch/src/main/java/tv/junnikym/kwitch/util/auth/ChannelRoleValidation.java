package tv.junnikym.kwitch.util.auth;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.channel.dao.ChannelDAO;
import tv.junnikym.kwitch.channel.vo.ChannelRoleVO;

@Service("ChannelRoleValidation")
public class ChannelRoleValidation {
	
	@Resource(name="ChannelDAO")
	ChannelDAO channelDAO;
	
	public boolean validation(
			String id, 
			ChannelIdType idType, 
			int checkFlag,
			HttpSession session
	) throws Exception {
		
//		String memberId 	= (String)session.getAttribute("member_id");
//		
//		if(memberId == null || memberId == "")
//			return false;
//		
//		String channelId 	= getChannelId(id, idType);
//		ChannelRoleVO vo = ChannelRoleVO.builder()
//									.memberId(memberId)
//									.channelId(channelId)
//									.build();
//		
//		vo = channelDAO.getRole(vo);
//		
//		int memberRoleFlag = vo.getRoleFlag();
//		
//		if((checkFlag & memberRoleFlag) > 0)
//			return true;
		
		return true;
	}
	
	private String getChannelId(
			String id, 
			ChannelIdType idType 
	) throws Exception {
		
		switch(idType) {
		
			case CHANNEL_ID_TYPE_COMMUNITY_ID:
				return channelDAO.getChannelIdByCommunityId(id);
				
			case CHANNEL_ID_TYPE_MENU_ID:
				return channelDAO.getChannelIdByMenuId(id);
				
			case CHANNEL_ID_TYPE_POST_ID:
				return channelDAO.getChannelIdByPostId(id);
				
			default:
				break;
		}
		
		return null;
	}
	
	public enum ChannelIdType {
		CHANNEL_ID_TYPE_COMMUNITY_ID,
		CHANNEL_ID_TYPE_MENU_ID,
		CHANNEL_ID_TYPE_POST_ID,
	}
	
}
