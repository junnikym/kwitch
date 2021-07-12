package tv.junnikym.kwitch.member.dao;

import org.springframework.stereotype.Repository;

import tv.junnikym.kwitch.member.vo.MemberVO;
import tv.junnikym.kwitch.member.vo.LoginVO;
import tv.junnikym.kwitch.util.AbstractMapper;

@Repository("MemberDAO")
public class MemberDAOImpl extends AbstractMapper implements MemberDAO {

	@Override
	public MemberVO login(LoginVO vo) throws Exception {		
		return selectOne("MemberDAO.login", vo);
	}
	
	@Override
	public int signup(MemberVO vo) throws Exception {
		return insert("MemberDAO.signup", vo);
	}

	@Override
	public MemberVO getAlias(MemberVO vo) throws Exception {
		return selectOne("MemberDAO.getAlias", vo);
	}
	
}
