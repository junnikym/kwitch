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
	
}
