package member.core.filter;

import global.GlobalService;

import java.io.IOException;

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

import member.model.MemberBean;

@WebFilter(
		urlPatterns = { "/login/login.jsp" }
		)
public class LoginUrlFilter implements Filter {

	@Override
	public void destroy() {


	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		MemberBean loginToken = (MemberBean) session.getAttribute(GlobalService.LOGIN_TOKEN);
		if(loginToken==null){
			chain.doFilter(req, resp);
			return;
		} else {
			resp.sendRedirect(req.getContextPath());
			return;
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		

	}

}
