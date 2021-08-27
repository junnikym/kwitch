package tv.junnikym.kwitch.community.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tv.junnikym.kwitch.channel.service.ChannelService;
import tv.junnikym.kwitch.channel.vo.ChannelRoleVO;
import tv.junnikym.kwitch.channel.vo.ChannelRoleVO.ChannelIdBy;
import tv.junnikym.kwitch.channel.vo.ChannelRoleVO.ChannelRoleFlag;
import tv.junnikym.kwitch.community.service.CommunityService;
import tv.junnikym.kwitch.community.vo.CommunityMenuVO;
import tv.junnikym.kwitch.community.vo.CommunityPostVO;
import tv.junnikym.kwitch.util.auth.ChannelRoleValid;
import tv.junnikym.kwitch.util.auth.ChannelRoleValid.IdGetMethod;

@Controller
@RequestMapping(value = "/api")
public class CommunityAPIController {

	@Resource(name="CommunityService")
	private CommunityService communityService;
	
	@Resource(name="ChannelService")
	private ChannelService channelService;
	
	
	
	@ResponseBody
	@RequestMapping(value = "/community/{communityId}", method = RequestMethod.GET)
	public Map<String, Object> getCommunity (
			@PathVariable("communityId") String id,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("post", communityService.getHomeContent(id));
		map.put("menu", communityService.getHomeMenu(id));
		
		return map;
	}
	
	
	
	/**
	 * Post Related
	 * --------------------------------------------------
	 */
	
	@ResponseBody
	@RequestMapping(value = "/community/post/", method = RequestMethod.POST)
	@ChannelRoleValid(
			role 		= ChannelRoleFlag.CH_ROLE_WRITE,
			idBy 	= ChannelIdBy.CHANNEL_ID_TYPE_COMMUNITY_ID,
			idGetMethod	= IdGetMethod.VO
	)
	public String registPost (
			@RequestBody() CommunityPostVO vo,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		
		vo.setWriterId((String)session.getAttribute("member_id"));
		
		return communityService.registPost(vo);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/community/post/{postId}", method = RequestMethod.GET)
	public CommunityPostVO getPost (
			@PathVariable("postId") String id,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		
		Cookie[] cookies 		= request.getCookies();
		boolean incView 		= false;
		boolean isCookieExist 	= false;
		
		for(Cookie it : cookies) {
			
			if(it.getName().equals("post_visit")) {
				
				isCookieExist = true;
				
				if( ! it.getValue().contains(id) ) {
					System.out.println("is not exist");
					
					it.setValue(it.getValue()+"_"+id);
					response.addCookie(it);
					incView = true;
				}
				
				break;
			}
		}
		
		CommunityPostVO result = communityService.getPost(id, incView);
		
		if(isCookieExist == false) 
			response.addCookie( new Cookie("post_visit", id) );
		
		return result;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/community/post/{postId}", method = RequestMethod.PUT)
	@ChannelRoleValid(
			role 		= ChannelRoleFlag.CH_ROLE_UPDATE,
			idBy 		= ChannelIdBy.CHANNEL_ID_TYPE_POST_ID,
			idGetMethod	= IdGetMethod.FULLNAME
	)
	public void setPost (
			@PathVariable("postId") String id,
			@RequestBody() CommunityPostVO vo,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		
		vo.setId(id);

		communityService.setPost(vo);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/community/post/{postId}", method = RequestMethod.DELETE)
	@ChannelRoleValid(
			role 		= ChannelRoleFlag.CH_ROLE_DELETE,
			idBy 	= ChannelIdBy.CHANNEL_ID_TYPE_POST_ID,
			idGetMethod	= IdGetMethod.FULLNAME
	)
	public void deletePostId (
			@PathVariable("postId") String id,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		
		communityService.deletePost(id);
	}


	@ResponseBody
	@RequestMapping(value = "/community/post/{postId}/block", method = RequestMethod.DELETE)
	@ChannelRoleValid(
			role 		= ChannelRoleFlag.CH_ROLE_DELETE_OTHERS,
			idBy 	= ChannelIdBy.CHANNEL_ID_TYPE_POST_ID,
			idGetMethod	= IdGetMethod.FULLNAME
	)
	public void blockPostId (
			@PathVariable("postId") String id,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session
	) throws Exception {

		communityService.blockPost(id);
	}

	
	
	/**
	 * Menu Related
	 * --------------------------------------------------
	 */
	
	@ResponseBody
	@RequestMapping(value = "/community/menu/{menuId}", method = RequestMethod.GET)
	public List<CommunityPostVO> getPostList (
			@PathVariable("menuId") String id,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		
		return communityService.getPostList(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/community/{communityId}/menu", method = RequestMethod.GET)
	public List<CommunityMenuVO> getMenuList (
			@PathVariable("communityId") String id,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		
		return communityService.getMenuList(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/community/menu/{menuId}/info", method = RequestMethod.GET)
	public CommunityMenuVO getMenuInfo (
			@PathVariable("menuId") String id,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		
		return communityService.getMenu(id);
	}

	
	
	/**
	 * User Auth Check
	 * --------------------------------------------------
	 */
	
	@ResponseBody
	@RequestMapping(value = "/community/auth", method = RequestMethod.POST)
	public ChannelRoleVO getCommunityAuthChack (
			@RequestBody() ChannelRoleVO vo,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		
		String memberId = (String)session.getAttribute("member_id");
		if(memberId == null || memberId == "")
			return null;
		
		vo.setMemberId(memberId);
		return channelService.getRole(vo);
	}
	
}