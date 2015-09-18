package items.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import global.GlobalService;
import items.model.ImagesBean;
import items.model.ItemImagesDAO;
import items.model.ItemsBean;

public class ItemImagesDAOjdbc implements ItemImagesDAO {
	private DataSource ds;
	
	private static final String URL="jdbc:sqlserver://localhost:1433;database=EEIT80TEAM01";
	private static final String USER="sa";
	private static final String PASSWORD="sa123456";
	
//	public ItemImagesDAOjdbc(){
//		Context ctx;
//		try {
//			ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("GlobalService.JNDI_DB_NAME");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	
	private static final String INSERT = "INSERT INTO ITEM_IMAGES(ITEM_ID, IMAGE, ITEM_NO) VALUES(?,?,?)";
	/* (non-Javadoc)
	 * @see items.model.dao.ItemImagesDAO#insert(items.model.ImagesBean)
	 */
	@Override
	public int insert(ImagesBean bean, FileInputStream fis, long size){
		int reuslt=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
//				conn = ds.getConnection();
				conn.setAutoCommit(false);				
				stmt = conn.prepareStatement(INSERT);
				if(bean!=null){
					stmt.setInt(1, bean.getItemId());
					stmt.setBinaryStream(2, fis, size);
					stmt.setInt(3, bean.getItemNo());
				}
				reuslt = stmt.executeUpdate();
				
				conn.setAutoCommit(true);
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
				if(fis!=null){
					try {
						fis.close();
					} catch (IOException e) {
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
		return reuslt;
	}
	
	private static final String UPDATE ="UPDATE ITEM_IMAGES SET IMAGE=? WHERE ITEM_ID=? AND ITEM_NO=?";
	
	/* (non-Javadoc)
	 * @see items.model.dao.ItemImagesDAO#update(items.model.ImagesBean)
	 */
	@Override
	public int update(ImagesBean bean, FileInputStream fis, long size){
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			conn = ds.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(UPDATE);
			stmt.setBinaryStream(1, fis, size);
			stmt.setInt(2, bean.getItemId());
			stmt.setInt(3, bean.getItemNo());
			
			result = stmt.executeUpdate();
			conn.setAutoCommit(true);
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
	
	private static final String DELETE = "DELETE FROM ITEM_IMAGES WHERE ITEM_ID=? AND ITEM_NO=? ";
	
	/* (non-Javadoc)
	 * @see items.model.dao.ItemImagesDAO#delete(int)
	 */
	@Override
	public boolean delete(int itemId, int itemNo){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			conn = ds.getConnection();
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, itemId);
			stmt.setInt(2, itemNo);
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
	
	private static final String SELECT_ITEM_PICTURE = "SELECT IMAGE FROM ITEM_IMAGES WHERE ITEM_ID=?";
	
	/**
	 * @param itemId
	 * @param itemNo
	 * @return
	 */
	@Override
	public ImagesBean selectOneItem(int itemId){
		ImagesBean result  =null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
//			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECT_ITEM_PICTURE);
			stmt.setInt(1, itemId);
			rset = stmt.executeQuery();
			 
			if(rset.next()){
				result = new ImagesBean();
				result.setImage(rset.getBlob("IMAGE"));
				
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
	
	
	
	public static void main(String[] args) throws IOException{
//		ItemImagesDAO dao = new ItemImagesDAOjdbc();
		
		//新增
//		ImagesBean bean = new ImagesBean();
//		bean.setItemId(3);
//		bean.setItemNo(3);
//		File file = new File("C:/Users/Student/Desktop/01.jpg");
//		long size = file.length();
//		FileInputStream fis = new FileInputStream(file);
//		dao.insert(bean, fis, size);
//		
//		System.out.println("執行新增");
		
		//修改
//		ImagesBean bean2 = new ImagesBean();
//		bean2.setItemId(3);
//		bean2.setItemNo(3);
//		File file = new File("C:/Users/Student/Desktop/02.jpg");
//		long size = file.length();
//		FileInputStream fis = new FileInputStream(file);
//		dao.update(bean2, fis, size);
//	
//		System.out.println("執行修改");
		
		//刪除
//		dao.delete(3,2);
//		System.out.println("執行刪除");
		
		
	}
	
}
