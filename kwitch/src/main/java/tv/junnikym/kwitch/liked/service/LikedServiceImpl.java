package tv.junnikym.kwitch.liked.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.liked.dao.LikedDAO;
import tv.junnikym.kwitch.liked.vo.LikedVO;

@Service("LikedService")
public class LikedServiceImpl implements LikedService {

	@Resource(name="LikedDAO")
	private LikedDAO likedDAO;
	
	
	
	@Override
	public int regist(LikedVO vo) throws Exception {
		Boolean like = likedDAO.isLiked(vo);
		if(like == null)
			return likedDAO.regist(vo);
		else {
			if(vo.getIsUnliked() == like)	// both same value => different
				return likedDAO.update(vo);
		}
		
		return 0;
	}

	@Override
	public Boolean isLiked(LikedVO vo) throws Exception {
		return likedDAO.isLiked(vo);
	}

	@Override
	public Integer nLiked(String targetId) throws Exception {
		return likedDAO.nLiked(targetId);
	}

	@Override
	public Integer nUnliked(String targetId) throws Exception {
		return likedDAO.nUnliked(targetId);
	}
	
	public Map<String, String> countLike(String targetId) throws Exception {
		Map<String, String> result = new HashMap<>();
		
		Integer countResult = likedDAO.nLiked(targetId);
		if(countResult == null)
			countResult = 0;
		
		result.put("like", countResult.toString());
		
		countResult = likedDAO.nUnliked(targetId);
		if(countResult == null)
			countResult = 0;
		
		result.put("unlike", countResult.toString());
		
		return result;
	}

	@Override
	public int deleteLike(LikedVO vo) throws Exception {
		return likedDAO.deleteLike(vo);
	}
	
}
