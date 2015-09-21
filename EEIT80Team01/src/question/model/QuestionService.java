package question.model;

import java.util.List;

import question.model.dao.QuestionDAOJdbc;

public class QuestionService {
	public List<QuestionBean> supporterListUnAnsweredQuestions() {
		QuestionDAO dao = new QuestionDAOJdbc();
		return dao.supporterListUnAnsweredQuestions();
	}

	public boolean memberAskQuestion(String member, String title, String qmsg) {
		QuestionDAO dao = new QuestionDAOJdbc();
		return dao.memberAskQuestion(member, title, qmsg);
	}

	public QuestionBean supporterQuestionDetail(int qno) {
		QuestionDAO dao = new QuestionDAOJdbc();
		return dao.supporterQuestionDetail(qno);
	}

	public boolean supporterAnswerQuestion(QuestionBean bean) {
		QuestionDAO dao = new QuestionDAOJdbc();
		return dao.supporterAnswerQuestion(bean);
	}

	public List<QuestionBean> memberListUnAnsweredQuestions(String member) {
		QuestionDAO dao = new QuestionDAOJdbc();
		return dao.memberListUnAnsweredQuestions(member);
	}
	
	public List<QuestionBean> memberListAnsweredQuestions(String member) {
		QuestionDAO dao = new QuestionDAOJdbc();
		return dao.memberListUnAnsweredQuestions(member);
	}
	
	public QuestionBean memberUnansweredQuestionDetail(String member, int qno) {
		QuestionDAO dao = new QuestionDAOJdbc();
		return dao.memberUnansweredQuestionDetail(member, qno);
	}
	
	public QuestionBean memberAnsweredQuestionDetail(String member, int qno) {
		QuestionDAO dao = new QuestionDAOJdbc();
		return dao.memberAnsweredQuestionDetail(member, qno);
	}
}
