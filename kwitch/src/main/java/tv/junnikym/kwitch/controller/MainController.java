package tv.junnikym.kwitch.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	@RequestMapping(value = {"/", "/q/*"}, method = RequestMethod.GET)
	public String home() {
		
		return "index";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {

		return "test";
	}
	
}
