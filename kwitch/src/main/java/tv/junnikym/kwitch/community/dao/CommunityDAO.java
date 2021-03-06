package tv.junnikym.kwitch.community.dao;

import java.util.List;

import tv.junnikym.kwitch.community.vo.CommunityMenuVO;
import tv.junnikym.kwitch.community.vo.CommunityPostHistoryVO;
import tv.junnikym.kwitch.community.vo.CommunityPostVO;

public interface CommunityDAO {


	// < Community Menu >
	
	String regist(String id) throws Exception;

	void registMenu(CommunityMenuVO vo) throws Exception;

	void registMenuList(List<CommunityMenuVO> vo) throws Exception;
	
	CommunityMenuVO getMenu(String id) throws Exception;
	
	List<CommunityMenuVO> getMenuList(String communityId) throws Exception;


	// < Community Post >

	String registPost(CommunityPostVO vo) throws Exception;
	
	void registPostHistory(CommunityPostHistoryVO vo) throws Exception;
	
	List<CommunityPostVO> getPostList(String menuId) throws Exception;
	
	List<CommunityPostVO> getPostListBySearch(CommunityPostVO vo) throws Exception;
	
	CommunityPostVO getPost(String postId) throws Exception;

	void setPostView(String PostId) throws Exception;
	
	void setPost(CommunityPostVO vo) throws Exception;

	void deletePost(String postId) throws Exception;

	void blockPost(String postId) throws Exception;
	

	// < Community Home >

	void registHome(CommunityMenuVO vo) throws Exception;

	void registHomeList(List<CommunityMenuVO> vo) throws Exception;

	List<CommunityPostVO> getHomeContent(String communityId) throws Exception;

	List<CommunityMenuVO> getHomeMenu(String communityId) throws Exception;
}
