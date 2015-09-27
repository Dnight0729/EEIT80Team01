package item.category.model.dao;

import java.sql.Connection;
//import java.sql.DriverManager;
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
import item.category.model.ItemCategoryBean;
import item.category.model.ItemCategoryDAO;

public class ItemCategoryDAOjdbc implements ItemCategoryDAO {
private DataSource ds = null;
	
	//DriverManager用
//	private static final String URL = "jdbc:sqlserver://localhost:1433;database=EEIT80TEAM01";
//	private static final String USER = "sa";
//	private static final String PASSWORD = "sa123456";
	
	//DataSource用
	public ItemCategoryDAOjdbc(){
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String SELECT_BY_ITEMCATEGORY = "SELECT * FROM ITEMCLASS WHERE ITEM_CATEGORY = ?";

	/* (non-Javadoc)
	 * @see item.category.model.dao.ItemCategoryDAO#selectItemCategory(int)
	 */
	@Override
	public ItemCategoryBean selectItemCategory(int itemCategory){
		ItemCategoryBean result=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECT_BY_ITEMCATEGORY);
			stmt.setInt(1, itemCategory);
			rset = stmt.executeQuery();
			if(rset.next()){
				result = new ItemCategoryBean();
				result.setItemCategory(rset.getInt("ITEM_CATEGORY"));
				result.setCategoryName(rset.getString("CATEGORY_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rset!=null){
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	private static final String SELECT_ALL = "SELECT * FROM ITEMCLASS ";

	/* (non-Javadoc)
	 * @see item.category.model.dao.ItemCategoryDAO#selectAll()
	 */
	@Override
	public List<ItemCategoryBean> getAll(){
		List<ItemCategoryBean> result=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECT_ALL);
			rset = stmt.executeQuery();
			result = new ArrayList<ItemCategoryBean>();
			while(rset.next()){
				ItemCategoryBean item = new ItemCategoryBean();
				item.setItemCategory(rset.getInt("ITEM_CATEGORY"));
				item.setCategoryName(rset.getString("CATEGORY_NAME"));
				result.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rset!=null){
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	private static final String INSERT = "INSERT INTO ITEMCLASS(ITEM_CATEGORY, CATEGORY_NAME) VALUES(?,?)";

	/* (non-Javadoc)
	 * @see item.category.model.dao.ItemCategoryDAO#insert(item.category.model.ItemCategoryBean)
	 */
	@Override
	public ItemCategoryBean insert(ItemCategoryBean bean){
		ItemCategoryBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			stmt = conn.prepareStatement(INSERT);
			if(bean!=null){
				stmt.setInt(1, bean.getItemCategory());
				stmt.setString(2, bean.getCategoryName());
				int i = stmt.executeUpdate();
				if(i == 1){
					result = bean;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	private static final String UPDATE ="UPDATE ITEMCLASS SET CATEGORY_NAME=? WHERE ITEM_CATEGORY=?";


	/* (non-Javadoc)
	 * @see item.category.model.dao.ItemCategoryDAO#update(item.category.model.ItemCategoryBean)
	 */
	@Override
	public ItemCategoryBean update(ItemCategoryBean bean){
		ItemCategoryBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, bean.getCategoryName());
			stmt.setInt(2, bean.getItemCategory());
			int i = stmt.executeUpdate();
			if(i == 1){
				result = bean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	private static final String DELETE = "DELETE FROM ITEMCLASS WHERE ITEM_CATEGORY=?";

	/* (non-Javadoc)
	 * @see item.category.model.dao.ItemCategoryDAO#delete(int)
	 */
	@Override
	public boolean delete(int itemCategory){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, itemCategory);
			int i = stmt.executeUpdate();
			if(i == 1){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	

}
