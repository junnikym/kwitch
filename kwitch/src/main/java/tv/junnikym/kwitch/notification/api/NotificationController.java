package tv.junnikym.kwitch.notification.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tv.junnikym.kwitch.notification.service.NotificationService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/api")
public class NotificationController {

	@Resource(name = "NotificationService")
	NotificationService notificationService;

	@RequestMapping("/notification/connection/{key}")
	public SseEmitter handle(@PathVariable("key") String key) {
		SseEmitter emitter = new SseEmitter();
		notificationService.setEmitter(key, emitter);

		emitter.onCompletion(() -> {
			synchronized (this) {
				notificationService.remove(key);
			}
		});

		emitter.onTimeout (emitter::complete);


		return emitter;
	}

	@RequestMapping(value = "/notification/signal/{key}", method = RequestMethod.POST)
	public void signal(@PathVariable("key") String key, HttpServletResponse response) throws IOException {
		notificationService.sendEmitter(key);
		response.setStatus(200);
	}
}
