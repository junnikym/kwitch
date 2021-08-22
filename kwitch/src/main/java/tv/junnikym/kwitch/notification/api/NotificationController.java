package tv.junnikym.kwitch.notification.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tv.junnikym.kwitch.member.vo.LoginVO;
import tv.junnikym.kwitch.notification.service.NotificationService;
import tv.junnikym.kwitch.notification.vo.NotificationVO;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/notification")
public class NotificationController {

	@Resource(name = "NotificationService")
	NotificationService notificationService;

	@RequestMapping("/connection")
	public SseEmitter handle(
			HttpServletResponse response,
			HttpSession session
	) throws Exception {

		SseEmitter emitter = new SseEmitter();
		String memberId = (String) session.getAttribute("member_id");

		System.out.println("session is " + memberId);

		if(memberId == null) {
			response.setStatus(403);
			throw new Exception();
		}

		notificationService.setEmitter(memberId, emitter);

		emitter.onCompletion(() -> {
			synchronized (this) {
				notificationService.remove(memberId);
			}
		});

		emitter.onTimeout (emitter::complete);

		return emitter;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<NotificationVO> getOwnNotification(
			HttpServletResponse response,
			HttpSession session
	) throws Exception {

		String memberId = (String) session.getAttribute("member_id");
		return notificationService.getOwnNotification(memberId);
	}
}
