package tv.junnikym.kwitch.file.dao;

import tv.junnikym.kwitch.file.vo.VideoVO;

import java.util.List;

public interface VideoDAO {

	String uploadVideo (VideoVO vo) throws Exception;

	List<VideoVO> getOwnVideoList (String uploaderId) throws Exception;
	
	VideoVO getVideo (String videoId) throws Exception;

	List<VideoVO> getNewVideo() throws Exception;

	List<VideoVO> getHotVideo() throws Exception;
	
	int deleteVideo (String videoId) throws Exception;
	
}
