package tv.junnikym.kwitch.file.dao;

import org.springframework.stereotype.Repository;

import tv.junnikym.kwitch.file.vo.VideoVO;
import tv.junnikym.kwitch.util.AbstractMapper;

import java.util.List;

@Repository("VideoDAO")
public class VideoDAOImpl extends AbstractMapper implements VideoDAO {

	@Override
	public String uploadVideo (VideoVO vo) throws Exception {
		insert("VideoDAO.upload", vo);
		return vo.getId();
	}

	@Override
	public List<VideoVO> getOwnVideoList (String uploaderId) throws Exception {
		return selectList("getOwnVideoList", uploaderId);
	}

	@Override
	public VideoVO getVideo (String videoId) throws Exception {
		return selectOne("getVideo", videoId);
	}

	@Override
	public int deleteVideo (String videoId) throws Exception {
		return delete("VideoDAO.deleteVideo", videoId);
	}

}
