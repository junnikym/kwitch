package tv.junnikym.kwitch.notification.dao;

import tv.junnikym.kwitch.notification.vo.NotificationVO;

import java.util.List;

public interface NotificationDAO {

	String regist (NotificationVO vo) throws Exception;

	List<NotificationVO> getOwnNotification (String receiverId) throws Exception;

	int deleteOne (String notificationId) throws Exception;

	int deleteAll (String receiverId) throws Exception;

}
