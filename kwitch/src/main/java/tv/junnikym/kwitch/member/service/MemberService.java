package tv.junnikym.kwitch.member.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import tv.junnikym.kwitch.member.vo.LoginVO;
import tv.junnikym.kwitch.member.vo.MemberVO;

public interface MemberService {
	
	MemberVO login(LoginVO vo, HttpServletRequest request, HttpSession session) throws Exception;
	
	MemberVO getMy(String id) throws Exception;
	
	int regist(MemberVO vo) throws Exception;
	
	MemberVO getDetail(String id) throws Exception;
	
	List<MemberVO> getMemberBySearch(String query) throws Exception;
	
	void logout(HttpSession session) throws Exception;
	
}
