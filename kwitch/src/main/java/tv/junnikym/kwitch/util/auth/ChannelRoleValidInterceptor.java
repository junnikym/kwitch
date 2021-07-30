package tv.junnikym.kwitch.util.auth;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import tv.junnikym.kwitch.channel.dao.ChannelDAO;
import tv.junnikym.kwitch.channel.vo.ChannelRoleVO;
import tv.junnikym.kwitch.util.auth.ChannelRoleValid.IdGetMethod;

public class ChannelRoleValidInterceptor extends HandlerInterceptorAdapter {
	
	@Resource(name="ChannelDAO")
	ChannelDAO channelDAO;
	 
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
    		ChannelIdType 	idType		= channelRoleValid.idType();
    		IdGetMethod 	idGetMethod	= channelRoleValid.idGetMethod();
    		Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    		
    		if(idGetMethod == IdGetMethod.JUST_ID) {
    			id = (String)pathVariables.get("id");
    		}
    		else if(idGetMethod == IdGetMethod.FULLNAME) {
    			id = getIdFromPath(pathVariables, idType);
        	}
    		else if(idGetMethod == IdGetMethod.NONE) {  
		    	id = (String)pathVariables.get("id"); 
    		}
    		else if(idGetMethod == IdGetMethod.VO) {
    			id = getIdFromVO(request, idType);
    			System.out.println("id : "+id);
    		}
    		
    		if(id == null || id == "") {
    			response.sendError(401, "Unauthorized");
    			return false;
    		}
    		
    		String channelId = getChannelId(id, idType);
    		ChannelRoleVO vo = ChannelRoleVO.builder()
    									.memberId(memberId)
    									.channelId(channelId)
    									.build();
    		
    		vo = channelDAO.getRole(vo);
    		
    		int memberRoleFlag = vo.getRoleFlag();
    		
    		if((channelRoleValid.role() & memberRoleFlag) > 0)
    			return true;
    	
    		response.sendError(401, "Unauthorized");
    		
    		return false;
    	}
    	
    	return true;
    }
    
    private String getIdFromVO(
    		HttpServletRequest request, 
    		ChannelIdType idType
    ) {
    	switch(idType) {
			case CHANNEL_ID_TYPE_CHANNEL_ID:
				return (String)request.getAttribute("channelId");
		
			case CHANNEL_ID_TYPE_COMMUNITY_ID:
				return (String)request.getAttribute("communityId");
				
			case CHANNEL_ID_TYPE_OWNER_ID:
				return (String)request.getAttribute("ownChannelId");
				
			case CHANNEL_ID_TYPE_MENU_ID:
				return (String)request.getAttribute("menuId");
				
			case CHANNEL_ID_TYPE_POST_ID:
				return (String)request.getAttribute("postId");
				
			default:
				break;
		}
	
		return null;
    }
    
    private String getIdFromPath(
    		Map<?, ?> pathVariables, 
    		ChannelIdType idType
    ) {
    	switch(idType) {
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
				
			default:
				break;
		}
	
		return null;
    }
    
    private String getChannelId(
			String id, 
			ChannelIdType idType 
	) throws Exception {
		
		switch(idType) {
			case CHANNEL_ID_TYPE_CHANNEL_ID:
				return id;
		
			case CHANNEL_ID_TYPE_COMMUNITY_ID:
				return channelDAO.getChannelIdByCommunityId(id);
				
			case CHANNEL_ID_TYPE_OWNER_ID:
				return channelDAO.getChannelIdByOwnerId(id);
				
			case CHANNEL_ID_TYPE_MENU_ID:
				return channelDAO.getChannelIdByMenuId(id);
				
			case CHANNEL_ID_TYPE_POST_ID:
				return channelDAO.getChannelIdByPostId(id);
				
			default:
				break;
		}
		
		return null;
	}
	
	public enum ChannelIdType {
		CHANNEL_ID_TYPE_CHANNEL_ID,
		CHANNEL_ID_TYPE_COMMUNITY_ID,
		CHANNEL_ID_TYPE_OWNER_ID,
		CHANNEL_ID_TYPE_MENU_ID,
		CHANNEL_ID_TYPE_POST_ID, 
	}
	
}
