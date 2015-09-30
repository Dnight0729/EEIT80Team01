package exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/exception/ExceptionHandler")
public class ExceptionHandler extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processError(request, response);
	}
	private void processError(HttpServletRequest request,
			HttpServletResponse response)throws ServletException,IOException{
			Throwable throwable = 
					(Throwable)request.getAttribute("javax.servlet.error.exception");
			String exceptionName = throwable.getClass().getName();
			String exceptionMsg = throwable.getMessage();
			Integer statusCode = 
					(Integer)request.getAttribute("javax.servlet.error.status_code");
			String servletName = 
					(String)request.getAttribute("javax.servlet.error.servlet_name");
			String requestUri = 
					(String)request.getAttribute("javax.servlet.error.request_uri");
			if(servletName==null){
				servletName = "不知名的程式";
			}
			if(requestUri==null){
				requestUri = "不知名的請求";
			}
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			request.setAttribute("servletName",servletName);
			request.setAttribute("requestUri",requestUri);
			request.setAttribute("statusCode",statusCode);
			request.setAttribute("exceptonName",exceptionName);
			request.setAttribute("exceptionMsg",exceptionMsg);
			request.getRequestDispatcher("exceptonPage.jsp").forward(request, response);
	}
}
