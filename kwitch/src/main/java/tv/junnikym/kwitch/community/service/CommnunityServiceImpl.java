package tv.junnikym.kwitch.community.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.community.dao.CommunityDAO;
import tv.junnikym.kwitch.community.vo.CommunityMenuVO;
import tv.junnikym.kwitch.community.vo.CommunityPostVO;
import tv.junnikym.kwitch.community.vo.CommunityVO;

@Service("CommnunityService")
public class CommnunityServiceImpl implements CommunityService {
	
	@Resource(name = "CommunityDAO")
	private CommunityDAO communityDAO;

	@Override
	public List<CommunityMenuVO> getMenuList(String communityId) throws Exception {
		return communityDAO.getMenuList(communityId);
	}

	@Override
	public List<CommunityPostVO> getPostList(String menuId) throws Exception {
		return communityDAO.getPostList(menuId);
	}
	
	@Override
	public void registPost(CommunityPostVO vo) throws Exception {
		communityDAO.registPost(vo);
	}
	
	@Override
	public CommunityPostVO getPost(String postId) throws Exception {
		return communityDAO.getPost(postId);
	}

	@Override
	public List<CommunityPostVO> getHome(String communityId) throws Exception {
		return communityDAO.getHome(communityId);
	}

	@Override
	public void setPost(CommunityPostVO vo) throws Exception {
		communityDAO.setPost(vo);
	}

	@Override
	public void deletePost(String PostId) throws Exception {
		communityDAO.deletePost(PostId);
	}
	
}
