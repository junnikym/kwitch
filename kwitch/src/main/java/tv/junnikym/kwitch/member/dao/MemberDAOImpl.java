package tv.junnikym.kwitch.member.dao;

import org.springframework.stereotype.Repository;

import tv.junnikym.kwitch.member.vo.MemberVO;
import tv.junnikym.kwitch.util.AbstractMapper;

@Repository("MemberDAO")
public class MemberDAOImpl extends AbstractMapper implements MemberDAO {

	@Override
	public MemberVO signIn(MemberVO vo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO getAlias(MemberVO vo) throws Exception {
		return selectOne("MemberDAO.getAlias", vo);
	}
	
	
	
}
