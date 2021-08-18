package tv.junnikym.kwitch.main.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MainService {
	
	Map<String, List<?>> search(String query, HttpServletRequest request) throws Exception;
	
}
