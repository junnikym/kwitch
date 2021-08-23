package tv.junnikym.kwitch.file.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.file.dao.VideoDAO;
import tv.junnikym.kwitch.file.vo.VideoVO;
import tv.junnikym.kwitch.member.dao.MemberDAO;
import tv.junnikym.kwitch.member.vo.MemberVO;
import tv.junnikym.kwitch.notification.service.NotificationService;
import tv.junnikym.kwitch.notification.vo.NotificationVO;

import java.util.List;

@Service("VideoService")
public class VideoServiceImpl implements VideoService {

	@Resource(name="VideoDAO")
	private VideoDAO videoDAO;

	@Resource(name="MemberDAO")
	private MemberDAO memberDAO;

	@Resource(name="NotificationService")
	NotificationService notificationService;

	static final String SHOW_VIDEO_URL = "/";
	


	@Override
	public String uploadVideo(VideoVO vo) throws Exception {
		String result = videoDAO.uploadVideo(vo);

		if(result != null) {

			MemberVO uploader = memberDAO.getMy(vo.getUploaderId());
			String notificationText = uploader.getAlias() + " did upload new video";

			NotificationVO notificationVO = NotificationVO.builder()
														.senderId(vo.getUploaderId())
														.to("subscriber")
														.href(SHOW_VIDEO_URL)
														.text(notificationText)
														.build();

			notificationService.sendNotification(notificationVO);

			return result;
		}

		return null;
	}

	@Override
	public VideoVO getVideo(String videoId) throws Exception {
		return videoDAO.getVideo(videoId);
	}

	@Override
	public List<VideoVO> getOwnVideoList (String uploaderId) throws Exception {
		return videoDAO.getOwnVideoList(uploaderId);
	}

	@Override
	public int deleteVideo(String videoId) throws Exception {
		return videoDAO.deleteVideo(videoId);
	}
	
}
