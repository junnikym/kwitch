package tv.junnikym.kwitch.file.api;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
	
	private static final String nginxURL = "http://localhost/";
	
	private static final Integer nginxConnectTimeout = 100000;
	private static final Integer nginxReadTimeout 	 = 100000;
	private static final String  boundary 		 	 = "^-----^";
	private static final String  lineFeed			 = "\r\n";
	
	@ResponseBody
	@RequestMapping(value = "/video/upload", method = RequestMethod.POST)
	public void videoUpload (
			MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response,
			HttpSession session
	) {
		
		try {
			MultipartFile video = multipartRequest.getFile("video");
			
			URL url = new URL(nginxURL+"upload");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestProperty("Content-Type", "multipart/form-data;charset=utf-8;boundary=" + boundary);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
            conn.setUseCaches(false);
			conn.setConnectTimeout(nginxConnectTimeout); 
			conn.setReadTimeout(nginxReadTimeout);
			
			OutputStream outputStream = conn.getOutputStream(); 
			
			PrintWriter writer;
			
			writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
			
			writer.append("--" + boundary).append(lineFeed);
			writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + video.getOriginalFilename() + "\"").append(lineFeed);
			writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(video.getOriginalFilename())).append(lineFeed);
			writer.append("Content-Transfer-Encoding: binary").append(lineFeed);
			writer.append(lineFeed);
			writer.flush();
			
			InputStream inputStream = video.getInputStream();
            byte[] buffer = video.getBytes();
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            writer.append(lineFeed);
            writer.flush();

            writer.append("--" + boundary + "--").append(lineFeed);
            writer.close();
			
			int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {

				BufferedReader resInput = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder resStrBuilder = new StringBuilder();
				String resInputBuf;
				while ((resInputBuf = resInput.readLine()) != null) {
					if (resStrBuilder.length() > 0) {
						resStrBuilder.append("\n");
					}
					resStrBuilder.append(resInputBuf);
				}

				ObjectMapper mapper = new ObjectMapper();
				Map<String, String> fileInfo = mapper.readValue(resStrBuilder.toString(), Map.class);

				VideoVO vo = VideoVO.builder()
						.title(multipartRequest.getParameter("title"))
						.text(multipartRequest.getParameter("text"))
						.uploaderId((String) session.getAttribute("member_id"))
						.name( fileInfo.get("uploaded_file.name") )
						.md5 ( fileInfo.get("uploaded_file.md5") )
						.path( fileInfo.get("uploaded_file.path") )
						.type( fileInfo.get("uploaded_file.content_type") )
						.size( Integer.parseInt( fileInfo.get("uploaded_file.size") ) )
						.build();

				videoService.uploadVideo(vo);
            } else {
                System.out.println("Video upload request is NOT OK => " + Integer.toString(responseCode));
            }
            
            conn.disconnect(); 

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/upload/complete", method = RequestMethod.POST)
	public Map<String, String> nginxUploadComplete (
			HttpServletRequest request, 
			HttpServletResponse response, 
			HttpSession session
	) throws IOException {

		Map<String, String> result = new HashMap<>();

		Enumeration<String> paramKeys = request.getParameterNames();
		while (paramKeys.hasMoreElements()) {
			String key = paramKeys.nextElement();
			result.put(key, request.getParameter(key));
		}

		response.setStatus(200);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/video/{videoId}", method = RequestMethod.GET)
	public VideoVO getVideo (
			@PathVariable("videoId") String videoId,
			HttpServletResponse response,
			HttpSession session
	) throws Exception {

		return videoService.getVideo(videoId);
	}
	
	@ResponseBody
	@RequestMapping(value = "{uploaderId}/video", method = RequestMethod.GET)
	public List<VideoVO> getVideoList (
			@PathVariable("uploaderId") String uploaderId,
			HttpServletResponse response,
			HttpSession session
	) throws Exception {

		return videoService.getOwnVideoList(uploaderId);
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
