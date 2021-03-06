package tv.junnikym.kwitch.community.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.community.dao.CommunityDAO;
import tv.junnikym.kwitch.community.vo.CommunityMenuVO;
import tv.junnikym.kwitch.community.vo.CommunityPostHistoryVO;
import tv.junnikym.kwitch.community.vo.CommunityPostVO;
import tv.junnikym.kwitch.community.vo.CommunityVO;

@Service("CommunityService")
public class CommunityServiceImpl implements CommunityService {
	
	@Resource(name = "CommunityDAO")
	private CommunityDAO communityDAO;

	private static final List<CommunityMenuVO> defaultMenu = new ArrayList<CommunityMenuVO>( Arrays.asList(
		CommunityMenuVO.builder() .title("자유게시판")	.homePriority(2) 										.build(),
		CommunityMenuVO.builder() .title("공지사항")	.homePriority(1) .nHomeThumb(3)	.isAllowToWrite(false)	.build(),
		CommunityMenuVO.builder() .title("기타")		.homePriority(3) 										.build()
	));

	/**
	 *	Community Menu �뿰愿� 硫붿냼�뱶
	 * --------------------------------------------------
	 */
	
	@Override
	public CommunityMenuVO getMenu(String menuId) throws Exception {
		return communityDAO.getMenu(menuId);
	}
	
	@Override
	public List<CommunityMenuVO> getMenuList(String communityId) throws Exception {
		return communityDAO.getMenuList(communityId);
	}

	@Override
	public void setDefaultMenu(String communityId) throws Exception {

		// @TODO : if exist any menu in community then delete whole menu
		List<CommunityMenuVO> newDefaultMenu = new ArrayList( this.defaultMenu );

		for (CommunityMenuVO vo : newDefaultMenu) {
			vo.setCommunityId(communityId);
		}

		communityDAO.registMenuList(newDefaultMenu);
		communityDAO.registHomeList(newDefaultMenu);
	}

	/**
	 *	Community Post �뿰愿� 硫붿냼�뱶
	 * --------------------------------------------------
	 */

	@Override
	public List<CommunityPostVO> getPostList(String menuId) throws Exception {
		return communityDAO.getPostList(menuId);
	}
	
	@Override
	public String registPost(CommunityPostVO vo) throws Exception {
		return communityDAO.registPost(vo);
	}
	
	@Override
	public List<CommunityPostVO> getPostListBySearch(CommunityPostVO vo) throws Exception {
		return communityDAO.getPostListBySearch(vo);
	}
	
	@Override
	public CommunityPostVO getPost(String postId, boolean incView) throws Exception {
		if(incView)
			communityDAO.setPostView(postId);
		
		return communityDAO.getPost(postId);
	}

	@Override
	public void setPost(CommunityPostVO vo) throws Exception {
		communityDAO.setPost(vo);
	}

	@Override
	public void deletePost(String postId) throws Exception {
		communityDAO.deletePost(postId);
	}

	@Override
	public void blockPost(String postId) throws Exception {
		communityDAO.blockPost(postId);
	}

	/**
	 *	Community Home �뿰愿� 硫붿냼�뱶
	 * --------------------------------------------------
	 */

	@Override
	public List<CommunityPostVO> getHomeContent(String communityId) throws Exception {
		return communityDAO.getHomeContent(communityId);
	}

	@Override
	public List<CommunityMenuVO> getHomeMenu(String communityId) throws Exception {
		return communityDAO.getHomeMenu(communityId);
	}

	@Override
	public void registPostHistory(CommunityPostHistoryVO vo) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
