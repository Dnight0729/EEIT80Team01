package member.changedata.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.FindPwService;


@WebServlet("/service/findpassword")
public class FindPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FindPasswordServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String pass = request.getParameter("pass");
		FindPwService service = new FindPwService();
		HttpSession session = request.getSession();
		if(username!=null && pass!=null){			
			if(service.validateData(username, pass)){				
				session.setAttribute("EmailChecked", username);
				service.deleteLog(username);			
				response.sendRedirect(request.getContextPath()+"/service/changePassword.jsp");				
			}else{
				service.deleteLog(username);
				session.setAttribute("message", "此連結已經失效，請重新申請信件");
				response.sendRedirect(request.getContextPath()+"/service/forgetpassword.jsp");
			}
		} else{
			session.setAttribute("message", "此連結已經失效，請重新申請信件");
			response.sendRedirect(request.getContextPath()+"/service/forgetpassword.jsp");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.sendRedirect(request.getContextPath()+"/service/forgetpassword.jsp");
	}

}
