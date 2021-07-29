package tv.junnikym.kwitch.community.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.junnikym.kwitch.channel.vo.ChannelRoleVO.ChannelRoleFlag;
import tv.junnikym.kwitch.community.service.CommunityService;
import tv.junnikym.kwitch.util.auth.ChannelRoleValid;
import tv.junnikym.kwitch.util.auth.ChannelRoleValidInterceptor.ChannelIdType;

@Controller
public class CommunityController {
	
	@Resource(name="CommunityService")
	private CommunityService communityService;
	
	
	
	/**
	 * [ CREATE ]
	 */
	
	@RequestMapping(value = "/c/p/{id}/upload", method = RequestMethod.GET)
	@ChannelRoleValid(
			role 	= ChannelRoleFlag.CH_ROLE_WRITE,
			idType 	= ChannelIdType.CHANNEL_ID_TYPE_CHANNEL_ID
	)
	public String communityCreate(
			HttpServletRequest request, 
			HttpServletResponse response
	) throws Exception {
		
		return "index";
	}
	
	/**
	 * [ READ ]
	 */
	
	@RequestMapping(value = {
			"/c/{id}", 
			"/p/{id}", 
			"/c/m/{id}"
	}, method = RequestMethod.GET)	
	public String communityRead(
			HttpServletRequest request, 
			HttpServletResponse response
	) throws Exception {
		
		return "index";
	}
	
	/**
	 * [ UPDATE ]
	 */
	
	@RequestMapping(value = "/c/p/{id}/edit", method = RequestMethod.GET)	
	@ChannelRoleValid(
			role 	= ChannelRoleFlag.CH_ROLE_UPDATE,
			idType 	= ChannelIdType.CHANNEL_ID_TYPE_CHANNEL_ID
	)
	public String communityUpload(
			HttpServletRequest request, 
			HttpServletResponse response
	) throws Exception {
		
		return "index";
	}
	
}
