package member.core.controller;

import global.GlobalService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.MemberBean;
import member.model.MemberService;


@WebServlet("/login/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		Map<String, String> errorMsgMap = new HashMap<String, String>();

		request.setAttribute("ErrorMsgKey", errorMsgMap);

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String requestURI = (String) session.getAttribute("requestURI");

		if (username == null || username.trim().length() == 0) {
			errorMsgMap.put("AccountEmptyError", "帳號欄必須輸入");
		}

		if (password == null || password.trim().length() == 0) {
			errorMsgMap.put("PasswordEmptyError", "密碼欄必須輸入");
		}
		

		if (!errorMsgMap.isEmpty()) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}

		MemberService service;
		service = new MemberService();

		
		MemberBean mb = service.checkPasswordWithUsername(username.toLowerCase(), password);
		if (mb != null) {
			if(mb.getAccess()==1){
				session.setAttribute("memberBan", "此帳號已被停權");
				response.sendRedirect(request.getContextPath() + "/login/login.jsp");
				return;
			}else{
				session.setAttribute(GlobalService.LOGIN_TOKEN, mb);
			}			
		} else {
			errorMsgMap.put("LoginError", "該帳號不存在或密碼錯誤");
		}

	
		if (errorMsgMap.isEmpty()) {
			if (requestURI != null) {
				session.removeAttribute("requestURI");
				requestURI = (requestURI.length() == 0 ? request
						.getContextPath() : requestURI);
				response.sendRedirect(response.encodeRedirectURL(requestURI));
				return;
			} else {
				response.sendRedirect(response.encodeRedirectURL(request
						.getContextPath()));
				return;
			}
		} else {
			// 如果errorMsgMap不是空的，表示有錯誤，交棒給login.jsp
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		}
	}

}
