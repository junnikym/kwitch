package tv.junnikym.kwitch.comment.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tv.junnikym.kwitch.channel.vo.ChannelRoleVO.ChannelIdBy;
import tv.junnikym.kwitch.channel.vo.ChannelRoleVO.ChannelRoleFlag;
import tv.junnikym.kwitch.comment.service.CommentService;
import tv.junnikym.kwitch.liked.vo.LikedVO;
import tv.junnikym.kwitch.util.auth.ChannelRoleValid;
import tv.junnikym.kwitch.util.auth.ChannelRoleValid.IdGetMethod;

@Controller
@RequestMapping(value = "/api/comment")
public class CommentAPIController {
	
	@Resource(name="CommentService")
	private CommentService commentService;
	
	

	@ResponseBody
	@RequestMapping(value = "/community", method = RequestMethod.POST)
	@ChannelRoleValid(
			role 		= ChannelRoleFlag.CH_ROLE_COMMNET_WRITE,
			idBy 		= ChannelIdBy.CHANNEL_ID_TYPE_TARGET_ID,
			idGetMethod	= IdGetMethod.FULLNAME
	)
	public void registCommunityComment (
		@RequestBody LikedVO vo,
		HttpServletRequest request, 
		HttpServletResponse response, 
		HttpSession session
	) throws Exception {
		
		
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public void getComment (
		@RequestBody LikedVO vo,
		HttpServletRequest request, 
		HttpServletResponse response, 
		HttpSession session
	) throws Exception {
		
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public void updateComment (
		@RequestBody LikedVO vo,
		HttpServletRequest request, 
		HttpServletResponse response, 
		HttpSession session
	) throws Exception {
		
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteComment (
		@RequestBody LikedVO vo,
		HttpServletRequest request, 
		HttpServletResponse response, 
		HttpSession session
	) throws Exception {
		
	}
	
}
