package tv.junnikym.kwitch.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

import tv.junnikym.kwitch.channel.vo.ChannelRoleVO.ChannelRoleFlag;
import tv.junnikym.kwitch.member.service.MemberService;
import tv.junnikym.kwitch.util.auth.ChannelRoleValid;
import tv.junnikym.kwitch.util.auth.ChannelRoleValidInterceptor.ChannelIdType;

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