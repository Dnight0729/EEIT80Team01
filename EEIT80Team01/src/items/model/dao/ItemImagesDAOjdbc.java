package items.model.dao;


import java.io.FileInputStream;
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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import global.GlobalService;
import items.model.ImageInput;
import items.model.ImagesBean;
import items.model.ItemImagesDAO;
@Path("/itemImg")
public class ItemImagesDAOjdbc implements ItemImagesDAO {
	private DataSource ds;
	
	public ItemImagesDAOjdbc(){
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = "INSERT INTO ITEM_IMAGES(ITEM_ID, IMAGE) VALUES(?,?)";
	/* (non-Javadoc)
	 * @see items.model.dao.ItemImagesDAO#insert(items.model.ImagesBean)
	 */
	@Override
	public int insert(ImagesBean bean, List<ImageInput> list){
		int reuslt=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
//				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				conn = ds.getConnection();
				conn.setAutoCommit(false);				
				stmt = conn.prepareStatement(INSERT);
				if(bean!=null){
					if(list!=null && !list.isEmpty()){
						for(ImageInput input : list){
							stmt.setInt(1, bean.getItemId());
							stmt.setBinaryStream(2, input.getFis(), input.getSize());	
							reuslt = stmt.executeUpdate();
							conn.commit();
						}
					}
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
		return reuslt;
	}
	
	private static final String UPDATE ="UPDATE ITEM_IMAGES SET IMAGE=? WHERE IMAGE_NO=?";
	
	/* (non-Javadoc)
	 * @see items.model.dao.ItemImagesDAO#update(items.model.ImagesBean)
	 */
	@Override
	public int update(ImagesBean bean, FileInputStream fis, long size){
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(UPDATE);
			stmt.setBinaryStream(1, fis, size);
			stmt.setInt(2, bean.getImageNo());
			
			result = stmt.executeUpdate();
			conn.commit();
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
	
	private static final String DELETE = "DELETE FROM ITEM_IMAGES WHERE IMAGE_NO=? ";
	
	/* (non-Javadoc)
	 * @see items.model.dao.ItemImagesDAO#delete(int)
	 */
	@Override
	public boolean delete(int imageNo){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			stmt = conn.prepareStatement(DELETE);
			stmt.setInt(1, imageNo);
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
	 * @return
	 */
	@Override
	public List<ImagesBean> selectOneItem(int itemId){
		List<ImagesBean> result  =null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECT_ITEM_PICTURE);
			stmt.setInt(1, itemId);
			rset = stmt.executeQuery();
			 result = new ArrayList<ImagesBean>();
			while(rset.next()){
				ImagesBean image = new ImagesBean();
				image.setImage(rset.getBlob("IMAGE"));
				result.add(image);
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
	
	private static final String SELECT_IMAGENO_BY_ITEMID = "SELECT IMAGE_NO FROM ITEM_IMAGES WHERE ITEM_ID=?";
	@GET
	@Path("/{itemId}")
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON+";charset=utf-8")
	public List<Integer> selectImages(@PathParam("itemId") int itemId){
		List<Integer> result  =null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECT_IMAGENO_BY_ITEMID);
			stmt.setInt(1, itemId);
			rset = stmt.executeQuery();
			 result = new ArrayList<Integer>();
			while(rset.next()){
				result.add(rset.getInt("IMAGE_NO"));
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
	
	
private static final String SELECT_PICTURE = "SELECT IMAGE FROM ITEM_IMAGES WHERE IMAGE_NO=?";
	
	public ImagesBean selectOneImage(int imageNo){
		ImagesBean result  =null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
//			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			conn = ds.getConnection();
			stmt = conn.prepareStatement(SELECT_PICTURE);
			stmt.setInt(1, imageNo);
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

}
