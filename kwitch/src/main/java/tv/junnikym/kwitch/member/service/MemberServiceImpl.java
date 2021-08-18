package tv.junnikym.kwitch.member.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import tv.junnikym.kwitch.member.dao.MemberDAO;
import tv.junnikym.kwitch.member.vo.LoginVO;
import tv.junnikym.kwitch.member.vo.MemberVO;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

	@Resource(name="MemberDAO")
	private MemberDAO memberDAO;
	
	@Resource(name="uploadProfileImagePath")
    private String uploadProfileImagePath;
	
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
	public MemberVO getMy(String id) throws Exception {
		return memberDAO.getMy(id);
	}
	
	@Override
	public int regist(MemberVO vo) throws Exception {
		return memberDAO.regist(vo);
	}
	
	@Override
	public MemberVO getDetail(String id) throws Exception {
		return memberDAO.getDetail(id);
	}
	
	@Override
	public List<MemberVO> getMemberBySearch(String query) throws Exception {
		return memberDAO.getMemberBySearch(query);
	}
	
	@Override
	public void logout(HttpSession session) throws Exception {
		session.invalidate();
	}
	
	private void setSessionAttribute(
			MemberVO vo,
			HttpServletRequest request, 
			HttpSession session
	) {
		session.setAttribute("member_id", vo.getId());
		session.setAttribute("member_name", vo.getName());
		session.setAttribute("member_alias", vo.getAlias());
		session.setAttribute("member_profile_image", vo.getProfileImagePath());
		session.setAttribute("member_profile_image_ext", vo.getProfileImageExt());
	}
	
}
