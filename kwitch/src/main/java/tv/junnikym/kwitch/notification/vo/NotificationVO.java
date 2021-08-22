package tv.junnikym.kwitch.notification.vo;

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
public class NotificationVO {

	private String id;

	private String href;

	private String text;

	private Boolean isChecked;

	private String senderId;



	private String receiverId;

	private String receiverAlias;

	private String senderProfileImageExt;

	private String senderProfileImagePath;



	private String to;

}
