package tv.junnikym.kwitch.simpleCapcha.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SimpleCaptchaService {
	
	public class genCaptchaImage {

	}

	void genCaptchaImage(HttpServletRequest request, HttpServletResponse response);
	
	void genAudioCaptcha(HttpServletRequest request, HttpServletResponse response);
	
}
