package question.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import global.GlobalService;
import question.model.QuestionBean;
import question.model.QuestionDAO;

public class QuestionDAOJdbc implements QuestionDAO {
	private DataSource ds = null;

	public QuestionDAOJdbc() {
		Context context = null;
		try {
			context = new InitialContext();
			ds = (DataSource) context.lookup(GlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/* SUPPOTER AREA */
	// supporter list unanswered questions
	private static final String UNANSWERED_QUESTIONS = "SELECT QNO,MEMBER,TITLE,QMSG,QT FROM QUESTIONS WHERE SUPPORTER IS NULL";

	/* (non-Javadoc)
	 * @see question.model.dao.QuestionDAO#supporterListUnAnsweredQuestions()
	 */
	@Override
	public List<QuestionBean> supporterListUnAnsweredQuestions() {
		List<QuestionBean> qbl = null;
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UNANSWERED_QUESTIONS);) {
			ResultSet rs = pstmt.executeQuery();
			qbl = new ArrayList<QuestionBean>();
			while (rs.next()) {
				QuestionBean qb = new QuestionBean();
				qb.setQno(rs.getInt("QNO"));
				qb.setMember(rs.getString("MEMBER"));
				qb.setTitle(rs.getString("TITLE"));
				qb.setQmsg(rs.getString("QMSG"));
				qb.setQt(rs.getLong("QT"));
				qbl.add(qb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qbl;
	}

	// supporter answer question
	private static final String SUPPORT_ANSWER_QUESTION = "UPDATE QUESTIONS SET SUPPORTER=?,AMSG=?,AT=? WHERE QNO=?";

	/* (non-Javadoc)
	 * @see question.model.dao.QuestionDAO#supporterAnswerQuestion(int, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean supporterAnswerQuestion(int qno, String supporter, String amsg) {
		boolean result = false;
		long at = new Date().getTime();
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SUPPORT_ANSWER_QUESTION);) {
			pstmt.setString(1, supporter);
			pstmt.setString(2, amsg);
			pstmt.setLong(3, at);
			pstmt.setInt(4, qno);
			result = pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// supporter watch question detail
	private static final String QUESTION_DETAIL = "SELECT QNO,MEMBER,TITLE,QMSG,QT FROM QUESTIONS WHERE QNO=?";

	/* (non-Javadoc)
	 * @see question.model.dao.QuestionDAO#supporterQuestionDetail(int)
	 */
	@Override
	public QuestionBean supporterQuestionDetail(int qno) {
		QuestionBean result = null;
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(QUESTION_DETAIL);) {
			pstmt.setInt(1, qno);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new QuestionBean();
				result.setQno(rs.getInt("QNO"));
				result.setMember(rs.getString("MEMBER"));
				result.setTitle(rs.getString("TITLE"));
				result.setQmsg(rs.getString("QMSG"));
				result.setQt(rs.getLong("QT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/* SUPPORTER AREA */

	/* MEMBER AREA */
	// member ask question
	private static final String MEMBER_NEW_QUESTION = "INSERT INTO QUESTIONS SET MEMBER=?,TITLE=?,QMSG=?,QT=?";

	/* (non-Javadoc)
	 * @see question.model.dao.QuestionDAO#memberAskQuestion(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean memberAskQuestion(String member, String title, String qmsg) {
		boolean result = false;
		long qt = new Date().getTime();
		try {
			Connection connection = ds.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(MEMBER_NEW_QUESTION);
			pstmt.setString(1, member);
			pstmt.setString(2, title);
			pstmt.setString(3, qmsg);
			pstmt.setLong(4, qt);
			result = pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// member list answered questions
	private static final String MEMBER_LIST_ANSWERED_QUESTIONS = "SELECT QNO,MEMBER,TITLE,QMSG,QT,SUPPORTER,AMSG,AT FROM QUESTIONS WHERE MEMBER=? AND SUPPORTER IS NOT NULL AND ORDER BY QNO DESC";

	/* (non-Javadoc)
	 * @see question.model.dao.QuestionDAO#memberListAnsweredQuestions(java.lang.String)
	 */
	@Override
	public List<QuestionBean> memberListAnsweredQuestions(String member) {
		List<QuestionBean> qbl = new ArrayList<QuestionBean>();
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(MEMBER_LIST_ANSWERED_QUESTIONS);) {
			pstmt.setString(1, member);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				QuestionBean qb = new QuestionBean();
				qb.setQno(rs.getInt("QNO"));
				qb.setMember(rs.getString("MEMBER"));
				qb.setTitle(rs.getString("TITLE"));
				qb.setQmsg(rs.getString("QMSG"));
				qb.setQt(rs.getLong("QT"));
				qb.setSupporter(rs.getString("SUPPORTER"));
				qb.setAmsg(rs.getString("AMSG"));
				qb.setAt(rs.getLong("AT"));
				qbl.add(qb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qbl;
	}

	// member list unanswered questions
	private static final String MEMBER_LIST_UNANSWERED_QUESTIONS = "SELECT QNO,MEMBER,TITLE,QMSG,QT FROM QUESTIONS WHERE MEMBER=? AND SUPPORTER IS NULL AND ORDER BY QNO DESC";

	/* (non-Javadoc)
	 * @see question.model.dao.QuestionDAO#memberListUnAnsweredQuestions(java.lang.String)
	 */
	@Override
	public List<QuestionBean> memberListUnAnsweredQuestions(String member) {
		List<QuestionBean> qbl = new ArrayList<QuestionBean>();
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(MEMBER_LIST_UNANSWERED_QUESTIONS);) {
			pstmt.setString(1, member);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				QuestionBean qb = new QuestionBean();
				qb.setQno(rs.getInt("QNO"));
				qb.setMember(rs.getString("MEMBER"));
				qb.setTitle(rs.getString("TITLE"));
				qb.setQmsg(rs.getString("QMSG"));
				qb.setQt(rs.getLong("QT"));
				qbl.add(qb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qbl;
	}

	// member watch unanswered question detail
	private static final String MEMBER_WATCH_UNANSWERED_QUESTION_DETAIL = "SELECT QNO,MEMBER,TITLE,QMSG,QT FROM QUESTIONS WHERE MEMBER=? AND QNO=? AND SUPPORTER IS NULL AND ORDER BY QNO DESC";

	/* (non-Javadoc)
	 * @see question.model.dao.QuestionDAO#memberUnansweredQuestionDetail(java.lang.String, int)
	 */
	@Override
	public QuestionBean memberUnansweredQuestionDetail(String member, int qno) {
		QuestionBean result = null;
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(MEMBER_WATCH_UNANSWERED_QUESTION_DETAIL);) {
			pstmt.setInt(1, qno);
			pstmt.setString(2, member);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new QuestionBean();
				result.setQno(rs.getInt("QNO"));
				result.setMember(rs.getString("MEMBER"));
				result.setTitle(rs.getString("TITLE"));
				result.setQmsg(rs.getString("QMSG"));
				result.setQt(rs.getLong("QT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// member watch answered question detail
	private static final String MEMBER_WATCH_ANSWERED_QUESTION_DETAIL = "SELECT QNO,MEMBER,TITLE,QMSG,QT,SUPPORTER,AMSG,AT FROM QUESTIONS WHERE MEMBER=? AND QNO=? AND SUPPORTER IS NOT NULL AND ORDER BY QNO DESC";

	/* (non-Javadoc)
	 * @see question.model.dao.QuestionDAO#memberAnsweredQuestionDetail(java.lang.String, int)
	 */
	@Override
	public QuestionBean memberAnsweredQuestionDetail(String member, int qno) {
		QuestionBean result = null;
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(MEMBER_WATCH_ANSWERED_QUESTION_DETAIL);) {
			pstmt.setInt(1, qno);
			pstmt.setString(2, member);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new QuestionBean();
				result.setQno(rs.getInt("QNO"));
				result.setMember(rs.getString("MEMBER"));
				result.setTitle(rs.getString("TITLE"));
				result.setQmsg(rs.getString("QMSG"));
				result.setQt(rs.getLong("QT"));
				result.setSupporter(rs.getString("SUPPORTER"));
				result.setAmsg(rs.getString("AMSG"));
				result.setAt(rs.getLong("AT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
