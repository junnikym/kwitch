package tv.junnikym.kwitch.liveStreaming.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import tv.junnikym.kwitch.liveStreaming.dao.LiveStreamingDAO;
import tv.junnikym.kwitch.liveStreaming.vo.LiveStreamingVO;

@Service("LiveStreamingService")
public class LiveStreamingServiceImpl implements LiveStreamingService {

	@Resource(name = "LiveStreamingDAO")
	LiveStreamingDAO liveStreamingDAO;

	private static Map<String, SseEmitter> datas = new ConcurrentHashMap<String, SseEmitter>();
	
	
	
	@Override
	public void setEmitter(String key, SseEmitter emitter) throws Exception {
		this.datas.put(key, emitter);
	}

	@Override
	public void sendEmitter(String key) throws IOException {
        for (Map.Entry<String, SseEmitter> data : this.datas.entrySet()) {
            if (data.getKey().equals(key)) {
                data.getValue().send(key + " is sended");
            }
        }
    }

	@Override
	public void remove(String key) throws Exception {
		this.datas.remove(key);
	}
	
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
