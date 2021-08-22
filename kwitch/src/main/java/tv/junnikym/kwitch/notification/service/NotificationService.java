package tv.junnikym.kwitch.notification.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tv.junnikym.kwitch.notification.vo.NotificationVO;

import java.io.IOException;
import java.util.List;

public interface NotificationService {

	String regist(NotificationVO vo) throws Exception;

	List<NotificationVO> getOwnNotification (String receiverId) throws Exception;

	int deleteOwn (String notificationId) throws Exception;

	int deleteAll (String receiverId) throws Exception;



	void setEmitter(String key, SseEmitter emitter);

	void remove(String key);



	void sendNotification (NotificationVO vo) throws Exception;

}
