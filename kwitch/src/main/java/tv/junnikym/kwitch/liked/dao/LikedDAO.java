package tv.junnikym.kwitch.liked.dao;

import tv.junnikym.kwitch.liked.vo.LikedVO;

public interface LikedDAO {

	int regist(LikedVO vo) throws Exception;
	
	Boolean isLiked(LikedVO vo) throws Exception;
	
	Integer nLiked(String targetId) throws Exception;
	
	Integer nUnliked(String targetId) throws Exception;
	
	int update(LikedVO vo) throws Exception;
	
	int deleteLike(LikedVO vo) throws Exception;
	
}
