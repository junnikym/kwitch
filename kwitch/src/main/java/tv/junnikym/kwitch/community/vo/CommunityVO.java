package tv.junnikym.kwitch.community.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommunityVO {
	
	private String id;
	
	private String channelId;
	
	private String nShowNotice;
	
}
