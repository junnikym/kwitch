package tv.junnikym.kwitch.file.dao;

import tv.junnikym.kwitch.file.vo.ImageVO;

public interface ImageDAO {

	String upload(ImageVO vo) throws Exception;
	
	ImageVO getImage(String id) throws Exception;
	
	ImageVO getProfileImage(String memberId) throws Exception;
	
}
