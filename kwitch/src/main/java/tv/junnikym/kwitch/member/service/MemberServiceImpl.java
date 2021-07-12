package tv.junnikym.kwitch.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.member.dao.MemberDAO;
import tv.junnikym.kwitch.member.vo.MemberVO;
import tv.junnikym.kwitch.member.vo.LoginVO;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

	@Resource(name="MemberDAO")
	private MemberDAO memberDAO;
	
	@Override
	public MemberVO login(LoginVO vo) throws Exception {
		return memberDAO.login(vo);
	}
	
	@Override
	public int signup(MemberVO vo) throws Exception {
		return memberDAO.signup(vo);
	}
	
	@Override
	public MemberVO getAlias(MemberVO vo) throws Exception {
		return memberDAO.getAlias(vo);
	}
	
}
