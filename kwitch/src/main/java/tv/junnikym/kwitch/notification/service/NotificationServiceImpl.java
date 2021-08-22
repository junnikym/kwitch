package tv.junnikym.kwitch.notification.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tv.junnikym.kwitch.channel.dao.ChannelDAO;
import tv.junnikym.kwitch.community.dao.CommunityDAO;
import tv.junnikym.kwitch.member.dao.MemberDAO;
import tv.junnikym.kwitch.notification.dao.NotificationDAO;
import tv.junnikym.kwitch.notification.vo.NotificationVO;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service("NotificationService")
public class NotificationServiceImpl implements NotificationService {

	@Resource(name = "NotificationDAO")
	private NotificationDAO notificationDAO;

	@Resource(name = "ChannelDAO")
	private ChannelDAO channelDAO;

	@Resource(name = "MemberDAO")
	private MemberDAO memberDAO;

	private Map<String, SseEmitter> datas = new ConcurrentHashMap<String, SseEmitter>();



	@Override
	public String regist(NotificationVO vo) throws Exception {
		return notificationDAO.regist(vo);
	}

	@Override
	public List<NotificationVO> getOwnNotification (String receiverId) throws Exception {
		return notificationDAO.getOwnNotification(receiverId);
	}

	@Override
	public int deleteOwn (String notificationId) throws Exception {
		return notificationDAO.deleteOne (notificationId);
	}

	@Override
	public int deleteAll (String receiverId) throws Exception {
		return notificationDAO.deleteAll (receiverId);
	}

	@Override
	public void setEmitter(String key, SseEmitter emitter) {
		this.datas.put(key, emitter);
	}

	@Override
	public void remove(String key) {
		this.datas.remove(key);
	}


	@Override
	public void sendNotification (NotificationVO vo) throws Exception {
		if (vo.getTo().equals("subscriber")) {
			this.sendEmitterToSubsriber(vo);
		}
	}

	private void sendEmitter(NotificationVO vo) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(vo);

		for (Map.Entry<String, SseEmitter> data : this.datas.entrySet()) {
			if (data.getKey().equals(vo.getReceiverId())) {
				data.getValue().send(json);
			}
		}

	}

	private void sendEmitterToSubsriber(NotificationVO vo) throws Exception {

		String channelId = memberDAO.getDetail(vo.getSenderId()).getOwnChannelId();
		List<String> subscribers = channelDAO.getSubscriberList(channelId);

		for(String id : subscribers) {
			vo.setReceiverId(id);
			notificationDAO.regist(vo);
			this.sendEmitter(vo);
		}
	}

}
