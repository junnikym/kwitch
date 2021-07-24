package tv.junnikym.kwitch.community.dao;

import java.util.List;

import tv.junnikym.kwitch.community.vo.CommunityMenuVO;
import tv.junnikym.kwitch.community.vo.CommunityPostVO;

public interface CommunityDAO {


	// < Community Menu >
	
	String regist(String id) throws Exception;

	void registMenu(CommunityMenuVO vo) throws Exception;

	void registMenuList(List<CommunityMenuVO> vo) throws Exception;
	
	List<CommunityMenuVO> getMenuList(String communityId) throws Exception;


	// < Community Post >

	void registPost(CommunityPostVO vo) throws Exception;
	
	List<CommunityPostVO> getPostList(String menuId) throws Exception;
	
	CommunityPostVO getPost(String postId) throws Exception;

	void setPost(CommunityPostVO vo) throws Exception;

	void deletePost(String PostId) throws Exception;


	// < Community Home >

	void registHome(CommunityMenuVO vo) throws Exception;

	void registHomeList(List<CommunityMenuVO> vo) throws Exception;

	List<CommunityPostVO> getHomeContent(String communityId) throws Exception;
}
