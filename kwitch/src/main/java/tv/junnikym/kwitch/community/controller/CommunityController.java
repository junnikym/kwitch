package tv.junnikym.kwitch.community.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.junnikym.kwitch.channel.vo.ChannelRoleVO.ChannelRoleFlag;
import tv.junnikym.kwitch.community.service.CommunityService;
import tv.junnikym.kwitch.util.auth.ChannelRoleValidation;
import tv.junnikym.kwitch.util.auth.ChannelRoleValidation.ChannelIdType;

@Controller
public class CommunityController {
	
//	@Resource(name="ChannelRoleValidation")
//	private ChannelRoleValidation roleValication;
//	
//	@Resource(name="CommunityService")
//	private CommunityService communityService;
//	
//	
//	@RequestMapping(value = {
//			"/community/{id}", 
//			"/community/post/{id}", 
//			"/community/menu/{id}"
//	}, method = RequestMethod.GET)
//	public String communityRead(
//			@PathVariable("id") String id,
//			HttpServletRequest request, 
//			HttpServletResponse response, 
//			HttpSession session
//	) throws Exception {
//		if(request.isRequestedSessionIdValid()) {
//			String member_id = (String) session.getAttribute("member_id");
//		}
//		
//		boolean isValid = roleValication.validation(
//				id, 
//				ChannelIdType.CHANNEL_ID_TYPE_COMMUNITY_ID, 
//				ChannelRoleFlag.CH_ROLE_READ, 
//				session
//		);
//		if(!isValid) {
//			response.sendError(401, "Unauthorized");
//			return null;
//		}
//		
//		return "index";
//	}
//	
}
