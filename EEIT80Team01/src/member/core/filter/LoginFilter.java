package member.core.filter;
import global.GlobalService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import support.model.SupportBean;
import admin.model.AdminBean;
import member.model.MemberBean;
import member.model.MemberService;

@WebFilter(
		urlPatterns = { "/member/*", "/items/*","/trade/*" }
		)
public class LoginFilter implements Filter {
	Collection<String> url = new ArrayList<String>();
	String servletPath;
	String contextPath;
	String requestURI;
	public void init(FilterConfig fConfig) throws ServletException {
		Enumeration<String> e = fConfig.getInitParameterNames();
		while (e.hasMoreElements()) {
			String path = e.nextElement();
			url.add(fConfig.getInitParameter(path));
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		boolean isRequestedSessionIdValid = false;
		if (request instanceof HttpServletRequest
				&& response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			servletPath = req.getServletPath();  
			contextPath = req.getContextPath();
			requestURI  = req.getRequestURI();
			String queryString = req.getQueryString();
			if(queryString!=null){
				requestURI = requestURI+"?"+queryString;
			}
			isRequestedSessionIdValid = req.isRequestedSessionIdValid();
			HttpSession session = req.getSession();
			MemberBean loginToken = (MemberBean) session.getAttribute(GlobalService.LOGIN_TOKEN);
				if (checkLogin(req)) {   //  需要登入，已經登入
					MemberService service = new MemberService();
					MemberBean user = service.findMemberData(loginToken.getUserName());
					if (user.getAccess()==1){
						session.removeAttribute(GlobalService.LOGIN_TOKEN);
						session.setAttribute("memberBan", "此帳號已被停權");
						resp.sendRedirect(contextPath + "/login/login.jsp");
						return;						
					} else {
						chain.doFilter(request, response);
					}
				} else {				//  需要登入，尚未登入
					session.setAttribute("requestURI", requestURI);
					if ( ! isRequestedSessionIdValid ) {
						session.setAttribute("timeOut", "使用逾時，請重新登入");
					}
					resp.sendRedirect(contextPath + "/login/login.jsp");
					return;
				}				
		}
	}
	private boolean checkLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		MemberBean loginToken = (MemberBean) session.getAttribute(GlobalService.LOGIN_TOKEN);
		AdminBean adminToken = (AdminBean) session.getAttribute(GlobalService.LOGIN_TOKEN_ADMIN);
		SupportBean supportToken = (SupportBean) session.getAttribute(GlobalService.LOGIN_TOKEN_SUPPORT);
		if (loginToken == null && adminToken == null && supportToken==null) {
			return false;
		} else {
			return true;
		}
	}


	@Override
	public void destroy() {
	}
}