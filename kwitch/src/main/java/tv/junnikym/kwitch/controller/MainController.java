package tv.junnikym.kwitch.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	@RequestMapping(value = {
			"/", "/detail/*", 
			"/community/*",
			"/channel/*",
			"/community/*/menu",
			"/community/post/*", 
			"/community/post/*/edit",
			"/community/menu/*",
			"/community/post/upload/*"
	}, method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpSession session) {
		if(request.isRequestedSessionIdValid()) {
			String member_id = (String) session.getAttribute("member_id");
		}
		
		return "index";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpSession session) {
		if(request.isRequestedSessionIdValid()) {
			String member_id = (String) session.getAttribute("member_id");
		}
		
		return "edit";
	}
	
}
