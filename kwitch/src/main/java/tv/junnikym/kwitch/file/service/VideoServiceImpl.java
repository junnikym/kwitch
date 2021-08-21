package tv.junnikym.kwitch.file.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.file.dao.VideoDAO;
import tv.junnikym.kwitch.file.vo.VideoVO;

import java.util.List;

@Service("VideoService")
public class VideoServiceImpl implements VideoService {

	@Resource(name="VideoDAO")
	private VideoDAO videoDAO;
	
	
	
	@Override
	public String uploadVideo(VideoVO vo) throws Exception {
		return videoDAO.uploadVideo(vo);
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
