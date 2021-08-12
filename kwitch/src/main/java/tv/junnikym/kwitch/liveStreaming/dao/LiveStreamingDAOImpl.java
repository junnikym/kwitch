package tv.junnikym.kwitch.liveStreaming.dao;

import org.springframework.stereotype.Repository;

import tv.junnikym.kwitch.liveStreaming.vo.LiveStreamingVO;
import tv.junnikym.kwitch.util.AbstractMapper;

@Repository("LiveStreamingDAO")
public class LiveStreamingDAOImpl extends AbstractMapper implements LiveStreamingDAO {

	@Override
	public LiveStreamingVO regist(LiveStreamingVO vo) throws Exception {
		insert("LiveStreamingDAO.regist", vo);
		return vo;
	}

	@Override
	public LiveStreamingVO getOwnStreaming(String ownerId) throws Exception {
		return selectOne("LiveStreamingDAO.getOwnStreaming", ownerId);
	}

	@Override
	public LiveStreamingVO getChannelStreaming(String channelId) throws Exception {
		return selectOne("LiveStreamingDAO.getChannelStreaming", channelId);
	}

	@Override
	public int start(String ownerId) throws Exception {
		return update("LiveStreamingDAO.start", ownerId);
	}

	@Override
	public int stop(String ownerId) throws Exception {
		return update("LiveStreamingDAO.stop", ownerId);
	}
	
}
