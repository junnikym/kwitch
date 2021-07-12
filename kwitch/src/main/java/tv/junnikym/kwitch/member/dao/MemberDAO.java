package tv.junnikym.kwitch.member.dao;

import tv.junnikym.kwitch.member.vo.MemberVO;
import tv.junnikym.kwitch.member.vo.LoginVO;

public interface MemberDAO {
	
	MemberVO login(LoginVO vo) throws Exception;
	
	int signup(MemberVO vo) throws Exception;
	
	MemberVO getAlias(MemberVO vo) throws Exception;
	
}
