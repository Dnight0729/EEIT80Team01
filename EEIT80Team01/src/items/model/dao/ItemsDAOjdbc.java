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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/items")
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
	
	private static final String SELECT_BY_CATEGORY = "SELECT top 5 * FROM ITEMS WHERE ITEM_CATEGORY = ? and item_status = 0 order by newid()";

	@Override
	@GET
	@Path("/{itemCategory}")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON+";charset=utf-8")
	public List<ItemsBean> selectCategory(@PathParam("itemCategory") int itemCategory){
		List<ItemsBean> result=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECT_BY_CATEGORY);
			stmt.setInt(1, itemCategory);
			rset = stmt.executeQuery();
			result = new ArrayList<ItemsBean>();
			while(rset.next()){
				ItemsBean bean = new ItemsBean();
				bean.setItemId(rset.getInt("ITEM_ID"));
				bean.setSeller(rset.getString("SELLER"));
				bean.setItemCategory(rset.getInt("ITEM_CATEGORY"));
				bean.setTitle(rset.getString("TITLE"));
				bean.setStartPrice(rset.getDouble("START_PRICE"));
				bean.setDirectPrice(rset.getDouble("DIRECT_PRICE"));
				bean.setBid(rset.getInt("BID"));
				bean.setEndTime(rset.getTimestamp("END_TIME"));
				bean.setItemDescribe(rset.getString("ITEM_DESCRIBE"));
				bean.setItemStatus(rset.getInt("ITEM_STATUS"));
				bean.setThreadLock(rset.getInt("THREAD_LOCK"));
				result.add(bean);
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
			//進行commit
			conn.setAutoCommit(false);	
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
					bean.setItemId(itemId);
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
					result =  bean;
					conn.commit();
				}
			}						
		} catch (SQLException e) {
			if (conn!=null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
		}finally{
			try {
				conn.setAutoCommit(true);
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
			conn.setAutoCommit(false);
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
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.setAutoCommit(true);
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
	
	

	private static final String UPDATE_PICTURE = "UPDATE ITEM_IMAGES SET IMAGE=? WHERE IMAGE_NO=?";
	/* (non-Javadoc)
	 * @see items.model.dao.ItemsDAO#update(java.lang.String, java.lang.String, int, java.lang.String, double, double, int, java.util.Date, java.lang.String, int, int, int)
	 */
	@Override
	public ItemsBean update(ItemsBean bean, List<Integer> deletes, List<ImageInput> list){
		ItemsBean result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			conn.setAutoCommit(false);
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
			if(i==1){
				if(deletes!=null && !deletes.isEmpty()){
					for(int delete : deletes){
						stmt = conn.prepareStatement(DELETE_PICTURE);
						stmt.setInt(1, delete);
						i = stmt.executeUpdate();
					}
				}
				int itemId = bean.getItemId();
				
				if(list!=null && !list.isEmpty()){
					System.out.println(itemId);
					for(ImageInput input : list){
						stmt = conn.prepareStatement(INSERT_PICTURE);
						stmt.setInt(1, itemId);
						stmt.setBinaryStream(2, input.getFis(), input.getSize());						
						i = stmt.executeUpdate();
					}
				}
			}
							
			if(i>0){
				result =  bean;
				conn.commit();
			}
		} catch (SQLException e) {
			if (conn!=null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} 
			}
			e.printStackTrace();
		}finally{
			try {
				conn.setAutoCommit(true);
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
		
	private static final String SELECT_THREE_BY_SELLER = "SELECT top(3) * FROM ITEMS WHERE SELLER = ? and ITEM_STATUS=0 order by item_id desc";
	
	public List<ItemsBean> selectThreeBySeller(String username){
		List<ItemsBean> result=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECT_THREE_BY_SELLER);
			stmt.setString(1, username);
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
	
	private static final String DELETE_PICTURE = "DELETE FROM ITEM_IMAGES WHERE IMAGE_NO=? ";

	/* (non-Javadoc)
	 * @see items.model.dao.ItemsDAO#delete(int)
	 */
	@Override
	public boolean delete(int imageNo, int itemId){
		Connection conn = null;
		PreparedStatement stmt = null;
		int i =0;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			//先刪圖片
			stmt = conn.prepareStatement(DELETE_PICTURE);
			stmt.setInt(1, imageNo);
			i = stmt.executeUpdate();
			
			
//			//後刪商品
//			stmt = conn.prepareStatement(DELETE);
//			stmt.setInt(1, itemId);
//			i = stmt.executeUpdate();
//			if(i > 0){
//				conn.commit();
//				return true;
//			}
			
		} catch (SQLException e) {
			if(conn!=null){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally{
			try {
				conn.setAutoCommit(true);
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
		return false;
	}

	
	private static final String GET_SELLER = "SELECT * FROM ITEMS WHERE SELLER = ? and item_status = 0";
	@Override
	public List<ItemsBean> selectSeller(String seller){
		ArrayList<ItemsBean> result=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			stmt = conn.prepareStatement(GET_SELLER);
			stmt.setString(1, seller);
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
	
	private static final String GET_SELLER_DOWN = "SELECT * FROM ITEMS WHERE SELLER = ? and item_status = 1";
	@Override
	public List<ItemsBean> selectSellerDown(String seller){
		ArrayList<ItemsBean> result=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			stmt = conn.prepareStatement(GET_SELLER_DOWN);
			stmt.setString(1, seller);
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
	
	private static final String GET_SELLER_SOLD = "SELECT * FROM ITEMS WHERE SELLER = ? and item_status = 2";
	@Override
	public List<ItemsBean> selectSellerSold(String seller){
		ArrayList<ItemsBean> result=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			stmt = conn.prepareStatement(GET_SELLER_SOLD);
			stmt.setString(1, seller);
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
	
	private static final String GET_LATEST = "SELECT top(5) * FROM ITEMS where item_status = 0 order by ITEM_ID desc";
	@Override
	public List<ItemsBean> selectLatest() {
		ArrayList<ItemsBean> result=null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(GET_LATEST);
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
	
}
