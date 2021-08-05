package tv.junnikym.kwitch.liveStreaming.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service("LiveStreamingService")
public class LiveStreamingService {

private Map<String, SseEmitter> datas = new ConcurrentHashMap<String, SseEmitter>();
    
    public void setEmitter(String key, SseEmitter emitter) {
        this.datas.put(key, emitter);
    }
    
    public void sendEmitter(String key) throws IOException {
        for (Map.Entry<String, SseEmitter> data : this.datas.entrySet()) {
            if (data.getKey().equals(key)) {
                data.getValue().send(key + " is sended");
            }
        }
        
    }
    
    public void remove(String key) {
        this.datas.remove(key);
    }

}
