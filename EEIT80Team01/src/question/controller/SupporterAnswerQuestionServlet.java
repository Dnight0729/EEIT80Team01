package question.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import global.GlobalService;
import question.model.QuestionBean;
import question.model.QuestionService;
import support.model.SupportBean;

@WebServlet("/support/manage/question/supporterAnswerQuestion.do")
public class SupporterAnswerQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SupporterAnswerQuestionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		SupportBean sb = (SupportBean) session.getAttribute(GlobalService.LOGIN_TOKEN_SUPPORT);
		// member question info grab from form.
		int qno = Integer.parseInt(request.getParameter("hidden-qno"));
		String member = request.getParameter("hidden-member");
		String title = request.getParameter("hidden-title");
		String qmsg = request.getParameter("hidden-qmsg");
		long qt = Long.parseLong(request.getParameter("hidden-qt"));
		
		// supporter question answer and info grab from form.
		String supporter = sb.getSupportername();
		String amsg = request.getParameter("supporter-answer");
		long at = new Date().getTime();
		
		// put all info into a bean for transfer
		QuestionBean bean = new QuestionBean();
		bean.setQno(qno);
		bean.setMember(member);
		bean.setTitle(title);
		bean.setQmsg(qmsg);
		bean.setQt(qt);
		bean.setSupporter(supporter);
		bean.setAmsg(amsg);
		bean.setAt(at);
		
		QuestionService service = new QuestionService();
		boolean result = service.supporterAnswerQuestion(bean);
		if (result){
			RequestDispatcher rd = request.getRequestDispatcher("/support/manage/question/listUnansweredQuestionsServlet.jsp");
			rd.forward(request, response);
			return;
		} else {
			response.sendRedirect(request.getContextPath()+"/support/index.jsp");
			return;
		}
	}

}
