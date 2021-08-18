package tv.junnikym.kwitch.main.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tv.junnikym.kwitch.community.vo.CommunityPostVO;
import tv.junnikym.kwitch.main.service.MainService;

@Controller
@RequestMapping(value = "/api")
public class MainAPIController {
	
	@Resource(name = "MainService")
	MainService mainService;
	
	@ResponseBody
	@RequestMapping(value = "/q/{searchQuery}", method = RequestMethod.GET)
	public Map<String, List<?>> getPostBySearch (
			@PathVariable("searchQuery") String query,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		
		return mainService.search(query, request);
	}
	
}
