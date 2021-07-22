package tv.junnikym.kwitch.file.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import tv.junnikym.kwitch.file.service.ImageService;
import tv.junnikym.kwitch.file.vo.ImageVO;

@Controller
@RequestMapping(value = "/api")
public class FileAPIController {

	@Resource(name="ImageService")
	private ImageService imageService;

	@Resource(name="uploadProfileImagePath")
	private String uploadProfileImagePath;

	private static final String viewName = "../resources/api/memberProc";

	@ResponseBody
	@RequestMapping(value = "/profile/image/{image}/{ext}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getProfileImage (
			@PathVariable("image") String image,
			@PathVariable("ext") String ext
	) throws IOException {
		
		InputStream inputStream = new FileInputStream(uploadProfileImagePath +"/"+ image+"."+ext);

		return new ResponseEntity<byte[]>(IOUtils.toByteArray(inputStream), HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/profile/image/{uploaderId}", method = RequestMethod.POST)
	public void setProfileImage (
			@PathVariable("uploaderId") String uploaderId,
			MultipartHttpServletRequest file
	) throws Exception {
		System.out.println(file.getFile("profileImage"));
		
		this.imageService.upload(uploaderId, file.getFile("profileImage"), ImageVO.Usage.PROFILE_IMAGE);
	}
	
	@ResponseBody
	@RequestMapping(value = "/post/image/{uploaderId}", method = RequestMethod.POST)
	public void setPostImage (
			@PathVariable("uploaderId") String uploaderId,
			MultipartHttpServletRequest file
	) throws Exception {
		System.out.println(file.getFile("profileImage"));
		
		this.imageService.upload(uploaderId, file.getFile("profileImage"), ImageVO.Usage.PROFILE_IMAGE);
	}
}
