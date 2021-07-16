package tv.junnikym.kwitch.member.dao;

import tv.junnikym.kwitch.member.vo.MemberVO;
import tv.junnikym.kwitch.member.vo.LoginVO;

public interface MemberDAO {
	
	MemberVO login(LoginVO vo) throws Exception;
	
	int regist(MemberVO vo) throws Exception;
	
	MemberVO getDetail(String id) throws Exception;

	int setProfileImage(String memberId, String imageId) throws Exception;
}
