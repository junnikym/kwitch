package tv.junnikym.kwitch.simpleCapcha.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;
import nl.captcha.audio.AudioCaptcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.NumbersAnswerProducer;
import nl.captcha.text.producer.TextProducer;

public class SimpleCaptchaServiceImpl implements SimpleCaptchaService {

	private static final int width = 150;
	private static final int height = 50;
	
	private static final int nAnswer = 6;
	
	@Override
	public void genCaptchaImage(HttpServletRequest request, HttpServletResponse response) {
		
		Captcha captcha = new Captcha.Builder(this.width, this.height)
				.addText(new NumbersAnswerProducer(nAnswer))
				.addNoise()
				.addBackground(new GradiatedBackgroundProducer())
				.build();
		
		request.getSession().setAttribute(Captcha.NAME, captcha);
		
		CaptchaServletUtil.writeImage(response, captcha.getImage());
	}

	@Override
	public void genAudioCaptcha(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void genAudioCaptcha(HttpServletRequest request, HttpServletResponse response) {
//		
//		Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME); 
//		String answer = captcha.getAnswer();
//		
//		AudioCaptcha audioCaptcha = new AudioCaptcha.Builder()
//				.addAnswernew (textProducerImple(getAnswer)()
//				.addNoise()
//				.build();
//		
//		CaptchaServletUtil.writeAudio(request, audioCaptcha.getChallenge());
//	}
//	
//
//	public class textProducerImple implements TextProducer {
//
//		private final String answer;
//		
//		TextProducerImpl(String answer){
//			this.answer = answer;
//		}
//		
//		@Override
//		public String getText() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		
//	}
}
