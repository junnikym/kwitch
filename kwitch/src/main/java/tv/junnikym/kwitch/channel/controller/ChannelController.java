package tv.junnikym.kwitch.channel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChannelController {
	
	@RequestMapping(value = {"/channel/{id}"}, method = RequestMethod.GET)
	public String channel(HttpServletRequest request, HttpSession session) {
		if(request.isRequestedSessionIdValid()) {
			String member_id = (String) session.getAttribute("member_id");
		}
		
		return "index";
	}

}