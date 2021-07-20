package tv.junnikym.kwitch.member.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberVO implements Serializable {
	
	private String id;
	
	private String email;
	
	private String pw;
	
	private String name;
	
	private String alias;
	
	private String message;
	
	private String phone;
	
	private String birth;
	
	private String createdAt;
	
	private String updatedAt;
	
	
	// JOIN
	
	private String profileImagePath;
	
	private String profileImageExt;
	
	private String channelId;
	
	private String communityId;

}
