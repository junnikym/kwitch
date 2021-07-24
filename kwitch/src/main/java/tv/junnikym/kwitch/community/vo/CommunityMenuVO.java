package tv.junnikym.kwitch.community.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import tv.junnikym.kwitch.channel.vo.ChannelRoleVO;
import tv.junnikym.kwitch.channel.vo.ChannelVO;

@Data
@Builder
@AllArgsConstructor
public class CommunityMenuVO {
	
	private String id;
	
	private String title;
	
	private String communityId;

	private boolean isAllowToRead;

	private boolean isAllowToWrite;
	
	private Integer homeSettingFlag;
	
	private Integer homePriority;
	
	private Integer nHomeThumb;
	
	public static final class CommunityHomeStyle {
		public static final int HOME_STYLE_BIG 				= 1 << 0;
		public static final int HOME_STYLE_IS_LEFT			= 1 << 1;
	}
	
}
