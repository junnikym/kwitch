package tv.junnikym.kwitch.channel.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tv.junnikym.kwitch.channel.service.ChannelService;
import tv.junnikym.kwitch.channel.vo.ChannelVO;

@Controller
public class ChannelController {
	
	@RequestMapping(value = {"/channel/{id}"}, method = RequestMethod.GET)
	public String channel(HttpServletRequest request) {
		
		return "index";
	}

}