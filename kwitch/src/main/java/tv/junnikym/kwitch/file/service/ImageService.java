package tv.junnikym.kwitch.file.service;

import org.springframework.web.multipart.MultipartFile;

import tv.junnikym.kwitch.file.vo.ImageVO;

public interface ImageService {

	public boolean upload(String uploaderId, MultipartFile file, ImageVO.Usage usage) throws Exception;
	
}
