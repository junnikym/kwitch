package tv.junnikym.kwitch.comment.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import tv.junnikym.kwitch.channel.vo.ChannelRoleVO.ChannelIdBy;
import tv.junnikym.kwitch.channel.vo.ChannelRoleVO.ChannelRoleFlag;
import tv.junnikym.kwitch.comment.service.CommentService;
import tv.junnikym.kwitch.comment.vo.CommentVO;
import tv.junnikym.kwitch.liked.vo.LikedVO;
import tv.junnikym.kwitch.util.auth.ChannelRoleValid;
import tv.junnikym.kwitch.util.auth.ChannelRoleValid.IdGetMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class CommentAPIController {
	
	@Resource(name="CommentService")
	private CommentService commentService;
	
	

	@ResponseBody
	@RequestMapping(value = "/community/comment", method = RequestMethod.POST)
	@ChannelRoleValid(
			role 		= ChannelRoleFlag.CH_ROLE_COMMNET_WRITE,
			idBy 		= ChannelIdBy.CHANNEL_ID_TYPE_TARGET_ID,
			idGetMethod	= IdGetMethod.VO
	)
	public CommentVO registCommunityComment (
		@RequestBody CommentVO vo,
		HttpServletResponse response,
		HttpSession session
	) throws Exception {

		String memberId = (String) session.getAttribute("member_id");

		if(memberId == null || memberId == "") {
			response.setStatus(401);
			return null;
		}

		vo.setWriterId(memberId);
		vo.setUsage(CommentVO.CommentUsage.COMMENT_USAGE_POST);

		commentService.registComment(vo);

		return vo;
	}

	@ResponseBody
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public CommentVO registComment (
			@RequestBody CommentVO vo,
			HttpServletResponse response,
			HttpSession session
	) throws Exception {
		if(vo.getUsage().equals( CommentVO.CommentUsage.COMMENT_USAGE_POST )) {
			response.setStatus(400);
			return null;
		}

		String memberId = (String) session.getAttribute("member_id");
		if(memberId == null || memberId == "") {
			response.setStatus(401);
			return null;
		}
		vo.setWriterId(memberId);

		commentService.registComment(vo);

		return vo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/comment/list", method = RequestMethod.POST)
	public List<CommentVO> getComment (
		@RequestBody CommentVO vo,
		HttpServletResponse response, 
		HttpSession session
	) throws Exception {

		return commentService.getComment(vo);
	}
	
	@ResponseBody
	@RequestMapping(value = "/comment", method = RequestMethod.PUT)
	public void updateComment (
		@RequestBody CommentVO vo
	) throws Exception {

	}
	
	@ResponseBody
	@RequestMapping(value = "/comment/{commentId}", method = RequestMethod.DELETE)
	public void deleteComment (
		@PathVariable("commentId") String commentId,
		HttpServletResponse response,
		HttpSession session
	) throws Exception {
		String memberId = (String) session.getAttribute("member_id");
		if(memberId == null || memberId == "") {
			response.setStatus(401);
			return;
		}

		commentService.deleteComment(commentId);
	}
	
}
