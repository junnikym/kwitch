package tv.junnikym.kwitch.member.dao;

import tv.junnikym.kwitch.member.vo.MemberVO;

public interface MemberDAO {
	
	MemberVO signIn(MemberVO vo) throws Exception;
	
	MemberVO getAlias(MemberVO vo) throws Exception;
	
}
