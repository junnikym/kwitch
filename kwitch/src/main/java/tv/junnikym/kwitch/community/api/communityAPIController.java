package tv.junnikym.kwitch.community.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tv.junnikym.kwitch.community.service.CommunityService;
import tv.junnikym.kwitch.community.vo.CommunityPostVO;
import tv.junnikym.kwitch.member.vo.LoginVO;

@Controller
@RequestMapping(value = "/api")
public class communityAPIController {

	@Resource(name="CommunityService")
	private CommunityService communityService;
	
	@ResponseBody
	@RequestMapping(value = "/community/{communityId}", method = RequestMethod.GET)
	public List<CommunityPostVO> getCommunity (
			@PathVariable("communityId") String id,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		System.out.println(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CommunityPostVO> result = communityService.getHomeContent(id);
		System.out.println("is exception here");
		
		map.put("data", result);
		
		return result;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/community/post/{postId}", method = RequestMethod.GET)
	public CommunityPostVO getPost (
			@PathVariable("postId") String id,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		
		return communityService.getPost(id);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/community/post", method = RequestMethod.POST)
	public void registPost (
			@RequestBody() CommunityPostVO vo,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		
		communityService.registPost(vo);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/community/post/{postId}", method = RequestMethod.PUT)
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
	public void deletePostId (
			@PathVariable("postId") String id,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		
		communityService.deletePost(id);
	}
	
	
	
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
	
}
