package member.changedata.controller;

import global.GlobalService;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.SessionEvent;

import member.model.MemberBean;
import member.model.MemberService;


@WebServlet("/member/changePassword")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ChangePasswordServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		MemberBean bean = (MemberBean) session.getAttribute("LoginOK");
		MemberService service = new MemberService();
		
		String username = request.getParameter("username").toLowerCase();
		String oldPassword = request.getParameter("oldPassword");
		MemberBean mb = service.checkPasswordWithUsername(username, oldPassword);
		if(mb==null){
			session.setAttribute("ChangeFailure", "修改失敗");
			response.sendRedirect(request.getContextPath()+"/member/changePassword.jsp");
			return;
		}
		boolean result=false;
		
		String password = request.getParameter("password");
		if(password!=null && password.length() >= 5){
			result = service.changePassword(username, password);
		}
		
		
		
		if(result){	
			session.setAttribute("ChangeSuccess", "修改成功");
			response.sendRedirect(request.getContextPath()+"/member/changePassword.jsp");
			return;
		} else {
			session.setAttribute("ChangeFailure", "修改失敗");
			response.sendRedirect(request.getContextPath()+"/member/changePassword.jsp");
			return;
		}
		
	}
	

}
