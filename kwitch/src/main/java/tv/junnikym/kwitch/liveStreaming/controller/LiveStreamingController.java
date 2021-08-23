package tv.junnikym.kwitch.liveStreaming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LiveStreamingController {
	
	@RequestMapping(value = {"/live/guide"}, method = RequestMethod.GET)
	public String guide() {
		
		return "index";
	}
	
}
