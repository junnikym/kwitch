package tv.junnikym.kwitch.member.service;

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
	
	void logout(HttpSession session) throws Exception;
	
}
