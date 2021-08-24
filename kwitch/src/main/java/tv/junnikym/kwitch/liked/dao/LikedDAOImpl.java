package tv.junnikym.kwitch.liked.dao;

import org.springframework.stereotype.Repository;

import tv.junnikym.kwitch.liked.vo.LikedVO;
import tv.junnikym.kwitch.util.AbstractMapper;

@Repository("LikedDAO")
public class LikedDAOImpl extends AbstractMapper implements LikedDAO {

	@Override
	public int regist(LikedVO vo) throws Exception {
		return insert("LikedDAO.regist", vo);
	}

	@Override
	public Boolean isLiked(LikedVO vo) throws Exception {
		return selectOne("LikedDAO.isLiked", vo);
	}

	@Override
	public Integer nLiked(String targetId) throws Exception {
		return selectOne("LikedDAO.nLiked", targetId);
	}

	@Override
	public Integer nUnliked(String targetId) throws Exception {
		return selectOne("LikedDAO.nUnliked", targetId);
	}
	
	@Override
	public int update(LikedVO vo) throws Exception {
		return update("LikedDAO.updateLike", vo);
	}

	@Override
	public int deleteLike(LikedVO vo) throws Exception {
		return delete("LikedDAO.deleteLike", vo);
	}

}
