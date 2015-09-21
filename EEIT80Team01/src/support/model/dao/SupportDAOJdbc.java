package support.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import global.GlobalService;
import support.model.SupportBean;
import support.model.SupportDAO;

public class SupportDAOJdbc implements SupportDAO {
	private DataSource ds = null;

	public SupportDAOJdbc() {
		Context context = null;
		try {
			context = new InitialContext();
			ds = (DataSource) context.lookup(GlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_BY_SUPPORTERNAME = "SELECT SUPPORTERNAME,PASSWD,EMPLOYEE_ID,FIRST_NAME,LAST_NAME FROM SUPPORTERS WHERE SUPPORTERNAME=?";

	@Override
	public SupportBean select(String supportername) {
		SupportBean result = null;
		ResultSet rs = null;
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_SUPPORTERNAME);) {
			pstmt.setString(1, supportername);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new SupportBean();
				result.setSupportername(rs.getString("SUPPORTERNAME"));
				result.setPassword(rs.getString("PASSWD"));
				result.setEmployeeid(rs.getString("EMPLOYEE_ID"));
				result.setFirstname(rs.getString("FIRST_NAME"));
				result.setLastname(rs.getString("LAST_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private static final String SELECT_ALL_SUPPORTER = "SELECT SUPPORTERNAME,PASSWD,EMPLOYEE_ID,FIRST_NAME,LAST_NAME FROM SUPPORTERS";

	@Override
	public List<SupportBean> select() {
		List<SupportBean> beans = null;
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_SUPPORTER);
				ResultSet rs = pstmt.executeQuery();) {
			beans = new ArrayList<SupportBean>();
			while (rs.next()) {
				SupportBean bean = new SupportBean();
				bean.setSupportername(rs.getString("SUPPORTERNAME"));
				bean.setPassword(rs.getString("PASSWD"));
				bean.setEmployeeid(rs.getString("EMPLOYEE_ID"));
				bean.setFirstname(rs.getString("FIRST_NAME"));
				bean.setLastname(rs.getString("LAST_NAME"));
				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return beans;
	}

	// 模糊搜尋(依照客服名稱)
	private static final String SELECT_LIKE_SUPPORTERNAME = "SELECT SUPPORTERNAME,PASSWD,EMPLOYEE_ID,FIRST_NAME,LAST_NAME FROM SUPPORTERS WHERE SUPPORTERNAME LIKE ?";

	@Override
	public List<SupportBean> selectSupporterNameLike(String supportername) {
		List<SupportBean> beans = null;
		ResultSet rs = null;
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_LIKE_SUPPORTERNAME);) {
			pstmt.setString(1, "%" + supportername + "%");
			rs = pstmt.executeQuery();
			beans = new ArrayList<SupportBean>();
			while (rs.next()) {
				SupportBean bean = new SupportBean();
				bean.setSupportername(rs.getString("SUPPORTERNAME"));
				bean.setPassword(rs.getString("PASSWD"));
				bean.setEmployeeid(rs.getString("EMPLOYEE_ID"));
				bean.setFirstname(rs.getString("FIRST_NAME"));
				bean.setLastname(rs.getString("LAST_NAME"));
				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return beans;
	}

	// 模糊搜尋(依照客服員工編號)
	private static final String SELECT_LIKE_EMPLOYEE_ID = "SELECT SUPPORTERNAME,PASSWD,EMPLOYEE_ID,FIRST_NAME,LAST_NAME FROM SUPPORTERS WHERE EMPLOYEE_ID LIKE ?";

	@Override
	public List<SupportBean> selectEmployeeIDLike(String employeeid) {
		List<SupportBean> beans = null;
		ResultSet rs = null;
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_LIKE_EMPLOYEE_ID);) {
			pstmt.setString(1, "%" + employeeid + "%");
			rs = pstmt.executeQuery();
			beans = new ArrayList<SupportBean>();
			while (rs.next()) {
				SupportBean bean = new SupportBean();
				bean.setSupportername(rs.getString("SUPPORTERNAME"));
				bean.setPassword(rs.getString("PASSWD"));
				bean.setEmployeeid(rs.getString("EMPLOYEE_ID"));
				bean.setFirstname(rs.getString("FIRST_NAME"));
				bean.setLastname(rs.getString("LAST_NAME"));
				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return beans;
	}

	// 模糊搜尋(依照客服LAST NAME)
	private static final String SELECT_LIKE_LAST_NAME = "SELECT SUPPORTERNAME,PASSWD,EMPLOYEE_ID,FIRST_NAME,LAST_NAME FROM SUPPORTERS WHERE LAST_NAME LIKE ?";

	@Override
	public List<SupportBean> selectLastNameLike(String lastname) {
		List<SupportBean> beans = null;
		ResultSet rs = null;
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_LIKE_LAST_NAME);) {
			pstmt.setString(1, "%" + lastname + "%");
			rs = pstmt.executeQuery();
			beans = new ArrayList<SupportBean>();
			while (rs.next()) {
				SupportBean bean = new SupportBean();
				bean.setSupportername(rs.getString("SUPPORTERNAME"));
				bean.setPassword(rs.getString("PASSWD"));
				bean.setEmployeeid(rs.getString("EMPLOYEE_ID"));
				bean.setFirstname(rs.getString("FIRST_NAME"));
				bean.setLastname(rs.getString("LAST_NAME"));
				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return beans;
	}

	// 模糊搜尋(依照客服FIRST NAME)
	private static final String SELECT_LIKE_FIRST_NAME = "SELECT SUPPORTERNAME,PASSWD,EMPLOYEE_ID,FIRST_NAME,LAST_NAME FROM SUPPORTERS WHERE FIRST_NAME LIKE ?";

	@Override
	public List<SupportBean> selectFirstNameLike(String firstname) {
		List<SupportBean> beans = null;
		ResultSet rs = null;
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_LIKE_FIRST_NAME);) {
			pstmt.setString(1, "%" + firstname + "%");
			rs = pstmt.executeQuery();
			beans = new ArrayList<SupportBean>();
			while (rs.next()) {
				SupportBean bean = new SupportBean();
				bean.setSupportername(rs.getString("SUPPORTERNAME"));
				bean.setPassword(rs.getString("PASSWD"));
				bean.setEmployeeid(rs.getString("EMPLOYEE_ID"));
				bean.setFirstname(rs.getString("FIRST_NAME"));
				bean.setLastname(rs.getString("LAST_NAME"));
				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return beans;
	}

	// 模糊搜尋(依照客服全名 FIRST + LAST NAME)
	private static final String SELECT_LIKE_FULL_NAME = "SELECT SUPPORTERNAME,PASSWD,EMPLOYEE_ID,FIRST_NAME,LAST_NAME FROM SUPPORTERS WHERE FIRST_NAME LIKE ? AND LAST_NAME LIKE ?";

	@Override
	public List<SupportBean> selectFirstNameLike(String firstname, String lastname) {
		List<SupportBean> beans = null;
		ResultSet rs = null;
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_LIKE_FULL_NAME);) {
			pstmt.setString(1, "%" + firstname + "%");
			pstmt.setString(2, "%" + lastname + "%");
			rs = pstmt.executeQuery();
			beans = new ArrayList<SupportBean>();
			while (rs.next()) {
				SupportBean bean = new SupportBean();
				bean.setSupportername(rs.getString("SUPPORTERNAME"));
				bean.setPassword(rs.getString("PASSWD"));
				bean.setEmployeeid(rs.getString("EMPLOYEE_ID"));
				bean.setFirstname(rs.getString("FIRST_NAME"));
				bean.setLastname(rs.getString("LAST_NAME"));
				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return beans;
	}

	private static final String INSERT_SUPPORTER = "INSERT INTO SUPPORTERS (SUPPORTERNAME,PASSWD,EMPLOYEE_ID,FIRST_NAME,LAST_NAME) VALUES (?,?,?,?,?)";

	@Override
	public SupportBean insert(SupportBean bean) {
		SupportBean result = null;
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_SUPPORTER);) {
			if (bean != null) {
				pstmt.setString(1, bean.getSupportername());
				pstmt.setString(2, bean.getPassword());
				pstmt.setString(3, bean.getEmployeeid());
				pstmt.setString(4, bean.getFirstname());
				pstmt.setString(5, bean.getLastname());
			}
			int i = pstmt.executeUpdate();
			if (i == 1) {
				result = bean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String MODIFY_SUPPORTER = "UPDATE SUPPORTERS SET SUPPORTERNAME=?,PASSWD=?,EMPLOYEE_ID=?,FIRST_NAME=?,LAST_NAME=? WHERE SUPPORTERNAME=?";

	@Override
	public boolean modify(SupportBean bean, String oldSupporterName) {
		try (
				Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(MODIFY_SUPPORTER);
				) {
			pstmt.setString(1, bean.getSupportername());
			pstmt.setString(2, bean.getPassword());
			pstmt.setString(3, bean.getEmployeeid());
			pstmt.setString(4, bean.getFirstname());
			pstmt.setString(5, bean.getLastname());
			pstmt.setString(6, oldSupporterName);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	private static final String UPDATE_SUPPORTER = "UPDATE SUPPORTERS SET PASSWD=?,EMPLOYEE_ID=?,FIRST_NAME=?,LAST_NAME=? WHERE SUPPORTERNAME=?";

	@Override
	public SupportBean update(SupportBean bean) {
		SupportBean result = null;
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_SUPPORTER);) {
			pstmt.setString(1, bean.getPassword());
			pstmt.setString(2, bean.getEmployeeid());
			pstmt.setString(3, bean.getFirstname());
			pstmt.setString(4, bean.getLastname());
			pstmt.setString(5, bean.getSupportername());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				result = bean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String DELETE_SUPPORTER = "DELETE FROM SUPPORTERS WHERE SUPPORTERNAME=?";

	@Override
	public boolean delete(String supportername) {
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(DELETE_SUPPORTER);) {
			pstmt.setString(1, supportername);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static final String DELETE_SUPPORTER_BY_EMPLOYEEID = "DELETE FROM SUPPORTERS WHERE EMPLOYEE_ID=?";

	@Override
	public boolean deleteByEmployeeID(String employeeid) {
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(DELETE_SUPPORTER_BY_EMPLOYEEID);) {
			pstmt.setString(1, employeeid);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static final String SELECT_BY_EMPLOYEEID = "SELECT SUPPORTERNAME,PASSWD,EMPLOYEE_ID,FIRST_NAME,LAST_NAME FROM SUPPORTERS WHERE EMPLOYEE_ID=?";

	@Override
	public SupportBean selectByEmployeeID(String employeeid) {
		SupportBean result = null;
		ResultSet rs = null;
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_EMPLOYEEID);) {
			pstmt.setString(1, employeeid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new SupportBean();
				result.setSupportername(rs.getString("SUPPORTERNAME"));
				result.setPassword(rs.getString("PASSWD"));
				result.setEmployeeid(rs.getString("EMPLOYEE_ID"));
				result.setFirstname(rs.getString("FIRST_NAME"));
				result.setLastname(rs.getString("LAST_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int delete(List<SupportBean> supporterlist) {
		int counter = 0;

		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(DELETE_SUPPORTER);) {
			connection.setAutoCommit(false);
			for (SupportBean sb : supporterlist) {
				pstmt.setString(1, sb.getSupportername());
				pstmt.executeQuery();
				counter++;
				if (counter % 100 == 0 || counter == supporterlist.size()) {
					connection.commit();
				}
			}
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}

}
