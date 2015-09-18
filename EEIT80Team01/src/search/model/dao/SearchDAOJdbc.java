package search.model.dao;

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

import items.model.ItemsBean;
import search.model.SearchDAO;

public class SearchDAOJdbc implements SearchDAO {
	
	private DataSource ds = null;
	private Connection conn = null;
	private PreparedStatement ptmt = null;
	private ResultSet rs = null;
	private List<ItemsBean> beans = null;
	private ItemsBean bean = null;
	public SearchDAOJdbc(){
		try {
			Context cxt = new InitialContext();
			ds = (DataSource)cxt.lookup("java:comp/env/jdbc/eeit80team01");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private String getItemsByKeyword = "select * from ITEMS where TITLE like ?";
	/* (non-Javadoc)
	 * @see search.model.dao.SearchDAO#getItemsByKeyword(java.lang.String)
	 */
	@Override
	public List<ItemsBean> getItemsByKeyword(String keyword){
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(getItemsByKeyword);
			ptmt.setString(1,"%"+keyword+"%");
			rs = ptmt.executeQuery();
			beans = new ArrayList<ItemsBean>();
			while(rs.next()){
				bean = new ItemsBean();
				bean.setItemId(rs.getInt(1));
				bean.setSeller(rs.getString(2));
				bean.setItemCategory(rs.getInt(3));
				bean.setTitle(rs.getString(4));
				bean.setStartPrice(rs.getDouble(5));
				bean.setDirectPrice(rs.getDouble(6));
				bean.setBid(rs.getInt(7));
				bean.setEndTime(rs.getTimestamp(8));
				bean.setItemDescribe(rs.getString(9));
				bean.setItemStatus(rs.getInt(10));
				bean.setThreadLock(rs.getInt(11));
				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ptmt!=null){
				try {
					ptmt.close();
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
		return beans;
	}
	
	private String getItemsWithOption = "select * from ITEMS where ITEM_CATEGORY = ? and  TITLE like ? ";
	/* (non-Javadoc)
	 * @see search.model.dao.SearchDAO#getItemsWithOption(int, java.lang.String)
	 */
	@Override
	public List<ItemsBean> getItemsWithOption(int option,String keyword){
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(getItemsWithOption);
			ptmt.setInt(1,option);
			ptmt.setString(2,"%"+keyword+"%");
			rs = ptmt.executeQuery();
			beans = new ArrayList<ItemsBean>();
			while(rs.next()){
				bean = new ItemsBean();
				bean.setItemId(rs.getInt(1));
				bean.setSeller(rs.getString(2));
				bean.setItemCategory(rs.getInt(3));
				bean.setTitle(rs.getString(4));
				bean.setStartPrice(rs.getDouble(5));
				bean.setDirectPrice(rs.getDouble(6));
				bean.setBid(rs.getInt(7));
				bean.setEndTime(rs.getTimestamp(8));
				bean.setItemDescribe(rs.getString(9));
				bean.setItemStatus(rs.getInt(10));
				bean.setThreadLock(rs.getInt(11));
				beans.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ptmt!=null){
				try {
					ptmt.close();
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
		return beans;
	}
	
	
	
	
	
	
	
	
}
