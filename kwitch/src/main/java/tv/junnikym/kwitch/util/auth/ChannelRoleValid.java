package tv.junnikym.kwitch.util.auth;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import tv.junnikym.kwitch.util.auth.ChannelRoleValidInterceptor.ChannelIdType;

@Target({ ElementType.METHOD, ElementType.TYPE }) 
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChannelRoleValid {
	
	int role();
	
	ChannelIdType idType() default ChannelIdType.CHANNEL_ID_TYPE_CHANNEL_ID;
	
}
