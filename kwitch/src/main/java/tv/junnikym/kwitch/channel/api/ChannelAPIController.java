package tv.junnikym.kwitch.channel.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tv.junnikym.kwitch.channel.service.ChannelService;
import tv.junnikym.kwitch.channel.vo.ChannelVO;
import tv.junnikym.kwitch.member.vo.LoginVO;
import tv.junnikym.kwitch.member.vo.MemberVO;

@Controller
@RequestMapping(value = "/api")
public class ChannelAPIController {
	
	@Resource(name="ChannelService")
	private ChannelService channelService;
	
	@ResponseBody
	@RequestMapping(value = "/channel/regist", method = RequestMethod.POST)
	public void regist(
			@RequestBody ChannelVO vo, 
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws IOException {

		try {
			vo.setOwnerId((String) session.getAttribute("member_id"));
			
			channelService.regist(vo);

			response.setStatus(200);
			
		} catch(Exception e) {
			response.sendError(400, "Can Not Create Channel");
			
			e.printStackTrace();
		}

	}
	
	@ResponseBody
	@RequestMapping(value = {"/channel/new"}, method = RequestMethod.GET)
	public List<ChannelVO> getNewChannel(HttpServletRequest request) throws Exception {
		
		return channelService.getNewChannel();
	}
	
}
