package tv.junnikym.kwitch.member.dao;

import java.util.List;

import tv.junnikym.kwitch.member.vo.LoginVO;
import tv.junnikym.kwitch.member.vo.MemberVO;

public interface MemberDAO {
	
	MemberVO login(LoginVO vo) throws Exception;
	
	MemberVO getMy(String id) throws Exception;
	
	int regist(MemberVO vo) throws Exception;
	
	MemberVO getDetail(String id) throws Exception;
	
	List<MemberVO> getMemberBySearch(String query) throws Exception;

	int setProfileImage(String memberId, String imageId) throws Exception;
}
