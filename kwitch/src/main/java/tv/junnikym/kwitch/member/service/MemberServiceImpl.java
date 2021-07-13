package tv.junnikym.kwitch.member.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import tv.junnikym.kwitch.member.dao.MemberDAO;
import tv.junnikym.kwitch.member.vo.MemberVO;
import tv.junnikym.kwitch.member.vo.LoginVO;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

	@Resource(name="MemberDAO")
	private MemberDAO memberDAO;
	
	@Override
	public MemberVO login(
			LoginVO vo, 
			HttpServletRequest request, 
			HttpSession session
	) throws Exception {
		MemberVO result = memberDAO.login(vo);
		
		if(result == null) {
			return null;
		}
		
		this.setSessionAttribute(result, request, session);
		
		return result;
	}
	
	@Override
	public int registe(MemberVO vo) throws Exception {
		return memberDAO.registe(vo);
	}
	
	@Override
	public MemberVO getAlias(MemberVO vo) throws Exception {
		return memberDAO.getAlias(vo);
	}

	private void setSessionAttribute(
			MemberVO vo,
			HttpServletRequest request, 
			HttpSession session
	) {
		System.out.println(vo.toString());
		
		session.setAttribute("member_id", vo.getId());
	}
	
}
