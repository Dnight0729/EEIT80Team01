package support.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberService;

@WebServlet("/support/manage/banMember.do")
public class SupporterBanMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SupporterBanMemberServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		int result = 0;
		String[] memberaccounts = request.getParameterValues("memberChecked");
		MemberService service = new MemberService();
		if (memberaccounts != null && memberaccounts.length>0){
			result = service.banMember(memberaccounts);
		}
		request.setAttribute("bannedNumber", result);
		RequestDispatcher rd = request.getRequestDispatcher("/support/manage/listMembers.jsp");
		rd.forward(request, response);
		return;
	}

}
