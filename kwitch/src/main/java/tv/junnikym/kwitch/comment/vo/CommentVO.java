package tv.junnikym.kwitch.comment.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tv.junnikym.kwitch.channel.vo.SubscribeVO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentVO {

	private String id;
	
	private String writerId;
	
	private String targetId;
	
	private CommentUsage usage;
	
	private String text;
	
	private String createdAt;
	
	private String updatedAt;

	
	private String writerAlias;
	
	private String profileImagePath;
	
	private String profileImageExt;

	public enum CommentUsage {
		COMMENT_USAGE_POST,
		COMMENT_USAGE_VIDEO,
	}
	
}
