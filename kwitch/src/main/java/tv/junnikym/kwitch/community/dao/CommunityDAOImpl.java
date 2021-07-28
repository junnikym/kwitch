package tv.junnikym.kwitch.community.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sun.javafx.collections.MappingChange.Map;

import tv.junnikym.kwitch.community.vo.CommunityMenuVO;
import tv.junnikym.kwitch.community.vo.CommunityPostVO;
import tv.junnikym.kwitch.util.AbstractMapper;

@Repository("CommunityDAO")
public class CommunityDAOImpl extends AbstractMapper implements CommunityDAO {

	/**
	 *	Community Menu �뿰愿� 硫붿냼�뱶
	 * --------------------------------------------------
	 */

	@Override
	public String regist(String id) throws Exception {
		String channelId = new String(id);
		
		insert("CommunityDAO.regist", channelId);
		return channelId;
	}

	@Override
	public void registMenu (CommunityMenuVO vo) throws Exception {
		insert("CommunityDAO.registMenu", vo);
	}

	@Override
	public void registMenuList(List<CommunityMenuVO> vo) throws Exception {
		insert("CommunityDAO.registMenuList", vo);
	}
	
	public CommunityMenuVO getMenu(String id) throws Exception {
		return selectOne("CommunityDAO.getMenu", id);
	}

	@Override
	public List<CommunityMenuVO> getMenuList(String communityId) throws Exception {
		return selectList("CommunityDAO.getMenuList", communityId);
	}

	/**
	 *	Community Post �뿰愿� 硫붿냼�뱶
	 * --------------------------------------------------
	 */

	@Override
	public String registPost(CommunityPostVO vo) throws Exception {
		insert("CommunityDAO.registPost", vo);
		
		return vo.getId();
	}

	@Override
	public List<CommunityPostVO> getPostList(String menuId) throws Exception {
		return selectList("CommunityDAO.getPostList", menuId);
	}

	@Override
	public CommunityPostVO getPost(String postId) throws Exception {
		return selectOne("CommunityDAO.getPost", postId);
	}

	@Override
	public void setPost(CommunityPostVO vo) throws Exception {
		insert("CommunityDAO.setPost", vo);
	}

	@Override
	public void deletePost(String PostId) throws Exception {
		delete("CommunityDAO.deletePost", PostId);
	}

	/**
	 *	Community Home �뿰愿� 硫붿냼�뱶
	 * --------------------------------------------------
	 */

	@Override
	public void registHome (CommunityMenuVO vo) throws Exception {
		insert("CommunityDAO.registHome", vo);
	}

	@Override
	public void registHomeList (List<CommunityMenuVO> vo) throws Exception {
		insert("CommunityDAO.registHomeList", vo);
	}

	@Override
	public List<CommunityPostVO> getHomeContent(String communityId) throws Exception {
		return selectList("CommunityDAO.getHomeContent", communityId);
	}

	@Override
	public List<CommunityMenuVO> getHomeMenu (String communityId) throws Exception {
		return selectList("CommunityDAO.getHomeMenu", communityId);
	}
	
}
