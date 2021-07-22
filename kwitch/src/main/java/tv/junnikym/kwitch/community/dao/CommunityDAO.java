package tv.junnikym.kwitch.community.dao;

import java.util.List;

import tv.junnikym.kwitch.community.vo.CommunityMenuVO;
import tv.junnikym.kwitch.community.vo.CommunityPostVO;

public interface CommunityDAO {
	
	String regist(String id) throws Exception;
	
	void setDefaultMenu(String communityId) throws Exception;
	
	List<CommunityMenuVO> getMenuList(String communityId) throws Exception;
	
	void registPost(CommunityPostVO vo) throws Exception;
	
	List<CommunityPostVO> getPostList(String menuId) throws Exception;
	
	CommunityPostVO getPost(String postId) throws Exception;
	
	List<CommunityPostVO> getHome(String communityId) throws Exception;
	
	void setPost(CommunityPostVO vo) throws Exception;
	
	void deletePost(String PostId) throws Exception;
}
