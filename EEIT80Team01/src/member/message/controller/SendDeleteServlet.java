package member.message.controller;

import global.GlobalService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import member.model.MemberBean;
import member.model.MessageService;


@WebServlet("/member/message/senddelete")
public class SendDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SendDeleteServlet() {
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
		MemberBean bean = (MemberBean)session.getAttribute(GlobalService.LOGIN_TOKEN);
		String sender = bean.getUserName();
		
		String[] messageNumbers = request.getParameterValues("messageNumber");
		MessageService service = new MessageService();
		if(messageNumbers!=null && messageNumbers.length>0){
			service.changeVisibility(sender, 2, messageNumbers);			
		}
		
		response.sendRedirect("send.jsp");
	}

}
