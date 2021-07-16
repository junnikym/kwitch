package tv.junnikym.kwitch.member.controller;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import tv.junnikym.kwitch.member.service.MemberService;
import tv.junnikym.kwitch.member.vo.MemberVO;
import tv.junnikym.kwitch.util.api.ResultModel;

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
		return "login";
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String signinView() {
		return "regist";
	}
	
	@RequestMapping(value = "/detail/{memberId}", method = RequestMethod.GET)
	public ModelAndView detail(
			@PathVariable("memberId") String id,
			HttpServletResponse response
	) throws Exception {
		
		MemberVO member = this.memberService.getDetail(id);
		
		if(member==null) {
			return ResultModel.PageNotFound(response);
		}

		return ResultModel.builder()
				.response(response)
				.status(200)
				.view("detail")
				.object( new AbstractMap.SimpleEntry<String, Object>("member", member) )
				.build()
				.getModelAndView();
	}
	
}