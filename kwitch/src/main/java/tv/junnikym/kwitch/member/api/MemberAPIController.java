package tv.junnikym.kwitch.member.api;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import tv.junnikym.kwitch.member.service.MemberService;
import tv.junnikym.kwitch.member.vo.LoginVO;
import tv.junnikym.kwitch.member.vo.MemberVO;

@Controller
@RequestMapping(value = "/api")
public class MemberAPIController {
	
	private static final String viewName = "../resources/api/memberProc";
	
	@Resource(name="MemberService")
	private MemberService memberService;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@ResponseBody
	@RequestMapping(value = "/auth/login", method = RequestMethod.POST)
	public Map<String, Object> login(@RequestBody LoginVO vo, HttpServletResponse response, HttpSession session) {
				
		try {			
			MemberVO user = memberService.login(vo);
			
			if(user == null) {
				System.out.println("not founded user account");
				return null;
			}
			
			return objectMapper.convertValue(user, Map.class);
			
		} catch(Exception e) {
			e.printStackTrace();
			
			return null;
		}

	}
	
	@ResponseBody
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public Map<String, Object> signup(@RequestBody MemberVO vo, HttpServletResponse response, HttpSession session) {
		
		try {			
			int result = memberService.signup(vo);
			System.out.println(result);
//			if(result == null) {
//				System.out.println("not founded user account");
//				return null;
//			}
//			
//			return objectMapper.convertValue(user, Map.class);
			return null;
		} catch(Exception e) {
			e.printStackTrace();
			
			return null;
		}

	}

}
