package tv.junnikym.kwitch.liveStreaming.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.liveStreaming.dao.LiveStreamingDAO;
import tv.junnikym.kwitch.liveStreaming.vo.LiveStreamingVO;

@Service("LiveStreamingService")
public class LiveStreamingServiceImpl implements LiveStreamingService {

	@Resource(name = "LiveStreamingDAO")
	LiveStreamingDAO liveStreamingDAO;

	@Override
	public LiveStreamingVO regist(LiveStreamingVO vo) throws Exception {
		if( liveStreamingDAO.getOwnStreaming (vo.getChannelOwnerId()) == null )
			return liveStreamingDAO.regist(vo);
		
		return null;
	}

	@Override
	public LiveStreamingVO getOwnStreaming(String ownerId) throws Exception {
		return liveStreamingDAO.getOwnStreaming(ownerId);
	}

	@Override
	public LiveStreamingVO getChannelStreaming(String channelId) throws Exception {
		return liveStreamingDAO.getChannelStreaming(channelId);
	}

	@Override
	public int start(String ownerId) throws Exception {
		return liveStreamingDAO.start(ownerId);
	}

	@Override
	public int stop(String ownerId) throws Exception {
		return liveStreamingDAO.stop(ownerId);
	}
	
}
