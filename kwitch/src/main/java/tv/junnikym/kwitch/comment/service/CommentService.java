package tv.junnikym.kwitch.comment.service;

import java.util.List;

import tv.junnikym.kwitch.comment.vo.CommentVO;

public interface CommentService {

	int registComment (CommentVO vo) throws Exception;
	
	List<CommentVO> getComment (CommentVO vo) throws Exception;
	
	int updateComment (CommentVO vo) throws Exception;
	
	int deleteComment (String commentId) throws Exception;
	
}
