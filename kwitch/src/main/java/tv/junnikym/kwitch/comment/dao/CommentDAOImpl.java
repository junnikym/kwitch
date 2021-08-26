package tv.junnikym.kwitch.comment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tv.junnikym.kwitch.comment.vo.CommentVO;
import tv.junnikym.kwitch.util.AbstractMapper;

@Repository("CommentDAO")
public class CommentDAOImpl extends AbstractMapper implements CommentDAO{

	@Override
	public int registComment(CommentVO vo) throws Exception {
		return insert("CommentDAO.regist", vo);
	}

	@Override
	public List<CommentVO> getComment(CommentVO vo) throws Exception {
		return selectList("CommentDAO.getComment", vo);
	}

	@Override
	public int updateComment(CommentVO vo) throws Exception {
		return update("CommentDAO.updateComment", vo);
	}

	@Override
	public int deleteComment(String commentId) throws Exception {
		return delete("CommentDAO.updateComment", commentId);
	}
	
}
