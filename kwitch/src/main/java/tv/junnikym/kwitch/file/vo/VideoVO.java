package tv.junnikym.kwitch.file.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tv.junnikym.kwitch.community.vo.CommunityPostVO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoVO {

	private String id;
	
	private String title;
	
	private String text;
	
	private String name;
	
	private String md5;
	
	private String path;
	
	private String type;
	
	private Integer size;
	
	
	
	private String uploaderId;
	
	private String uploaderAlias;
	
	
	
	private String createdAt;

}
