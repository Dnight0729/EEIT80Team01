package support.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberBean;
import member.model.MemberService;

@WebServlet("/support/manage/listBannedMembers.jsp")
public class SupporterListBannedMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SupporterListBannedMemberServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		MemberService service = new MemberService();
		List<MemberBean> list = service.findAllMembers();
		if (list != null) {
			RequestDispatcher rd = request.getRequestDispatcher("bannedMemberList.jsp");
			request.setAttribute("memberlist", list);
			rd.forward(request, response);
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/support/index.jsp");
			rd.forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
