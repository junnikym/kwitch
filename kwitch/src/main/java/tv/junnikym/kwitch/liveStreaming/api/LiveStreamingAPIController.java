package tv.junnikym.kwitch.liveStreaming.api;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tv.junnikym.kwitch.liveStreaming.service.LiveStreamingService;
import tv.junnikym.kwitch.liveStreaming.vo.LiveStreamingVO;

@Controller
@RequestMapping(value = "/api/live")
public class LiveStreamingAPIController {
	
	@Resource(name = "LiveStreamingService")
	LiveStreamingService liveStreamingService;
	
	
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public LiveStreamingVO regist(
			@RequestBody() LiveStreamingVO vo,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		try {
			String memberId = (String) session.getAttribute("member_id");
			vo.setChannelOwnerId(memberId);
			
			LiveStreamingVO result = liveStreamingService.regist(vo);
			
			if(result == null)
				response.sendError(409, "Streaming is already exist");
			else 
				return result;
			
		}
		catch(Exception e) {
			response.sendError(400, "Can not regist streaming");
			
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public void start(
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		try {
			String memberId = (String) session.getAttribute("member_id");
			
			int result = liveStreamingService.start(memberId);
			
			if(result > 0) {
				response.setStatus(200);
				return;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		response.sendError(400, "Can not start streaming");
	}
	
	@RequestMapping(value = "/stop", method = RequestMethod.POST)
	public void stop(
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		try {
			String memberId = (String) session.getAttribute("member_id");
			
			int result = liveStreamingService.stop(memberId);
			
			if(result > 0) {
				response.setStatus(200);
				return;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		response.sendError(400, "Can not start streaming");
	}
	
	
//    @RequestMapping("/events/{key}")
//    public SseEmitter handle (@PathVariable("key") String key) {
//        SseEmitter emitter = new SseEmitter();
//        liveStreamingService.setEmitter(key, emitter);
//        
//        emitter.onCompletion(() -> {
//            synchronized (this) {
//            	liveStreamingService.remove(key);
//            }
//        });
// 
//        emitter.onTimeout(()-> {
//                emitter.complete();
//        });
// 
//        
//        return emitter;
//    }
//    
//    @RequestMapping(value = "/signal/{key}", method = RequestMethod.POST)
//    public void signal(
//			@PathVariable("key") String key,
//    		HttpServletResponse response
//	) throws IOException {
//
//    	liveStreamingService.sendEmitter(key);
//		response.setStatus(200);
//    }

}
