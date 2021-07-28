package tv.junnikym.kwitch.community.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tv.junnikym.kwitch.channel.vo.ChannelRoleVO;
import tv.junnikym.kwitch.channel.vo.ChannelVO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommunityMenuVO {
	
	private String id;
	
	private String title;
	
	private String communityId;

	private Boolean isAllowToRead;

	private Boolean isAllowToWrite;
	
	private Integer homeSettingFlag;
	
	private Integer homePriority;
	
	private Integer nHomeThumb;
	
	public static final class CommunityHomeStyle {
		public static final int HOME_STYLE_BIG 				= 1 << 0;
		public static final int HOME_STYLE_IS_LEFT			= 1 << 1;
	}
	
}
