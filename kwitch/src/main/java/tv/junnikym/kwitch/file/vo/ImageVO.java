package tv.junnikym.kwitch.file.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ImageVO {
	
	private String id;
	
	private String uploaderId;
	
	private String path;

	private String extension;
	
	private String usage;
	
	private String createAt;
	
	public static enum Usage {
		PROFILE_IMAGE,
		PROFILE_BACKGROUND_IMAGE
	}
	
}
