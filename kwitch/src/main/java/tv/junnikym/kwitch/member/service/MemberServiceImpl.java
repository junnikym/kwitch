package tv.junnikym.kwitch.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.member.dao.MemberDAO;
import tv.junnikym.kwitch.member.vo.MemberVO;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

	@Resource(name="MemberDAO")
	private MemberDAO memberDAO;
	
	@Override
	public MemberVO getAlias(MemberVO vo) throws Exception {
		return memberDAO.getAlias(vo);
	}
	
}
