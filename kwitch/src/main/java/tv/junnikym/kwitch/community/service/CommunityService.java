package tv.junnikym.kwitch.community.service;

import java.util.List;
import java.util.Map;

import tv.junnikym.kwitch.community.vo.CommunityMenuVO;
import tv.junnikym.kwitch.community.vo.CommunityPostVO;
import tv.junnikym.kwitch.community.vo.CommunityVO;


public interface CommunityService {

	List<CommunityMenuVO> getMenuList(String communityId) throws Exception;
	
	List<CommunityPostVO> getPostList(String menuId) throws Exception;
	
	void registPost(CommunityPostVO vo) throws Exception;
	
	CommunityPostVO getPost(String postId) throws Exception;
	
	List<CommunityPostVO> getHome(String communityId) throws Exception;
	
	void setPost(CommunityPostVO vo) throws Exception;
	
	void deletePost(String PostId) throws Exception;
	
}
