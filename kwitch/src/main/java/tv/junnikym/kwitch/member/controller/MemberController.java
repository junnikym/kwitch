package tv.junnikym.kwitch.member.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

import tv.junnikym.kwitch.member.service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Resource(name="MemberService")
	private MemberService memberService;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginView() {
		return "/resources/template/login.jsp";
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String signinView() {
		return "regist";
	}
	
}