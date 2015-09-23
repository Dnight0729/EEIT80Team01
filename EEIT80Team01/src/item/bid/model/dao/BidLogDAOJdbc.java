package item.bid.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import item.bid.model.BidLogBean;
import item.bid.model.BidLogDAO;

public class BidLogDAOJdbc implements BidLogDAO {


	public BidLogDAOJdbc(){
		try {
			Context cxt  = new InitialContext();
			ds = (DataSource)cxt.lookup("java:comp/env/jdbc/eeit80team01");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private DataSource ds = null;
	private Connection conn = null;
	
	private String getTopPrice = "select top 1 * from bidlog where item_id=? order by bid_price desc";
	@Override
	public BidLogBean getTopPrice(int itemId){
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		BidLogBean bean = null;
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(getTopPrice);
			ptmt.setInt(1,itemId);
			rs = ptmt.executeQuery();
			if(rs.next()){
				bean = new BidLogBean();
				bean.setItemId(rs.getInt(1));
				bean.setBuyer(rs.getString(2));
				bean.setBidPrice(rs.getDouble(3));
				bean.setBidTime(rs.getTimestamp(4));
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
//	private String getTopTime = "select top 1 * from bidlog order by bid_time desc";
//	@Override
//	public BidLogBean getTopTime(){
//		try {
//			conn = ds.getConnection();
//			ptmt = conn.prepareStatement(getTopTime);
//			rs = ptmt.executeQuery();
//			if(rs.next()){
//				bean = new BidLogBean();
//				bean.setItemId(rs.getInt(1));
//				bean.setBuyer(rs.getString(2));
//				bean.setBidPrice(rs.getDouble(3));
//				bean.setBidTime(rs.getDate(4));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally{
//			if(rs!=null){
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if(ptmt!=null){
//				try {
//					ptmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if(conn!=null){
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return bean;
//	}
	
	private String getAll = "select * from bidlog";
	@Override
	public List<BidLogBean> getAll() {
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		List<BidLogBean> beans = null;
		BidLogBean bean = null;
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(getAll);
			rs = ptmt.executeQuery();
			beans = new ArrayList<BidLogBean>();
			while(rs.next()){
				bean = new BidLogBean();
				bean.setItemId(rs.getInt(1));
				bean.setBuyer(rs.getString(2));
				bean.setBidPrice(rs.getDouble(3));
				bean.setBidTime(rs.getTimestamp(4));
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

	private String getByPK ="select * from bidlog where item_id=? buyer=?";
	@Override
	public BidLogBean getByPK(int itemId, String buyer) {
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		BidLogBean bean = null;
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(getByPK);
			ptmt.setInt(1,itemId);
			ptmt.setString(2,buyer);
			rs = ptmt.executeQuery();
			if(rs.next()){
				bean = new BidLogBean();
				bean.setItemId(rs.getInt(1));
				bean.setBuyer(rs.getString(2));
				bean.setBidPrice(rs.getDouble(3));
				bean.setBidTime(rs.getTimestamp(4));
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

	private String getByItem = "select * from bidlog where item_id=?";
	@Override
	public List<BidLogBean> getByItem(int itemId) {
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		List<BidLogBean> beans = null;
		BidLogBean bean = null;
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(getByItem);
			ptmt.setInt(1,itemId);
			rs = ptmt.executeQuery();
			beans = new ArrayList<BidLogBean>();
			while(rs.next()){
				bean = new BidLogBean();
				bean.setItemId(rs.getInt(1));
				bean.setBuyer(rs.getString(2));
				bean.setBidPrice(rs.getDouble(3));
				bean.setBidTime(rs.getTimestamp(4));
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

	private String getByBidPrice = "select * from bidlog where bid_price=?";
	@Override
	public List<BidLogBean> getByBidPrice(double bidPrice) {
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		List<BidLogBean> beans = null;
		BidLogBean bean = null;
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(getByBidPrice);
			ptmt.setDouble(1,bidPrice);
			rs = ptmt.executeQuery();
			beans = new ArrayList<BidLogBean>();
			while(rs.next()){
				bean = new BidLogBean();
				bean.setItemId(rs.getInt(1));
				bean.setBuyer(rs.getString(2));
				bean.setBidPrice(rs.getDouble(3));
				bean.setBidTime(rs.getTimestamp(4));
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
	
	private String getByBidTime = "select * from bidlog where bid_time=?";
	@Override
	public List<BidLogBean> getByBidTime(Timestamp bidTime) {
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		List<BidLogBean> beans = null;
		BidLogBean bean = null;
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(getByBidTime);
			ptmt.setTimestamp(1,bidTime);
			rs = ptmt.executeQuery();
			beans = new ArrayList<BidLogBean>();
			while(rs.next()){
				bean = new BidLogBean();
				bean.setItemId(rs.getInt(1));
				bean.setBuyer(rs.getString(2));
				bean.setBidPrice(rs.getDouble(3));
				bean.setBidTime(rs.getTimestamp(4));
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

	private String getByBuyer = "select * from bidlog where buyer=?";
	@Override
	public List<BidLogBean> getByBuyer(String buyer) {
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		List<BidLogBean> beans = null;
		BidLogBean bean = null;
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(getByBuyer);
			ptmt.setString(1,buyer);
			rs = ptmt.executeQuery();
			beans = new ArrayList<BidLogBean>();
			while(rs.next()){
				bean = new BidLogBean();
				bean.setItemId(rs.getInt(1));
				bean.setBuyer(rs.getString(2));
				bean.setBidPrice(rs.getDouble(3));
				bean.setBidTime(rs.getTimestamp(4));
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
			"insert into bidlog(item_id,buyer,bid_price,bid_time)"
			+ "values(?,?,?,?)";
	@Override
	public int insert(BidLogBean bean) {
		int result = 0;
		PreparedStatement ptmt = null;
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(insert);
			ptmt.setInt(1,bean.getItemId());
			ptmt.setString(2,bean.getBuyer());
			ptmt.setDouble(3,bean.getBidPrice());
			ptmt.setTimestamp(4,bean.getBidTime());
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

	private String update = "update bidlog set bid_price=?,bid_time=? "
			+ "where item_id=? and buyer=?";
	@Override
	public int update(BidLogBean bean) {
		PreparedStatement ptmt = null;
		int result = 0;
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(update);
			ptmt.setDouble(1,bean.getBidPrice());
			ptmt.setTimestamp(2,bean.getBidTime());
			ptmt.setInt(3,bean.getItemId());
			ptmt.setString(4,bean.getBuyer());
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
			"delete from bidlog where item_id=? and buyer=?";
	@Override
	public int delete(int itemId, String buyer) {
		int result = 0;
		PreparedStatement ptmt = null;
		try {
			conn = ds.getConnection();
			ptmt = conn.prepareStatement(delete);
			ptmt.setInt(1,itemId);
			ptmt.setString(2,buyer);
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
