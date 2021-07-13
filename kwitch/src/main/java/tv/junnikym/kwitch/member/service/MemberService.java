package tv.junnikym.kwitch.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tv.junnikym.kwitch.member.vo.LoginVO;
import tv.junnikym.kwitch.member.vo.MemberVO;

public interface MemberService {
	
	MemberVO login(LoginVO vo, HttpServletRequest request, HttpSession session) throws Exception;
	
	int registe(MemberVO vo) throws Exception;
	
	MemberVO getAlias(MemberVO vo) throws Exception;

}
