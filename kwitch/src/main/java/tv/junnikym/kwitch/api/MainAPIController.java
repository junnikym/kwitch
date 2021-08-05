package tv.junnikym.kwitch.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tv.junnikym.kwitch.channel.service.ChannelService;
import tv.junnikym.kwitch.community.service.CommunityService;
import tv.junnikym.kwitch.community.vo.CommunityPostVO;

@Controller
@RequestMapping(value = "/api")
public class MainAPIController {
	
	@Resource(name = "CommunityService")
	CommunityService communityService;
	
	@Resource(name = "ChannelService")
	ChannelService channelService;
	

	
	@ResponseBody
	@RequestMapping(value = "/q/{searchQuery}", method = RequestMethod.GET)
	public List<CommunityPostVO> getPostBySearch (
			@PathVariable("searchQuery") String query,
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		
		CommunityPostVO vo = CommunityPostVO.builder()
											.title(query)
											.menuId( (String) request.getParameter("menu") )
											.communityId( (String) request.getParameter("community") )
											.build();

		return communityService.getPostListBySearch(vo);
	}
	
}
