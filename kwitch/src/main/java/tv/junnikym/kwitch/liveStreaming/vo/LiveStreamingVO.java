package tv.junnikym.kwitch.liveStreaming.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tv.junnikym.kwitch.community.vo.CommunityMenuVO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LiveStreamingVO {

	private String id;
	
	private String password;
	
	private String startAt;
	
	private String endAt;
	
	private Boolean isPrivate;
	
	
	
	private String channelId;
	
	private String channelOwnerId;
	
}
