package question.model;

import java.util.List;

import question.model.QuestionBean;

public interface QuestionDAO {

	List<QuestionBean> supporterListUnAnsweredQuestions();

	boolean supporterAnswerQuestion(QuestionBean bean);

	QuestionBean supporterQuestionDetail(int qno);

	boolean memberAskQuestion(String member, String title, String qmsg);

	List<QuestionBean> memberListAnsweredQuestions(String member);

	List<QuestionBean> memberListUnAnsweredQuestions(String member);

	QuestionBean memberUnansweredQuestionDetail(String member, int qno);

	QuestionBean memberAnsweredQuestionDetail(String member, int qno);

}