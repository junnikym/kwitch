package tv.junnikym.kwitch.liked.service;

import java.util.Map;

import tv.junnikym.kwitch.liked.vo.LikedVO;

public interface LikedService {

	int regist(LikedVO vo) throws Exception;
	
	Boolean isLiked(LikedVO vo) throws Exception;
	
	Integer nLiked(String targetId) throws Exception;
	
	Integer nUnliked(String targetId) throws Exception;
	
	Map<String, String> countLike(String targetId) throws Exception;
	
	int deleteLike(LikedVO vo) throws Exception;
	
}
