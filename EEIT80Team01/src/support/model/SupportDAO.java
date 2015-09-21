package support.model;

import java.util.List;

import support.model.SupportBean;

public interface SupportDAO {

	SupportBean select(String supportername);

	List<SupportBean> select();

	List<SupportBean> selectSupporterNameLike(String supportername);

	List<SupportBean> selectEmployeeIDLike(String employeeid);

	List<SupportBean> selectLastNameLike(String lastname);

	List<SupportBean> selectFirstNameLike(String firstname);

	List<SupportBean> selectFirstNameLike(String firstname, String lastname);

	SupportBean selectByEmployeeID(String employeeid);

	SupportBean insert(SupportBean bean);

	SupportBean update(SupportBean bean);

	boolean delete(String supportername);

	int delete(List<SupportBean> supporterlist);
	
	boolean deleteByEmployeeID(String employeeid);
	
	public boolean modify(SupportBean bean, String oldSupporterName);
}