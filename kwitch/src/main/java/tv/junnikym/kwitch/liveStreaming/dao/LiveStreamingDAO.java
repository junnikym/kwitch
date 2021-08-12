package tv.junnikym.kwitch.liveStreaming.dao;

import tv.junnikym.kwitch.liveStreaming.vo.LiveStreamingVO;

public interface LiveStreamingDAO {

	LiveStreamingVO regist(LiveStreamingVO vo) throws Exception;
	
	LiveStreamingVO getOwnStreaming(String ownerId) throws Exception;
	
	LiveStreamingVO getChannelStreaming(String channelId) throws Exception;
	
	int start(String ownerId) throws Exception;
	
	int stop(String ownerId) throws Exception;
	
}
