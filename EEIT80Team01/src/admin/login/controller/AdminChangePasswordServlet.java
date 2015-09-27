package admin.login.controller;

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

import admin.model.AdminBean;
import admin.model.AdminService;
import global.GlobalService;

@WebServlet("/admin/password/adminChangePassword.do")
public class AdminChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminChangePasswordServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		AdminBean bean = (AdminBean) session.getAttribute(GlobalService.LOGIN_TOKEN_ADMIN);
		String adminname = bean.getAdminname();
		String oldpassword = request.getParameter("oldpassword");
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordCheck");
		
		AdminBean ab = new AdminBean();
		AdminService service = new AdminService();
		ab = service.CheckAdminNamePassword(adminname, oldpassword);
		
		// error handle block
		Map<String, String> errorMsgMap = new HashMap<String, String>();
		if (oldpassword == null || oldpassword.trim().length() == 0) {
			errorMsgMap.put("oldPasswordError", "請輸入舊密碼");
		}
		if (ab == null) {
			errorMsgMap.put("oldPasswordError", "舊密碼錯誤");
		}
		if (password == null || password.trim().length() == 0) {
			errorMsgMap.put("passwordError", "請輸入新密碼");
		}
		if (passwordCheck == null || passwordCheck.trim().length() == 0) {
			errorMsgMap.put("passwordCheckError", "請再次確認密碼");
		}
		if (!password.equals(passwordCheck)) {
			errorMsgMap.put("passwordCheckError", "新密碼不相符");
		}
		
		if (password != null && password.length() >= 5 && password.equals(passwordCheck)) {
			bean.setPasswd(GlobalService.getMD5Encoding(password));
		}
		
		if (errorMsgMap.isEmpty()) {
			service.changeAdminPassword(bean);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/index.jsp");
			session.removeAttribute(GlobalService.LOGIN_TOKEN_ADMIN);
			session.setAttribute(GlobalService.LOGIN_TOKEN_ADMIN, bean);
			rd.forward(request, response);
		} else {
			request.setAttribute("errors", errorMsgMap);
			RequestDispatcher rd = request.getRequestDispatcher("changePassword.jsp");
			rd.forward(request, response);
		}
	}

}
