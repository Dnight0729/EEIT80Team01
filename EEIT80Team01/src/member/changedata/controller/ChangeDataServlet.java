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

import member.core.EmailValidator;
import member.model.MemberBean;
import member.model.MemberService;


@WebServlet("/member/changeData")
public class ChangeDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ChangeDataServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/member/changeData.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		MemberBean bean = (MemberBean) session.getAttribute(GlobalService.LOGIN_TOKEN);
		String firstName = request.getParameter("fistName");
		if(firstName!=null){
			bean.setFirstName(firstName);
		}
		String lastName = request.getParameter("lastName");
		if(lastName!=null){
			bean.setLastName(lastName);
		}
		String email = request.getParameter("email");
		EmailValidator emailcheck = new EmailValidator();
		if(email!=null && emailcheck.validate(email)){
			bean.setEmail(email);
		}
		MemberService service = new MemberService();
		service.changeMemberData(bean);
		
		if(bean!=null){
			session.removeAttribute(GlobalService.LOGIN_TOKEN);
			session.setAttribute(GlobalService.LOGIN_TOKEN, bean);
			session.setAttribute("ChangeSuccess", "修改成功");
			response.sendRedirect(request.getContextPath()+"/member/changeData.jsp");
			return;
		} else {
			session.setAttribute("ChangeFailure", "修改失敗");
			response.sendRedirect(request.getContextPath()+"/member/changeData.jsp");
			return;
		}
		
	}

}
