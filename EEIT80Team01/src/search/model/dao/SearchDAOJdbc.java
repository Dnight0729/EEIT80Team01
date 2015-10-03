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

import global.GlobalService;
import items.model.ItemsBean;
import search.model.SearchDAO;

public class SearchDAOJdbc implements SearchDAO {
	
	private DataSource ds = null;
	private Connection conn = null;

	public SearchDAOJdbc(){
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private String getItemsByKeyword = "select * from ITEMS where TITLE like ? and ITEM_Status!=3 order by ITEM_ID desc";
	/* (non-Javadoc)
	 * @see search.model.dao.SearchDAO#getItemsByKeyword(java.lang.String)
	 */
	@Override
	public List<ItemsBean> getItemsByKeyword(String keyword){
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		List<ItemsBean> beans = null;
		ItemsBean bean = null;
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
	
	private String getItemsWithOption = "select * from ITEMS where ITEM_CATEGORY = ? and  TITLE like ? ITEM_Status!=3 order by ITEM_ID desc";
	/* (non-Javadoc)
	 * @see search.model.dao.SearchDAO#getItemsWithOption(int, java.lang.String)
	 */
	@Override
	public List<ItemsBean> getItemsWithOption(int option,String keyword){
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		List<ItemsBean> beans = null;
		ItemsBean bean = null;
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
