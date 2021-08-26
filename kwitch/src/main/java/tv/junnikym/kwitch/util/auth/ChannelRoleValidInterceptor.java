package tv.junnikym.kwitch.util.auth;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import tv.junnikym.kwitch.channel.service.ChannelService;
import tv.junnikym.kwitch.channel.vo.ChannelRoleVO;
import tv.junnikym.kwitch.channel.vo.ChannelRoleVO.ChannelIdBy;
import tv.junnikym.kwitch.util.auth.ChannelRoleValid.IdGetMethod;

public class ChannelRoleValidInterceptor extends HandlerInterceptorAdapter {
	
	@Resource(name="ChannelService")
	ChannelService channelService;
	 
    @Override
    public boolean preHandle(
    		HttpServletRequest request, 
    		HttpServletResponse response, 
    		Object handler
    )throws Exception {
    	
    	System.out.println("Channel Role Vaild Interceptor Test ");
    	System.out.println("--------------------------------------------------");
    	
    	if (handler instanceof HandlerMethod) {
    		
    		HttpSession session = request.getSession();
        	
        	HandlerMethod method = ((HandlerMethod)handler);
        	ChannelRoleValid channelRoleValid = method.getMethodAnnotation(ChannelRoleValid.class);
           
        	if(channelRoleValid == null)
        		return true;
        	
        	String memberId = (String)session.getAttribute("member_id");
        	
    		if(memberId == null || memberId == "") {
    			response.sendError(401, "Unauthorized");
    			return false;
    		}
    		
    		String 			id 			= null;
    		ChannelIdBy 	getIdBy		= channelRoleValid.idBy();
    		IdGetMethod 	idGetMethod	= channelRoleValid.idGetMethod();
    		Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    		
    		if(idGetMethod == IdGetMethod.JUST_ID) {
    			id = (String)pathVariables.get("id");
    		}
    		else if(idGetMethod == IdGetMethod.FULLNAME) {
    			id = getIdFromPath(pathVariables, getIdBy);
        	}
    		else if(idGetMethod == IdGetMethod.NONE) {  
		    	id = (String)pathVariables.get("id"); 
    		}
    		else if(idGetMethod == IdGetMethod.VO) {
    			id = getIdFromVO(request, getIdBy);
    			System.out.println("id : "+id);
    		}
    		
    		if(id == null || id == "") {
    			response.sendError(401, "Unauthorized");
    			return false;
    		}
    		
    		ChannelRoleVO vo = ChannelRoleVO.builder()
					.memberId(memberId)
					.targetId(id)
					.channelIdBy(getIdBy)
					.build();
    		
    		vo = channelService.getRole(vo);
    		if(vo == null) {
    			response.sendError(401, "Unauthorized");
    			return false;
    		}
    		
    		Integer memberRoleFlag = vo.getRoleFlag();
    		
    		if( memberRoleFlag != null && (channelRoleValid.role() & memberRoleFlag) > 0)
    			return true;
    	
    		response.sendError(401, "Unauthorized");
    		
    		return false;
    	}
    	
    	return true;
    }
    
    private String getIdFromVO(
    		HttpServletRequest request, 
    		ChannelIdBy idBy
    ) throws Exception {
    	ObjectMapper mapper = new ObjectMapper();
    	Map<String, String> body = mapper.readValue(
    			(String) request.getAttribute("requestBody"), Map.class);
    	
    	switch(idBy) {
			case CHANNEL_ID_TYPE_CHANNEL_ID:
				return (String)body.get("channelId");
		
			case CHANNEL_ID_TYPE_COMMUNITY_ID:
				return (String)body.get("communityId");
				
			case CHANNEL_ID_TYPE_OWNER_ID:
				return (String)body.get("ownChannelId");
				
			case CHANNEL_ID_TYPE_MENU_ID:
				return (String)body.get("menuId");
				
			case CHANNEL_ID_TYPE_POST_ID:
				return (String)body.get("postId");
				
			default:
				break;
		}
	
		return null;
    }
    
    private String getIdFromPath(
    		Map<?, ?> pathVariables, 
    		ChannelIdBy idBy
    ) {
    	switch(idBy) {
			case CHANNEL_ID_TYPE_CHANNEL_ID:
				return (String)pathVariables.get("channelId");
		
			case CHANNEL_ID_TYPE_COMMUNITY_ID:
				return (String)pathVariables.get("communityId");
				
			case CHANNEL_ID_TYPE_OWNER_ID:
				return (String)pathVariables.get("ownChannelId");
				
			case CHANNEL_ID_TYPE_MENU_ID:
				return (String)pathVariables.get("menuId");
				
			case CHANNEL_ID_TYPE_POST_ID:
				return (String)pathVariables.get("postId");
				
			case CHANNEL_ID_TYPE_TARGET_ID:
				return (String)pathVariables.get("targetId");
				
			default:
				break;
		}
	
		return null;
    }
    
    
	
	
	
}
