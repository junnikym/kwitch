package tv.junnikym.kwitch.liked.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tv.junnikym.kwitch.file.vo.VideoVO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LikedVO {

	private LikeUsage usage;
	
	private Boolean isUnliked;
	
	private String giverId;
	
	private String targetId;
	
	public enum LikeUsage {
		LIKE_USAGE_POST,
		LIKE_USAGE_VIDEO,
		LIKE_USAGE_COMMENT, 
	}
	
}
