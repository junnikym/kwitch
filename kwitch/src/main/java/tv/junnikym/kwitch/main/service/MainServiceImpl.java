package tv.junnikym.kwitch.main.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.community.service.CommunityService;
import tv.junnikym.kwitch.community.vo.CommunityPostVO;
import tv.junnikym.kwitch.member.service.MemberService;

@Service("MainService")
public class MainServiceImpl implements MainService {
	
	@Resource(name = "CommunityService")
	CommunityService communityService;
	
	@Resource(name = "MemberService")
	MemberService memberService;
	
	@Override
	public Map<String, List<?>> search(String query, HttpServletRequest request) throws Exception {
		Map<String, List<?>> result = new HashMap();
		
		CommunityPostVO communityVO = CommunityPostVO.builder()
													.title(query)
													.menuId( (String) request.getParameter("menu") )
													.communityId( (String) request.getParameter("community") )
													.build();
	
		result.put("member", memberService.getMemberBySearch(query));
		result.put("community", communityService.getPostListBySearch(communityVO));
		
		return result;
	}
}
