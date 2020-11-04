package co.mongde.board.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.mongde.board.command.AjaxMemberInsertAction;
import co.mongde.board.command.AjaxMemberList;
import co.mongde.board.command.LoginAction;
import co.mongde.board.command.LoginForm;
import co.mongde.board.command.LogoutAction;
import co.mongde.board.command.MainAction;
import co.mongde.board.command.MemberForm;
import co.mongde.board.command.MemberInsertAction;
import co.mongde.board.command.MemberListAction;
import co.mongde.board.common.Action;
import co.mongde.users.command.EmpStat;
import co.mongde.users.common.DeleteUsers;
import co.mongde.users.common.GetUsers;
import co.mongde.users.common.GetUsersList;
import co.mongde.users.common.InsertUsers;
import co.mongde.users.common.UpdateUsers;

@MultipartConfig(maxFileSize=1024*1024*2  	//size of any uploaded file
,maxRequestSize = 1024*1024*10  //overall size of all uploaded files
,location="d:/img"
,fileSizeThreshold = 1024
)

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String, Action> map = new HashMap<String, Action>();

    public FrontController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException { //init은 처음 실행할 때 한 번만 실행됨
		
		//ajax 요청
		map.put("/ajax/memberList.do", new AjaxMemberList());
		map.put("/ajax/memberInsert.do", new AjaxMemberInsertAction());
		
		//users
		map.put("/ajax/usersList.do", new GetUsersList());  //전체조회
		map.put("/ajax/users.do", new GetUsers());		    //단건조회
		map.put("/ajax/usersInsert.do", new InsertUsers()); //등
		map.put("/ajax/usersUpdate.do", new UpdateUsers()); //수정
		map.put("/ajax/usersDelete.do", new DeleteUsers()); //삭제
		
		map.put("/ajax/empStat.do", new EmpStat());
		
		// 요청들을 정의함
		map.put("/main.do", new MainAction()); //처음 들어오는 페이지 처리 index.jsp
		map.put("/login.do", new LoginAction()); //로그인 메뉴를 처리하는 것
		map.put("/loginForm.do", new LoginForm()); //로그인 폼 띄우기
		map.put("/memberList.do", new MemberListAction()); //회원전체리스트
		map.put("/memberForm.do", new MemberForm()); //회원가입하면 호출
		map.put("/memberInsert.do", new MemberInsertAction()); //회원입력
		map.put("/logout.do", new LogoutAction());
//		map.put("/loginForm.do", new LoginForm());
//		map.put("/loginForm.do", new LoginForm());
//		map.put("/loginForm.do", new LoginForm());
		
	}
	
	//컨트롤러로 넘어오면 서비스 메소드 실행
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수행할 명령을 정리
		request.setCharacterEncoding("utf-8"); //한글깨짐 방지!
		response.setContentType("text/html; charset=utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = uri.substring(contextPath.length()); //실제 들어오는 요청페이지를 찾음(uri에서 contextPath를 지움)
		
		Action command = map.get(path); //init메소드 값을 가져온다!
		String viewPage = command.exec(request, response); //명령어가 수행되고 나서 보여줄 페이지 선택
		
		if(viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage); //선택한 페이지로 가기
			dispatcher.forward(request, response);
		}
	}
}
