package tv.junnikym.kwitch.simpleCapcha.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.captcha.Captcha;
import tv.junnikym.kwitch.simpleCapcha.service.SimpleCaptchaServiceImpl;

@Controller
@RequestMapping(value = "/captcha")
public class SimpleCaptchaController {
	
	@ResponseBody
	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public void captchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		new SimpleCaptchaServiceImpl().genCaptchaImage(request, response);
	}

	@ResponseBody
	@RequestMapping(value = "/check", method = RequestMethod.POST)  
	public void check(
			@RequestBody String ans,
			HttpServletRequest request, 
			HttpServletResponse response
	) throws IOException {
		
		Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
		
		if(ans!=null && !"".equals(ans)) { 
			if(captcha.isCorrect(ans)) {
				request.getSession().removeAttribute(Captcha.NAME);
				response.setStatus(200);
				return;
			}
			else {
				response.sendError(403, "Forbidden");
				return;
			} 
		}
		
		response.sendError(500, "Internal Server Error");
	}
}
