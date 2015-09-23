package item.trade.model.dao;

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

import item.trade.model.TradeBean;
import item.trade.model.TradeDAO;

public class TradeDAOJdbc implements TradeDAO {

	public TradeDAOJdbc(){
		try {
			Context cxt = new InitialContext();
			ds = (DataSource)cxt.lookup("java:comp/env/jdbc/eeit80team01");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private DataSource ds = null;
	private Connection conn = null;

	
	private String getAll = "select * from trade";
	@Override
	public List<TradeBean> getAll() {
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		List<TradeBean> beans = null;
		TradeBean bean = null;
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(getAll);
			rs = ptmt.executeQuery();
			beans = new ArrayList<TradeBean>();
			while(rs.next()){
				bean = new TradeBean();
				bean.setItemId(rs.getInt(1));
				bean.setSeller(rs.getString(2));
				bean.setSellerCheck(rs.getInt(3));
				bean.setBuyer(rs.getString(4));
				bean.setBuyerCheck(rs.getInt(5));
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

	private String getByPK ="select * from trade where item_id=?";
	@Override
	public TradeBean getByPK(int itemId) {
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		TradeBean bean = null;
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(getByPK);
			ptmt.setInt(1,itemId);
			rs = ptmt.executeQuery();
			if(rs.next()){
				bean = new TradeBean();
				bean.setItemId(rs.getInt(1));
				bean.setSeller(rs.getString(2));
				bean.setSellerCheck(rs.getInt(3));
				bean.setBuyer(rs.getString(4));
				bean.setBuyerCheck(rs.getInt(5));
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
		return bean;
	}


	private String getBySeller = "select * from trade where seller=?";
	@Override
	public List<TradeBean> getBySeller(String seller) {
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		List<TradeBean> beans = null;
		TradeBean bean = null;
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(getBySeller);
			ptmt.setString(1,seller);
			beans = new ArrayList<TradeBean>();
			rs = ptmt.executeQuery();
			while(rs.next()){
				bean = new TradeBean();
				bean.setItemId(rs.getInt(1));
				bean.setSeller(rs.getString(2));
				bean.setSellerCheck(rs.getInt(3));
				bean.setBuyer(rs.getString(4));
				bean.setBuyerCheck(rs.getInt(5));
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

	private String getByBuyer = "select * from trade where buyer=?";
	@Override
	public List<TradeBean> getByBuyer(String buyer) {
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		List<TradeBean> beans = null;
		TradeBean bean = null;
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(getByBuyer);
			ptmt.setString(1,buyer);
			beans = new ArrayList<TradeBean>();
			rs = ptmt.executeQuery();
			while(rs.next()){
				bean = new TradeBean();
				bean.setItemId(rs.getInt(1));
				bean.setSeller(rs.getString(2));
				bean.setSellerCheck(rs.getInt(3));
				bean.setBuyer(rs.getString(4));
				bean.setBuyerCheck(rs.getInt(5));
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

	private String insert =
			"insert into trade(item_id,seller,seller_check,buyer,buyer_check)"
			+ "values(?,?,?,?,?)";
	@Override
	public int insert(TradeBean bean) {
		PreparedStatement ptmt = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(insert);
			ptmt.setInt(1,bean.getItemId());
			ptmt.setString(2,bean.getSeller());
			ptmt.setInt(3,bean.getSellerCheck());
			ptmt.setString(4,bean.getBuyer());
			ptmt.setInt(5,bean.getBuyerCheck());
			result = ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
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
		return result;
	}

	private String update = "update trade set seller_check=?,buyer_check=? "
			+ "where item_id=? and seller=? and buyer=?";
	@Override
	public int update(TradeBean bean) {
		PreparedStatement ptmt = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(update);
			ptmt.setInt(1,bean.getSellerCheck());
			ptmt.setInt(2,bean.getBuyerCheck());
			ptmt.setInt(3,bean.getItemId());
			ptmt.setString(4,bean.getSeller());
			ptmt.setString(5,bean.getBuyer());
			result = ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
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
		return result;
	}

	private String delete = 
			"delete from trade where item_id=? and buyer=? and seller=?";
	@Override
	public int delete(int itemId) {
		PreparedStatement ptmt = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(delete);
			ptmt.setInt(1,itemId);
			result = ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
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
		return result;
	}

}
