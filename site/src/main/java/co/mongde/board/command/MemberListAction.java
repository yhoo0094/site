package co.mongde.board.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.mongde.board.common.Action;
import co.mongde.board.common.Paging;
import co.mongde.board.dao.MemberDao;
import co.mongde.board.vo.MemberVO;

public class MemberListAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO 회원리스트 보기 구현
		
		// dao랑 vo수정해야 한다!
		MemberDao dao = new MemberDao();
		MemberDao cntdao = new MemberDao();
		MemberVO vo = new MemberVO();
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		//페이지 번호 파라미터 
		String strp = request.getParameter("p");
		
		//페이지 번호가 없으면 1로 최기화
		int p = 1;
		if(strp != null && ! strp.equals("")) {
			p = Integer.parseInt(strp);
		}
		
		//레코드건수 조회
		Paging paging = new Paging();
		paging.setPageUnit(2); //한 페이지에 출력할 레코드 수 (기본값은 10)
		paging.setPageSize(3); //한 페이지에 출력할 페이지 번호 수 (기본값은 10)
		paging.setPage(p); //현재 페이지 번호
		
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		paging.setTotalRecord(cntdao.count(vo));
		
		//페이징 객체를 저장
		request.setAttribute("paging", paging);
		
		//해당 페이지의 리스트만 조회
		list = dao.selectAll(vo);
		
		//목록 결과와 페이징 객체를 저장해서 뷰페이지로 넘기기
		request.setAttribute("members", list);
		
		return "jsp/member/memberList.jsp";
	}

}
