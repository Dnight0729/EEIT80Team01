package member.model;

import global.GlobalService;
import items.model.ItemsBean;
import items.model.ItemsDAO;
import items.model.dao.ItemsDAOjdbc;

import java.util.ArrayList;
import java.util.List;

import member.model.dao.MemberDAOjdbc;

public class MemberService {
	
	public MemberService(){
		
	}
	
	public boolean accountCheck(String username){
		MemberDAO dao = new MemberDAOjdbc();
		MemberBean bean = dao.select(username);

		if(bean!=null && bean.getUserName().equals(username)){
			return false;
		} else {
			return true;
		}		
	}
	
	public MemberBean register(MemberBean bean){
		MemberDAO dao = new MemberDAOjdbc();
		bean.setPassword(GlobalService.getMD5Encoding(bean.getPassword()));
		MemberBean result = dao.insert(bean);
		return result;
	}
	
	public MemberBean checkPasswordWithUsername(String username, String password){
		MemberBean bean = new MemberBean();
		MemberDAO dao = new MemberDAOjdbc();
		bean = dao.select(username);
		if( bean!=null && bean.getUserName().toLowerCase().equals(username) 
				&& bean.getPassword().equals(GlobalService.getMD5Encoding(password))){
			return bean;
		} else {
			return null;
		}
			
	}
	
	public MemberBean changeMemberData(MemberBean bean){
		MemberDAO dao = new MemberDAOjdbc();

		bean = dao.update(bean);
			
		return bean;
	}
	
	public boolean changePassword(String username, String password){
		MemberDAO dao = new MemberDAOjdbc();
		password = GlobalService.getMD5Encoding(password);
		boolean result = dao.update(username, password);		
		return result;
	}
	
	public MemberBean findMemberData(String username){
		MemberDAO dao = new MemberDAOjdbc();
		MemberBean bean = dao.select(username);
		
		return bean;
	}
	
	public List<MemberBean> findAllMembers(){
		List<MemberBean> list = new ArrayList<MemberBean>();
		MemberDAO dao = new MemberDAOjdbc();
		list = dao.select();
		
		return list;
	}
	
	public int banMember(String[] userName) {
		MemberDAO dao = new MemberDAOjdbc();
		return dao.banMember(userName);
	}
	
	public int unbanMember(String[] userName) {
		MemberDAO dao = new MemberDAOjdbc();
		return dao.unbanMember(userName);
	}
	
	public List<ItemsBean> selectMyItems(String username){
		ItemsDAO dao = new ItemsDAOjdbc();
		List<ItemsBean> list = dao.selectThreeBySeller(username);
		
		return list;
	}
}
