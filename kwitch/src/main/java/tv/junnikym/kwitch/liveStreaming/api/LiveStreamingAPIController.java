package tv.junnikym.kwitch.liveStreaming.api;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import tv.junnikym.kwitch.liveStreaming.service.LiveStreamingService;

@Controller
@RequestMapping(value = "/api/live/")
public class LiveStreamingAPIController {
	
	@Resource(name = "LiveStreamingService")
	LiveStreamingService liveStreamingService;
	
    @RequestMapping("/events/{key}")
    public SseEmitter handle (@PathVariable("key") String key) {
        SseEmitter emitter = new SseEmitter();
        liveStreamingService.setEmitter(key, emitter);
        
        emitter.onCompletion(() -> {
            synchronized (this) {
            	liveStreamingService.remove(key);
            }
        });
 
        emitter.onTimeout(()-> {
                emitter.complete();
        });
 
        
        return emitter;
    }
    
    @RequestMapping(value = "/signal/{key}", method = RequestMethod.POST)
    public void signal(
			@PathVariable("key") String key,
    		HttpServletResponse response
	) throws IOException {

    	liveStreamingService.sendEmitter(key);
		response.setStatus(200);
    }

}
