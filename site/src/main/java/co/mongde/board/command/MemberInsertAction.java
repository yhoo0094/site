package co.mongde.board.command;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import co.mongde.board.common.Action;
import co.mongde.board.common.FileUtil;
import co.mongde.board.dao.MemberDao;
import co.mongde.board.vo.MemberVO;
import co.mongde.board.common.FileRenamePolicy;

public class MemberInsertAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// TODO 회원정보를 DB에 입력한다.
		MemberDao dao = new MemberDao();
		MemberVO vo = new MemberVO();

		vo.setId(request.getParameter("id")); // form객체의 name이 id인 것을 받음
		vo.setName(request.getParameter("name"));
		vo.setPassword(request.getParameter("password"));
		vo.setAddress(request.getParameter("address"));
		vo.setTel(request.getParameter("tel"));
		vo.setEnterdate(Date.valueOf(request.getParameter("enterdate"))); // String값을 날짜값으로 바꿔준다
		

		// 현재 컨텍스트에 업로드하기
		String appPath = request.getServletContext().getRealPath("/images");

		try {
			for (Part part : request.getParts()) { // 첨부파일을 읽어온다
				String fileName = FileUtil.extractFileName(part); // 파일이 존재하면
				if (!fileName.equals("")) {
					String uploadFile = appPath + File.separator + fileName;
					// 파일명 중복체크
					File renameFile = FileRenamePolicy.rename(new File(uploadFile));
					part.write(renameFile.getAbsolutePath());
					request.setAttribute("img", renameFile.getName());
					vo.setImg(renameFile.getName());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int n = dao.insert(vo); // insert하고 n값을 반환해준다.
		String page;
		if (n != 0) {
			page = "jsp/member/insertSucess.jsp";
		} else {
			page = "jsp/member/insertFail.jsp";
		}
		return page;
	}

}
