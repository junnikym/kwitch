package tv.junnikym.kwitch.file.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import tv.junnikym.kwitch.file.service.VideoService;
import tv.junnikym.kwitch.file.vo.ImageVO;
import tv.junnikym.kwitch.file.vo.VideoVO;

@Controller
@RequestMapping(value = "/api")
public class FileAPIController {

	@Resource(name="ImageService")
	private ImageService imageService;
	
	@Resource(name="VideoService")
	private VideoService videoService;
	
	

	@Resource(name="uploadProfileImagePath")
	private String uploadProfileImagePath;

	private static final String viewName = "../resources/api/memberProc";

	@ResponseBody
	@RequestMapping(value = "/upload/complete", method = RequestMethod.POST)
	public void nginxUploadComplete (
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) {
		
		try {
			VideoVO vo = VideoVO.builder()
//					.uploaderId((String) session.getAttribute("member_id"))
					.uploaderId("0cb6b1c2-0cda-4809-84c7-3752aa58043a")
					.name(request.getParameter("uploaded_file.name"))
					.md5(request.getParameter ("uploaded_file.md5"))
					.path(request.getParameter("uploaded_file.path"))
					.type(request.getParameter("uploaded_file.content_type"))
					.size( Integer.parseInt( request.getParameter("uploaded_file.size") ) )
					.build();
			
			videoService.uploadVideo(vo);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		response.setStatus(200);
	}
	
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
