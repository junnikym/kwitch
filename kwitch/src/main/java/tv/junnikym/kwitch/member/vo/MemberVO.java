package tv.junnikym.kwitch.member.vo;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MemberVO implements Serializable {
	
	String id;
	
	private String email;
	
	private String pw;
	
	private String name;
	
	private String alias;
	
	private String createdAt;

}
