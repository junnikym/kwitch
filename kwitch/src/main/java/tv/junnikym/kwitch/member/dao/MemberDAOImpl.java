package tv.junnikym.kwitch.member.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import tv.junnikym.kwitch.member.vo.LoginVO;
import tv.junnikym.kwitch.member.vo.MemberVO;
import tv.junnikym.kwitch.util.AbstractMapper;

@Repository("MemberDAO")
public class MemberDAOImpl extends AbstractMapper implements MemberDAO {

	@Override
	public MemberVO login(LoginVO vo) throws Exception {		
		return selectOne("MemberDAO.login", vo);
	}
	
	@Override
	public int regist(MemberVO vo) throws Exception {
		return insert("MemberDAO.regist", vo);
	}

	@Override
	public MemberVO getDetail(String id) throws Exception {
		return selectOne("MemberDAO.getDetail", id);
	}

	@Override
	public int setProfileImage(
			String memberId, 
			String imageId
	) throws Exception {
		HashMap<String, String> arg = new HashMap();
		arg.put("memberId", memberId);
		arg.put("imageId", imageId);
		
		return update("MemberDAO.setProfileImage", arg);
	}
	
}
