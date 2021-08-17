package tv.junnikym.kwitch.file.dao;

import tv.junnikym.kwitch.file.vo.VideoVO;

public interface VideoDAO {

	String uploadVideo(VideoVO vo) throws Exception;
	
	VideoVO getVideo(String videoId) throws Exception;
	
	int deleteVideo(String videoId) throws Exception;
	
}
