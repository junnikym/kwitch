package tv.junnikym.kwitch.file.dao;

import org.springframework.stereotype.Repository;

import tv.junnikym.kwitch.file.vo.ImageVO;
import tv.junnikym.kwitch.util.AbstractMapper;

@Repository("ImageDAO")
public class ImageDAOImpl extends AbstractMapper implements ImageDAO {

	@Override
	public String upload(ImageVO vo) throws Exception {
		insert("ImageDAO.upload", vo);
		return vo.getId();
	}

	@Override
	public ImageVO getImage(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageVO getProfileImage(String memberId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
