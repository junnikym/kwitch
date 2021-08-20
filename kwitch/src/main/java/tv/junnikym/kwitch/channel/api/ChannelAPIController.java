package tv.junnikym.kwitch.channel.api;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tv.junnikym.kwitch.channel.service.ChannelService;
import tv.junnikym.kwitch.channel.vo.ChannelVO;
import tv.junnikym.kwitch.channel.vo.SubscribeVO;

@Controller
@RequestMapping(value = "/api/channel")
public class ChannelAPIController {
	
	@Resource(name="ChannelService")
	private ChannelService channelService;
	
	@ResponseBody
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
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
	@RequestMapping(value = {"/new"}, method = RequestMethod.GET)
	public List<ChannelVO> getNewChannel(HttpServletRequest request) throws Exception {
		
		return channelService.getNewChannel();
	}
	
	/**
	 * Subscribe
	 */
	
	@ResponseBody
	@RequestMapping(value = "/{channelId}/subscribe", method = RequestMethod.GET)
	public Integer nSubscribe (
			@PathVariable("channelId") String channelId, 
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {
		
		return channelService.nSubscribe(channelId);
	}
	
	@RequestMapping(value = "/{channelId}/subscribe")
	public void subscribe (
			@PathVariable("channelId") String channelId, 
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {

		SubscribeVO vo = SubscribeVO.builder()
				.channelId(channelId)
				.subscriberId((String) session.getAttribute("member_id"))
				.build();
		
		try {

			int result = 0;
			if(request.getMethod().equals("POST")) {
				result = channelService.subscribe(vo);
			}
			else if (request.getMethod().equals("DELETE")) {
				result = channelService.unsubscribe(vo);
			}

			if(result > 0) {
				response.setStatus(200);
				return;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		response.sendError(400, "Can Not (Un)Subscribe");
	}
	
	@ResponseBody
	@RequestMapping(value = "/{channelId}/is_subscribe", method = RequestMethod.GET)
	public Boolean isSubscribed (
			@PathVariable("channelId") String channelId, 
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws Exception {

		SubscribeVO vo = SubscribeVO.builder()
				.channelId(channelId)
				.subscriberId((String) session.getAttribute("member_id"))
				.build();
		
		return channelService.isSubscribed(vo);
	}
	
}
