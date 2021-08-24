package tv.junnikym.kwitch.liked.api;

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

import tv.junnikym.kwitch.liked.service.LikedService;
import tv.junnikym.kwitch.liked.vo.LikedVO;

@Controller
@RequestMapping(value = "/api/like")
public class LikedAPIController {

	@Resource(name="LikedService")
	private LikedService likedService;
	
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public void regist (
		@RequestBody LikedVO vo,
		HttpServletRequest request, 
		HttpServletResponse response, 
		HttpSession session
	) throws Exception {
		
		vo.setGiverId((String) session.getAttribute("member_id"));
		
		int result = likedService.regist(vo);
		if(result <= 0)
			response.setStatus(400);
	}
	
	@ResponseBody
	@RequestMapping(value = "/is", method = RequestMethod.POST)
	public Boolean isLiked (
		@RequestBody LikedVO vo,
		HttpServletRequest request, 
		HttpServletResponse response, 
		HttpSession session
	) throws Exception {
		
		vo.setGiverId((String) session.getAttribute("member_id"));
		
		Boolean result = likedService.isLiked(vo);
		if(result == null)
			response.setStatus(404);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/count/{targetId}", method = RequestMethod.GET)
	public Map<String, String> nLiked (
		@PathVariable("targetId") String targetId, 
		HttpServletRequest request, 
		HttpServletResponse response, 
		HttpSession session
	) throws Exception {
		
		return likedService.countLike(targetId);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public void deleteLike (
		@RequestBody LikedVO vo,
		HttpServletRequest request, 
		HttpServletResponse response, 
		HttpSession session
	) throws Exception {
		
		vo.setGiverId((String) session.getAttribute("member_id"));
		
		int result = likedService.deleteLike(vo);
		if(result <= 0)
			response.setStatus(400);
	}
	
}
