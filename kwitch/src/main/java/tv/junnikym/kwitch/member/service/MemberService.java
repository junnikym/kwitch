package tv.junnikym.kwitch.member.service;

import tv.junnikym.kwitch.member.vo.MemberVO;

public interface MemberService {
	
	MemberVO getAlias(MemberVO vo) throws Exception;
	
}
