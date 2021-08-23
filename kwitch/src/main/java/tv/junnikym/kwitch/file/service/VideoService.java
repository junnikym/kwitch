package tv.junnikym.kwitch.file.service;

import tv.junnikym.kwitch.file.vo.VideoVO;

import java.util.List;

public interface VideoService {

	String uploadVideo(VideoVO vo) throws Exception;
	
	VideoVO getVideo (String videoId) throws Exception;

	List<VideoVO> getOwnVideoList (String uploaderId) throws Exception;
	
	int deleteVideo(String videoId) throws Exception;
	
}
