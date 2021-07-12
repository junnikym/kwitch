package tv.junnikym.kwitch.member.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVO implements Serializable {

	private String email;
	
	private String pw;

}
