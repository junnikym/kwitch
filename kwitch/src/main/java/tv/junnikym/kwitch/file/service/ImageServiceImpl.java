package tv.junnikym.kwitch.file.service;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import tv.junnikym.kwitch.file.dao.ImageDAO;
import tv.junnikym.kwitch.file.vo.ImageVO;
import tv.junnikym.kwitch.member.dao.MemberDAO;

@Service("ImageService")
public class ImageServiceImpl implements ImageService {
	
	@Resource(name="uploadProfileImagePath")
	String uploadProfileImagePath;
	
	@Resource(name="uploadTrashPath")
	String uploadTrashPath;
	
	@Resource(name="ImageDAO")
	private ImageDAO imageDAO;
	
	@Resource(name="MemberDAO")
	private MemberDAO memberDAO;
	
	
	@Override
	public boolean upload(
			String uploaderId, 
			MultipartFile file, 
			ImageVO.Usage usage
	) throws Exception {
		
		int daoResult = -1;
		
		String originalName = file.getOriginalFilename();
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat ( "yyyyMMddHHmmss");
        String datePaht = dateFormat.format(calendar.getTime());
		
        String uploadPath;
        
        switch(usage) {
	        case PROFILE_IMAGE:
	        	uploadPath = uploadProfileImagePath;
	        	break;
	        default:
	        	uploadPath = uploadTrashPath;
        }
        
        String saveFileName = uploaderId+"_"+datePaht;
        String ext = originalName.substring(originalName.lastIndexOf(".") + 1);
        
		File target = new File(uploadProfileImagePath, saveFileName+"."+ext);

		if ( ! new File(uploadProfileImagePath).exists())
			new File(uploadProfileImagePath).mkdirs();
		
		FileCopyUtils.copy(file.getBytes(), target);
		
		String imageId = this.imageDAO.upload ( 
			ImageVO.builder()
				.path(saveFileName)
				.uploaderId(uploaderId)
				.extension(ext)
				.usage(usage.toString())
				.build()
		);
		
		if(imageId != null) {
			daoResult = this.memberDAO.setProfileImage(uploaderId, imageId);

			return (daoResult != -1) ? true : false;
		}
		
		return false;
	}
	
}
