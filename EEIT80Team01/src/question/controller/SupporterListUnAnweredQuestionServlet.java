package question.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import question.model.QuestionBean;
import question.model.QuestionService;

@WebServlet("/support/manage/question/listUnansweredQuestionsServlet.jsp")
public class SupporterListUnAnweredQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SupporterListUnAnweredQuestionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		QuestionService service = new QuestionService();
		List<QuestionBean> qbl = service.supporterListUnAnsweredQuestions();
		if(qbl != null) {
			RequestDispatcher rd = request.getRequestDispatcher("listQuestions.jsp");
			request.setAttribute("unansweredQuestions",qbl);
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
