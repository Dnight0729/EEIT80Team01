package member.core.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.core.DateValidator;
import member.core.EmailValidator;
import member.core.IDChecker;
import member.model.MemberBean;
import member.model.MemberService;


@WebServlet("/register/register.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public RegisterServlet() {
        super();
    
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/register/register.jsp");
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		MemberBean bean = new MemberBean();
		MemberService service = new MemberService();
		String username = request.getParameter("username");
		if(username!=null && service.accountCheck(username)){
			bean.setUserName(username.toLowerCase());
			String password = request.getParameter("password");
			if(password!=null && password.length()>=5){
				bean.setPassword(password);
				String id = request.getParameter("id");
				IDChecker check = new IDChecker(id);
				if(id!=null && check.CheakID()){
					bean.setId(id);
					String firstName = request.getParameter("firstName");
					if(firstName!=null){
						bean.setFirstName(firstName);
						String lastName = request.getParameter("lastName");
						if(lastName!=null){
							bean.setLastName(lastName);
							String email = request.getParameter("email");
							EmailValidator emailcheck = new EmailValidator();
							if(email!=null && emailcheck.validate(email));{
								bean.setEmail(email);
								String birthDay = request.getParameter("birthDay");
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
								DateValidator datecheck = new DateValidator();
								if(birthDay!=null && datecheck.validate(birthDay)){
									try {
										bean.setBirthDay(sdf.parse(birthDay));
									} catch (ParseException e) {
										e.printStackTrace();
									}
									
									int gender = Integer.parseInt(request.getParameter("gender"));
									if(gender==0 || gender==1){
										bean.setGender(gender);
										bean.setAccess(0);
										bean.setCertified(0);
										MemberBean result = service.register(bean);
										if(result!=null && result.getUserName().toUpperCase().equals(bean.getUserName().toUpperCase())){
											RequestDispatcher rd = request.getRequestDispatcher("/register/register.jsp");									
											request.setAttribute("registerTrue", bean);
											rd.forward(request,response);
											return;
										}							
									}									
								}								
							}							
						}						
					}					
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("/register/register.jsp");
				request.setAttribute("registerFalse", "註冊失敗");
				rd.forward(request,response);
				return;
				
			}
			
		};
		
	}

}
