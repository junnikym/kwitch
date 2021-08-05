package tv.junnikym.kwitch.util.auth;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import tv.junnikym.kwitch.channel.vo.ChannelRoleVO.ChannelIdBy;

@Target({ ElementType.METHOD, ElementType.TYPE }) 
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChannelRoleValid {
	
	int role();
	
	ChannelIdBy idBy() default ChannelIdBy.CHANNEL_ID_TYPE_CHANNEL_ID;
	
	IdGetMethod idGetMethod() default IdGetMethod.NONE;

	public enum IdGetMethod { NONE, FULLNAME, JUST_ID, VO }
	
}
