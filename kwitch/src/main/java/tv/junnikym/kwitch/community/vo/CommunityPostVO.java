package tv.junnikym.kwitch.community.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommunityPostVO implements Serializable {
	
	private String id;
	
	private String title;
	
	private String content;
	
	private Boolean isCommentBlock;

	private Integer nView;
	
	private String createdAt;
	
	private String updatedAt;
	
	
	private String writerId;
	
	private String writerAlias;
	
	private String writerProfileImage;
	
	
	private String menuId;
	
	private String menuTitle;
	
	
	private String communityId;
	
	
	private String channelId;
	
	private String channelTitle;
	
}
