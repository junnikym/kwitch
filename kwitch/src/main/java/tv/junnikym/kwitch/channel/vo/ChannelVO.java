package tv.junnikym.kwitch.channel.vo;

import lombok.Data;
import tv.junnikym.kwitch.member.vo.MemberVO;

@Data
public class ChannelVO {
	
	private String id;
	
	private String ownerId;
	
	private String title;
	
	private String message;
	
	private String profileImagePath;
	
	private String profileImageExt;
	
	private Integer nSubscribe;
	
}
