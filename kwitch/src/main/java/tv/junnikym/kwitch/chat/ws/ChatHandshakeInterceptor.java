package tv.junnikym.kwitch.chat.ws;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class ChatHandshakeInterceptor extends HttpSessionHandshakeInterceptor {
	public static final String Session = "Session";
	public static final String Context = "Context";

	@Override
    public boolean beforeHandshake(
		ServerHttpRequest request,
		ServerHttpResponse response, 
		WebSocketHandler wsHandler,
		Map<String, Object> attributes
    ) throws Exception {    

        ServletServerHttpRequest ssreq = (ServletServerHttpRequest) request;
        System.out.println("URI:"+request.getURI());
  
        HttpServletRequest req =  ssreq.getServletRequest();
 
        String channelId = req.getParameter("channel");
        if(channelId == null)
        	throw new Exception();
        
        attributes.put("channelId", channelId);
        
        String memberId = (String)req.getSession().getAttribute("member_id");
        if(memberId != null)
        	attributes.put("memberId", memberId);
         
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }
  
    @Override
    public void afterHandshake(
		ServerHttpRequest request,
	    ServerHttpResponse response, WebSocketHandler wsHandler,
	    Exception ex
    ) {
    	
        System.out.println("After Handshake");
  
        super.afterHandshake(request, response, wsHandler, ex);
    }

}