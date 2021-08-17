package tv.junnikym.kwitch.file.dao;

import org.springframework.stereotype.Repository;

import tv.junnikym.kwitch.file.vo.VideoVO;
import tv.junnikym.kwitch.util.AbstractMapper;

@Repository("VideoDAO")
public class VideoDAOImpl extends AbstractMapper implements VideoDAO {

	@Override
	public String uploadVideo(VideoVO vo) throws Exception {
		insert("VideoDAO.upload", vo);
		return vo.getId();
	}

	@Override
	public VideoVO getVideo(String videoId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteVideo(String videoId) throws Exception {
		return delete("VideoDAO.deleteVideo", videoId);
	}

}
