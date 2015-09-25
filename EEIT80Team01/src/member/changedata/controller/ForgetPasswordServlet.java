package member.changedata.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.FindPwBean;
import member.model.FindPwService;
import member.model.MemberBean;
import member.model.MemberService;


@WebServlet("/service/forgetpassword")
public class ForgetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ForgetPasswordServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/service/forgetpassword.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String username = request.getParameter("username").toLowerCase();
		String email = request.getParameter("email");
		if(username!=null && email!=null){
			MemberService ms = new MemberService();
			MemberBean mb = ms.findMemberData(username);
			
			if(mb!=null && email.equals(mb.getEmail())){
				FindPwService fs = new FindPwService();
				FindPwBean fb = fs.writeLog(username);
				String url = fs.bulidUrl(request, fb);//將url以信件送出
				fs.sendEmail(email, url);			
				session.setAttribute("message", "您的重設信件已送出，請置電子郵件信箱查看");
				response.sendRedirect(request.getContextPath()+"/service/forgetpassword.jsp");
				return;
			}
		}		
			session.setAttribute("message", "信件無法送出，請確認您的帳號以及電子郵件信箱");
			response.sendRedirect(request.getContextPath()+"/service/forgetpassword.jsp");
		
	}

}
