package tv.junnikym.kwitch.liveStreaming.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import tv.junnikym.kwitch.liveStreaming.vo.LiveStreamingVO;

//@Service("LiveStreamingService")
//public class LiveStreamingService {
//
//private Map<String, SseEmitter> datas = new ConcurrentHashMap<String, SseEmitter>();
//    
//    public void setEmitter(String key, SseEmitter emitter) {
//        this.datas.put(key, emitter);
//    }
//    
//    public void sendEmitter(String key) throws IOException {
//        for (Map.Entry<String, SseEmitter> data : this.datas.entrySet()) {
//            if (data.getKey().equals(key)) {
//                data.getValue().send(key + " is sended");
//            }
//        }
//        
//    }
//    
//    public void remove(String key) {
//        this.datas.remove(key);
//    }
//
//}


public interface LiveStreamingService {

	void setEmitter(String key, SseEmitter emitter) throws Exception;

	void sendEmitter(String key) throws IOException;

	void remove(String key) throws Exception;

	
	LiveStreamingVO regist(LiveStreamingVO vo) throws Exception;
	
	LiveStreamingVO getOwnStreaming(String ownerId) throws Exception;
	
	LiveStreamingVO getChannelStreaming(String channelId) throws Exception;
	
	int start(String ownerId) throws Exception;
	
	int stop(String ownerId) throws Exception;
	
}