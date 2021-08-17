package tv.junnikym.kwitch.file.service;

import tv.junnikym.kwitch.file.vo.VideoVO;

public interface VideoService {

	String uploadVideo(VideoVO vo) throws Exception;
	
	int deleteVideo(String videoId) throws Exception;
	
}
