package tv.junnikym.kwitch.community.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import tv.junnikym.kwitch.community.vo.CommunityMenuVO;
import tv.junnikym.kwitch.community.vo.CommunityPostVO;
import tv.junnikym.kwitch.util.AbstractMapper;

@Repository("CommunityDAO")
public class CommunityDAOImpl extends AbstractMapper implements CommunityDAO {

	@Override
	public String regist(String id) throws Exception {
		String channelId = new String(id);
		
		insert("CommunityDAO.regist", channelId);
		return channelId;
	}

	@Override
	public void setDefaultMenu(String communityId) throws Exception {
		insert("CommunityDAO.setDefaultMenu", communityId);
	}

	@Override
	public List<CommunityMenuVO> getMenuList(String communityId) throws Exception {
		return selectList("CommunityDAO.getMenuList", communityId);
	}

	@Override
	public void registPost(CommunityPostVO vo) throws Exception {
		insert("CommunityDAO.registPost", vo);
	}

	@Override
	public List<CommunityPostVO> getPostList(String menuId) throws Exception {
		return selectList("CommunityDAO.getPostList", menuId);
	}

	@Override
	public CommunityPostVO getPost(String postId) throws Exception {
		return selectOne("CommunityDAO.getPost", postId);
	}
	
	public List<CommunityPostVO> getHome(String communityId) throws Exception {
		return selectList("CommunityDAO.getHome", communityId);
	}
	
	@Override
	public void setPost(CommunityPostVO vo) throws Exception {
		insert("CommunityDAO.setPost", vo);
	}

	@Override
	public void deletePost(String PostId) throws Exception {
		delete("CommunityDAO.deletePost", PostId);
	}
	
}
