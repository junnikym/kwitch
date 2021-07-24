package tv.junnikym.kwitch.community.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tv.junnikym.kwitch.community.service.CommunityService;
import tv.junnikym.kwitch.util.api.ResultModel;

@Controller
public class CommunityController {
	
	@Resource(name="CommunityService")
	private CommunityService communityService;
	
	@RequestMapping(value = "/community/*", method = RequestMethod.GET)
	public String detail (HttpServletResponse response) throws Exception {
		return "community";
	}
	
}
