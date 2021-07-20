package tv.junnikym.kwitch.community.dao;

import org.springframework.stereotype.Repository;

import tv.junnikym.kwitch.util.AbstractMapper;

@Repository("CommunityDAO")
public class CommunityDAOImpl extends AbstractMapper implements CommunityDAO {

	@Override
	public int regist(String channelId) throws Exception {
		return insert("CommunityDAO.regist", channelId);
	}

}
