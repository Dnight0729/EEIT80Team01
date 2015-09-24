package question.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import global.GlobalService;
import global.XSSValidate;
import member.model.MemberBean;
import question.model.QuestionService;

@WebServlet("/member/question/createQuestion.do")
public class MemberAskQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberAskQuestionServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		MemberBean mb = (MemberBean) session.getAttribute(GlobalService.LOGIN_TOKEN);
		String member = mb.getUserName();
		XSSValidate xss = new XSSValidate();
		Map<String, String> errorMsgMap = new HashMap<String, String>();

		boolean titleOK = false;
		boolean msgOK = false;

		String title = request.getParameter("qtitle");
		if (title == null) {
			errorMsgMap.put("errorTitle", "請填寫標題");
		} else if (xss.validate(title)) {
			errorMsgMap.put("errorTitle", "偵測到script語法");
		} else if (title.trim().length() == 0) {
			errorMsgMap.put("errorTitle", "請填寫標題");
		} else {
			titleOK = true;
		}

		String qmsg = request.getParameter("qmsg");
		if (qmsg == null) {
			errorMsgMap.put("errorQMsg", "請填寫內容");
		} else if (xss.validate(qmsg)) {
			errorMsgMap.put("errorQMsg", "偵測到script語法");
		} else if (qmsg.trim().length()==0) {
			errorMsgMap.put("errorQMsg", "請填寫內容");
		} else {
			msgOK = true;
		}
		if (titleOK && msgOK) {
			QuestionService service = new QuestionService();
			boolean result = service.memberAskQuestion(member, title, qmsg);
			if (result) {
				request.setAttribute("questionSuccess", "問題傳送成功");
				RequestDispatcher rd = request.getRequestDispatcher("/member/question/NewQuestion.jsp");
				rd.forward(request, response);
				return;
			}
		} else {
			request.setAttribute("errors",errorMsgMap);
			request.setAttribute("quesstionFailed", "問題傳送失敗");
			RequestDispatcher rd = request.getRequestDispatcher("/member/question/NewQuestion.jsp");
			rd.forward(request, response);
			return;
		}

	}

}
