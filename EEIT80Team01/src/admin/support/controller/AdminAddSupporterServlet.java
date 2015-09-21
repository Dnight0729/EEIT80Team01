package admin.support.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import global.GlobalService;
import support.model.SupportBean;
import support.model.SupportService;

@WebServlet("/admin/manage/AddSupport.do")
public class AdminAddSupporterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddSupporterServlet() {
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
		SupportBean bean = new SupportBean();
		SupportService service = new SupportService();
		String supportername = request.getParameter("supportername");
		if (supportername != null && !service.checkSupporterAccountExist(supportername)) {
			bean.setSupportername(supportername.toLowerCase());
			String password = request.getParameter("password");
			String passwordCheck = request.getParameter("passwordCheck");
			if (password != null && password.length() >= 5 && password.equals(passwordCheck)) {
				bean.setPassword(GlobalService.getMD5Encoding(password));
				String employeeid = request.getParameter("employeeid");
				if (employeeid != null && employeeid.trim().length() != 0
						&& !service.checkEmployeeIDExist(employeeid)) {
					bean.setEmployeeid(employeeid);
					String firstname = request.getParameter("firstname");
					if (firstname != null && firstname.trim().length() != 0) {
						bean.setFirstname(firstname);
						String lastname = request.getParameter("lastname");
						if (lastname != null && lastname.trim().length() != 0) {
							bean.setLastname(lastname);
							SupportBean result = service.register(bean);
							if (result != null && result.getSupportername().toLowerCase()
									.equals(bean.getSupportername().toLowerCase())) {
								request.setAttribute("addResult", "新增 1 筆資料");
								RequestDispatcher rd = request
										.getRequestDispatcher("/admin/manage/AddNewSupporter.jsp");
								rd.forward(request, response);
								return;
							}
						}
					}
				}
			}
		}
		request.setAttribute("addResult", "新增失敗");
		RequestDispatcher rd = request.getRequestDispatcher("/admin/manage/AddNewSupporter.jsp");
		rd.forward(request, response);
		return;
	}

}
