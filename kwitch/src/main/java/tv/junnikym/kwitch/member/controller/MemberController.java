package tv.junnikym.kwitch.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginView() {
		return "/resources/template/login.jsp";
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String signinView() {
		return "regist";
	}
	
}