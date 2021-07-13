package tv.junnikym.kwitch.util.api;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.web.servlet.ModelAndView;

import lombok.Builder;

public class ResultModel {
	
	private HttpServletResponse response;
	private ModelAndView mv;
	
	@Builder
	protected ResultModel (
			@NotNull HttpServletResponse response,
			Map<String, Object> model,
			String view,
			Integer status,
			String resultMessage,
			String redirection
	) throws IOException {
		this.response = response;
        this.mv = new ModelAndView();
        
        if(status == null) {
        	this.response.setStatus(200);
        	this.mv.addObject("resultMessage", "success");
        }
    	else {
    		this.response.setStatus(status);
    		this.mv.addObject("resultMessage", resultMessage==null?"":resultMessage);
    	}
        
        this.mv.addAllObjects(model);
        this.mv.setViewName(view);
        
        if(redirection != null)
        	response.sendRedirect(redirection);
	}
	
	public void setStatus(int status) {
        this.response.setStatus(status);
    }

    public void setViewName(String viewName) {
        this.mv.setViewName(viewName);
    }

    public void setData(String id, Object data) {
        this.mv.addObject(id, data);
    }

    public Object getData(String key) {
        return this.mv.getModel().get(key);
    }
    
	public ModelAndView getModelAndView() {
        return this.mv;
    }
}