package tv.junnikym.kwitch.comment.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.comment.dao.CommentDAO;
import tv.junnikym.kwitch.comment.vo.CommentVO;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {
	
	@Resource(name="CommentDAO")
	CommentDAO commentDAO;

	@Override
	public int registComment(CommentVO vo) throws Exception {
		return commentDAO.registComment(vo);
	}

	@Override
	public List<CommentVO> getComment(CommentVO vo) throws Exception {
		return commentDAO.getComment(vo);
	}

	@Override
	public int updateComment(CommentVO vo) throws Exception {
		return commentDAO.updateComment(vo);
	}

	@Override
	public int deleteComment(String commentId) throws Exception {
		return commentDAO.deleteComment(commentId);
	}
	
}
