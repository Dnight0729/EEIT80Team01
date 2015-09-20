package items.model.dao;


import global.GlobalService;
import items.model.ImageInput;
import items.model.ItemsBean;
import items.model.ItemsDAO;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ItemsDAOjdbc implements ItemsDAO{
	private DataSource ds = null;
	
	//DriverManager用
//	private static final String URL = "jdbc:sqlserver://localhost:1433;database=EEIT80TEAM01";
//	private static final String USER = "sa";
//	private static final String PASSWORD = "sa123456";
	
	//DataSource用
	public ItemsDAOjdbc(){
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String SELECT_BY_ID = "SELECT * FROM ITEMS WHERE ITEM_ID = ?";

	/* (non-Javadoc)
	 * @see items.model.dao.ItemsDAO#selectId(int)
	 */
	@Override
	public ItemsBean selectId(int itemId){
		ItemsBean result=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECT_BY_ID);
			stmt.setInt(1, itemId);
			rset = stmt.executeQuery();
			if(rset.next()){
				result = new ItemsBean();
				result.setItemId(rset.getInt("ITEM_ID"));
				result.setSeller(rset.getString("SELLER"));
				result.setItemCategory(rset.getInt("ITEM_CATEGORY"));
				result.setTitle(rset.getString("TITLE"));
				result.setStartPrice(rset.getDouble("START_PRICE"));
				result.setDirectPrice(rset.getDouble("DIRECT_PRICE"));
				result.setBid(rset.getInt("BID"));
				result.setEndTime(rset.getTimestamp("END_TIME"));
				result.setItemDescribe(rset.getString("ITEM_DESCRIBE"));
				result.setItemStatus(rset.getInt("ITEM_STATUS"));
				result.setThreadLock(rset.getInt("THREAD_LOCK"));
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
	
	private static final String SELECT_BY_CATEGORY = "SELECT * FROM ITEMS WHERE ITEM_CATEGORY = ?";

	/* (non-Javadoc)
	 * @see items.model.dao.ItemsDAO#selectCategory(int)
	 */
	@Override
	public ItemsBean selectCategory(int itemCategory){
		ItemsBean result=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECT_BY_CATEGORY);
			stmt.setInt(1, itemCategory);
			rset = stmt.executeQuery();
			if(rset.next()){
				result = new ItemsBean();
				result.setItemId(rset.getInt("ITEM_ID"));
				result.setSeller(rset.getString("SELLER"));
				result.setItemCategory(rset.getInt("ITEM_CATEGORY"));
				result.setTitle(rset.getString("TITLE"));
				result.setStartPrice(rset.getDouble("START_PRICE"));
				result.setDirectPrice(rset.getDouble("DIRECT_PRICE"));
				result.setBid(rset.getInt("BID"));
				result.setEndTime(rset.getTimestamp("END_TIME"));
				result.setItemDescribe(rset.getString("ITEM_DESCRIBE"));
				result.setItemStatus(rset.getInt("ITEM_STATUS"));
				result.setThreadLock(rset.getInt("THREAD_LOCK"));
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
	
	private static final String SELECT_ALL = "SELECT * FROM ITEMS ";

	/* (non-Javadoc)
	 * @see items.model.dao.ItemsDAO#selectAll()
	 */
	@Override
	public List<ItemsBean> getAll(){
		List<ItemsBean> result=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECT_ALL);
			rset = stmt.executeQuery();
			result = new ArrayList<ItemsBean>();
			while(rset.next()){
				ItemsBean item = new ItemsBean();
				item.setItemId(rset.getInt("ITEM_ID"));
				item.setSeller(rset.getString("SELLER"));
				item.setItemCategory(rset.getInt("ITEM_CATEGORY"));
				item.setTitle(rset.getString("TITLE"));
				item.setStartPrice(rset.getDouble("START_PRICE"));
				item.setDirectPrice(rset.getDouble("DIRECT_PRICE"));
				item.setBid(rset.getInt("BID"));
				item.setEndTime(rset.getTimestamp("END_TIME"));
				item.setItemDescribe(rset.getString("ITEM_DESCRIBE"));
				item.setItemStatus(rset.getInt("ITEM_STATUS"));
				item.setThreadLock(rset.getInt("THREAD_LOCK"));
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
	
	private static final String INSERT = "INSERT INTO ITEMS(SELLER, ITEM_CATEGORY, TITLE, "
			+ "START_PRICE, DIRECT_PRICE, BID, END_TIME,ITEM_DESCRIBE, ITEM_STATUS,THREAD_LOCK) VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_PICTURE = "INSERT INTO ITEM_IMAGES(ITEM_ID, IMAGE) VALUES(?,?)";
	@Override
	public ItemsBean insert(ItemsBean bean, List<ImageInput> list){
		ItemsBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			conn.setAutoCommit(false);	
			//進行commit
//			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			if(bean!=null){
				stmt.setString(1, bean.getSeller());
				stmt.setInt(2, bean.getItemCategory());
				stmt.setString(3, bean.getTitle());
				stmt.setDouble(4, bean.getStartPrice());
				stmt.setDouble(5, bean.getDirectPrice());
				stmt.setInt(6, bean.getBid());
				stmt.setTimestamp(7, bean.getEndTime());
				stmt.setString(8, bean.getItemDescribe());
				stmt.setInt(9, bean.getItemStatus());
				stmt.setInt(10, bean.getThreadLock());
				int i = stmt.executeUpdate();
				
				ResultSet rs= stmt.getGeneratedKeys();

				if(rs.next()){
					int itemId = rs.getInt(1);
					if(list!=null && !list.isEmpty()){
						for(ImageInput input : list){
							stmt = conn.prepareStatement(INSERT_PICTURE);
							stmt.setInt(1, itemId);
							stmt.setBinaryStream(2, input.getFis(), input.getSize());	
							i = stmt.executeUpdate();
						}
					}
				}
								
				if(i>0){
					conn.commit();
				}
			}						
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
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
	private static final String UPDATE ="UPDATE ITEMS SET SELLER=?,ITEM_CATEGORY=?,TITLE=?, "
			+ "START_PRICE=?, DIRECT_PRICE=?, BID=?, END_TIME=?,ITEM_DESCRIBE=?, ITEM_STATUS=?,THREAD_LOCK=? WHERE ITEM_ID=?";

	/* (non-Javadoc)
	 * @see items.model.dao.ItemsDAO#update(java.lang.String, java.lang.String, int, java.lang.String, double, double, int, java.util.Date, java.lang.String, int, int, int)
	 */
	@Override
	public ItemsBean update(ItemsBean bean){
		ItemsBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			stmt = conn.prepareStatement(UPDATE);
			stmt.setString(1, bean.getSeller());
			stmt.setInt(2, bean.getItemCategory());
			stmt.setString(3, bean.getTitle());
			stmt.setDouble(4, bean.getStartPrice());
			stmt.setDouble(5, bean.getDirectPrice());
			stmt.setInt(6, bean.getBid());
			stmt.setTimestamp(7, bean.getEndTime());
			stmt.setString(8, bean.getItemDescribe());
			stmt.setInt(9, bean.getItemStatus());
			stmt.setInt(10, bean.getThreadLock());
			stmt.setInt(11, bean.getItemId());
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
	
	private static final String DELETE = "DELETE FROM ITEMS WHERE ITEM_ID=?";

	/* (non-Javadoc)
	 * @see items.model.dao.ItemsDAO#delete(int)
	 */
	@Override
	public boolean delete(int itemId){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, itemId);
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
	
	
	
	
	
	public static void main(String[] args){
//		ItemsDAOjdbc dao = new ItemsDAOjdbc();
		//新增
//		ItemsBean bean = new ItemsBean();
//		bean.setSeller("aaaaa");	//FK到MEMBER的USERNAME
//		bean.setItemCategory(1);	//FK到ITEMCLASS的ITEM_CATEGORY
//		bean.setTitle("杯子");
//		bean.setStartPrice(new Double(10));
//		bean.setDirectPrice(new Double(1000));
//		bean.setBid(10);
//		bean.setEndTime(java.sql.Date.valueOf("2015-09-11"));
//		bean.setItemDescribe("這是一個杯子");
//		bean.setItemStatus(0);
//		bean.setThreadLock(0);
//		dao.insert(bean);
//		System.out.println("執行新增");
		
		//修改
//		ItemsBean bean2 = new ItemsBean();
//		bean2.setSeller("bbbbb");
//		bean2.setItemCategory(2);
//		bean2.setTitle("茶杯子");
//		bean2.setStartPrice(new Double(15));
//		bean2.setDirectPrice(new Double(1500));
//		bean2.setBid(10);
//		bean2.setEndTime(java.sql.Date.valueOf("2011-01-01"));
//		bean2.setItemDescribe("xxxxxxxxxxxxxx");
//		bean.setItemStatus(0);
//		bean2.setThreadLock(0);
//		bean2.setItemId(4);
//		dao.update(bean2);
//		System.out.println("執行修改");
		
		//刪除
//		dao.delete(5);
//				System.out.println("執行刪除");
		
		//查詢一筆
//		ItemsBean bean3 = dao.selectId(4);
//		System.out.println(bean3.getItemId()+","+bean3.getSeller()+","+bean3.getItemCategory()
//		+","+bean3.getTitle()+","+bean3.getStartPrice()+","+bean3.getDirectPrice()+","
//		+bean3.getBid()+","+bean3.getEndTime()+","+bean3.getItemDescribe()+","
//		+bean3.getItemStatus()+","+bean3.getThreadLock());
//		System.out.println("查詢一筆");
		
		//查詢全部
//		List<ItemsBean> bean4 = dao.getAll();
//		for(ItemsBean list : bean4){
//			System.out.println(list.getItemId()+","+list.getSeller()+","+list.getItemCategory()
//			+","+list.getTitle()+","+list.getStartPrice()+","+list.getDirectPrice()+","
//			+list.getBid()+","+list.getEndTime()+","+list.getItemDescribe()+","
//			+list.getItemStatus()+","+list.getThreadLock());
//		}
//		System.out.println("查詢全部");
		
		
		
		
		
		
	}
	
	
	
}
