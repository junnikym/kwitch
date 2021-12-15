package tv.junnikym.kwitch.chat.vo;

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
public class ChatVO {
	
	private String id;

	private String writerId;
	private String writerAlias;
	
	private String channelId;
	
	private String text;
	
	
	
	private String createdAt;
	
	private Boolean is_deleted;
	
	
	
	private String error;
	
}
