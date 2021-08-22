package tv.junnikym.kwitch.notification.dao;

import org.springframework.stereotype.Repository;
import tv.junnikym.kwitch.notification.vo.NotificationVO;
import tv.junnikym.kwitch.util.AbstractMapper;

import java.util.List;

@Repository("NotificationDAO")
public class NotificationDAOImpl extends AbstractMapper implements NotificationDAO {

	@Override
	public String regist (NotificationVO vo) throws Exception {
		insert("NotificationDAO.regist", vo);
		return vo.getId();
	}

	@Override
	public List<NotificationVO> getOwnNotification (String receiverId) throws Exception {
		return selectList("NotificationDAO.getOwnNotification", receiverId);
	}

	@Override
	public int deleteOne (String notificationId) throws Exception {
		return delete("NotificationDAO.deleteOne", notificationId);
	}

	@Override
	public int deleteAll (String receiverId) throws Exception {
		return delete("NotificationDAO.deleteAll", receiverId);
	}

}
