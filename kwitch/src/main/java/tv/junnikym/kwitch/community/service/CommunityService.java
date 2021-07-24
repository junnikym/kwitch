package tv.junnikym.kwitch.community.service;

import java.util.List;
import java.util.Map;

import tv.junnikym.kwitch.community.vo.CommunityMenuVO;
import tv.junnikym.kwitch.community.vo.CommunityPostVO;
import tv.junnikym.kwitch.community.vo.CommunityVO;


public interface CommunityService {


	// < Community Menu >

	List<CommunityMenuVO> getMenuList(String communityId) throws Exception;

	void setDefaultMenu(String communityId) throws Exception;


	// < Community Post >

	void registPost(CommunityPostVO vo) throws Exception;

	List<CommunityPostVO> getPostList(String menuId) throws Exception;

	CommunityPostVO getPost(String postId) throws Exception;

	void setPost(CommunityPostVO vo) throws Exception;

	void deletePost(String PostId) throws Exception;


	// < Community Home >

	List<CommunityPostVO> getHomeContent(String communityId) throws Exception;
	
}
