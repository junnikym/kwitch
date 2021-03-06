package tv.junnikym.kwitch.channel.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
public class ChannelRoleVO {

	private String memberId;
	
	private String roleId;
	
	private String channelId;
	
	private String authority;
	
	private Integer roleFlag;
	
	private ChannelIdBy channelIdBy;
	private String 		targetId;
	
	public class ChannelRoleFlag {
		public static final int CH_ROLE_READ 			= 1 << 0;
		public static final int CH_ROLE_WRITE 			= 1 << 1;
		public static final int CH_ROLE_UPDATE 			= 1 << 2;
		public static final int CH_ROLE_DELETE 			= 1 << 3;
		public static final int CH_ROLE_LEAVE			= 1 << 4;
		public static final int CH_ROLE_LIKE 			= 1 << 5;
		public static final int CH_ROLE_DELETE_OTHERS	= 1 << 6;
		public static final int CH_ROLE_READ_PRIVATE	= 1 << 7;
		public static final int CH_ROLE_BAN				= 1 << 8;
		public static final int CH_ROLE_FREEZE 			= 1 << 9;
		public static final int CH_ROLE_COMMNET_WRITE 	= 1 << 10;
	}
	
	public enum ChannelIdBy {
		CHANNEL_ID_TYPE_CHANNEL_ID,
		CHANNEL_ID_TYPE_COMMUNITY_ID,
		CHANNEL_ID_TYPE_OWNER_ID,
		CHANNEL_ID_TYPE_MENU_ID,
		CHANNEL_ID_TYPE_POST_ID, 
		CHANNEL_ID_TYPE_TARGET_ID, 
	}
	
}
