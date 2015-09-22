package item.bid.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member/login.do")
public class LoginToBidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginToBidServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemid = request.getParameter("itemid");
		response.sendRedirect(request.getContextPath()+"/search/item?itemid="+itemid);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemid = request.getParameter("itemid");
		response.sendRedirect(request.getContextPath()+"/search/item?itemid="+itemid);
	}

}
