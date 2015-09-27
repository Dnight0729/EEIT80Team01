package member.model.dao;

import global.GlobalService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import member.model.MessageBean;
import member.model.MessageDAO;

public class MessageDAOjdbc implements MessageDAO {

	private DataSource ds = null;
	
	public MessageDAOjdbc() {
  
        Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
	
		} catch (NamingException e) {
		}		
    }

	
	private static final String SELECT_BY_MESSAGENUMBER =
			"select * from MSG where MSGNO=?";
	
	public MessageBean select(long messageNumber) {
		MessageBean bean = null;
		ResultSet rset = null;
		try(Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_BY_MESSAGENUMBER);){
			stmt.setLong(1, messageNumber);
			rset = stmt.executeQuery();
			if(rset.next()){
				bean = new MessageBean();
				bean.setSender(rset.getString("sender"));
				bean.setReceiver(rset.getString("receiver"));
				bean.setMessageTitle(rset.getString("msg_title"));
				bean.setMessageBody(rset.getString("msg_body"));
				bean.setMessageTime(rset.getTimestamp("msg_time"));
				bean.setMessageNumber(rset.getLong("msgno"));
				bean.setVisibility(rset.getInt("visibility"));
			}
		} catch (SQLException e) {
			
		} finally{
			if(rset!=null){
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}		
		return bean;
	}

	private static final String SELECT_BY_SENDER =
			"select sender,receiver,msg_title,msg_body,msg_time,msgno from MSG where SENDER=? and visibility&2=0 order by msgno desc";

	public List<MessageBean> findBySender(String sender) {
		List<MessageBean> result = new ArrayList<MessageBean>();
		ResultSet rset = null;
		try(Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_BY_SENDER);){
			stmt.setString(1, sender);
			rset = stmt.executeQuery();
			while(rset.next()){				
				MessageBean bean = new MessageBean();
				bean.setSender(rset.getString("sender"));
				bean.setReceiver(rset.getString("receiver"));
				bean.setMessageTitle(rset.getString("msg_title"));
				bean.setMessageBody(rset.getString("msg_body"));
				bean.setMessageTime(rset.getTimestamp("msg_time"));
				bean.setMessageNumber(rset.getLong("msgno"));
				result.add(bean);
			}
		} catch (SQLException e) {
			
		} finally{
			if(rset!=null){
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}			
		return result;
	}

	private static final String SELECT_BY_RECEIVER =
			"select sender,receiver,msg_title,msg_body,msg_time,msgno from MSG where RECEIVER=? and visibility&1=0 order by msgno desc";
	
	public List<MessageBean> findByReceiver(String receiver) {
		List<MessageBean> result = new ArrayList<MessageBean>();
		ResultSet rset = null;
		try(Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_BY_RECEIVER);){
			stmt.setString(1, receiver);
			rset = stmt.executeQuery();
			while(rset.next()){				
				MessageBean bean = new MessageBean();
				bean.setSender(rset.getString("sender"));
				bean.setReceiver(rset.getString("receiver"));
				bean.setMessageTitle(rset.getString("msg_title"));
				bean.setMessageBody(rset.getString("msg_body"));
				bean.setMessageTime(rset.getTimestamp("msg_time"));
				bean.setMessageNumber(rset.getLong("msgno"));
				result.add(bean);
			}
		} catch (SQLException e) {
			
		} finally{
			if(rset!=null){
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}			
		return result;
	}
	
	private static final String INSERT =
			"insert into MSG (sender, receiver, msg_title, msg_body, msg_time, visibility) values (?, ?, ? ,? ,?, ?)";

	public MessageBean insert(MessageBean bean) {
		MessageBean result = null;
		try(Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(INSERT);){
					if(bean!=null) {
						stmt.setString(1, bean.getSender());
						stmt.setString(2, bean.getReceiver());
						stmt.setString(3, bean.getMessageTitle());
						stmt.setString(4, bean.getMessageBody());
						stmt.setTimestamp(5, bean.getMessageTime());
						stmt.setInt(6, 0);
						int i = stmt.executeUpdate();
						if(i==1) {
							result = bean;
						}
					}				
				} catch (SQLException e) {
					e.printStackTrace();
				}
		return result;
	}

	private static final String UPDATE =
			"update MSG set sender=?, receiver=?, msg_title=?, msg_body=?, msg_time=?, where msgno=?";

	public MessageBean update(MessageBean bean) {
		MessageBean result = null;
		try(Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(UPDATE);) {
			stmt.setString(1, bean.getSender());
			stmt.setString(2, bean.getReceiver());
			stmt.setString(3, bean.getMessageTitle());
			stmt.setString(4, bean.getMessageBody());
			stmt.setTimestamp(5, bean.getMessageTime());
			stmt.setLong(6, bean.getMessageNumber());
			int i = stmt.executeUpdate();
			if(i==1) {
				result = bean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	private static final String CHANGEVISIBILITY =
			"update MSG set visibility=? where msgno=?";

	public boolean changeVisibility(String charactor ,int type,String[] messageNumbers) {

		
		try(Connection conn = ds.getConnection();
			PreparedStatement pstm = conn.prepareStatement(SELECT_BY_MESSAGENUMBER);
			PreparedStatement stmt = conn.prepareStatement(CHANGEVISIBILITY);) {				

			for(String messageNumber : messageNumbers){
				pstm.setLong(1, Long.parseLong(messageNumber));
				ResultSet rs = pstm.executeQuery();
				if(rs.next()){
					int visibiblty = rs.getInt("visibility")|type;
					if(type==2){
						if(charactor.equals(rs.getString("sender"))){
							stmt.setInt(1, visibiblty);	
							stmt.setLong(2, Long.parseLong(messageNumber));
							stmt.executeUpdate();
						}
					} else if(type==1){
						if(charactor.equals(rs.getString("receiver"))){
							stmt.setInt(1, visibiblty);	
							stmt.setLong(2, Long.parseLong(messageNumber));
							stmt.executeUpdate();
						}
					}
				}
				
			}
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return false;
	}
	
	
	private static final String DELETE =
			"delete from MSG where msgno=?";

	public boolean delete(long messageNumber) {
		try(Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(DELETE);) {			
			stmt.setLong(1, messageNumber);
			int i = stmt.executeUpdate();
			if(i==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	


}
