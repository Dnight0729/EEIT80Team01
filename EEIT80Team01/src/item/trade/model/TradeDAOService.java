package item.trade.model;

import java.util.List;

import item.trade.model.dao.TradeDAOJdbc;

public class TradeDAOService {
	public TradeDAOService(){
		dao = new TradeDAOJdbc();
	}
	private TradeDAO dao = null;
	
	public List<TradeBean> getAll(){
		return dao.getAll();
	}
	public TradeBean getByPK(int itemId){
		return dao.getByPK(itemId);
	}
	public List<TradeBean> getByBuyer(String buyer){
		return dao.getByBuyer(buyer);
	}
	public List<TradeBean> getBySeller(String seller){
		return dao.getBySeller(seller);
	}
	public TradeBean insert(TradeBean bean){
		int i = dao.insert(bean);
		if(i==1){
			return bean;
		}
		return null;
	}
	public TradeBean update(TradeBean bean){
		int i = dao.update(bean);
		if(i==1){
			return bean;
		}
		return null;
	}
	public void delete(int itemId){
		dao.delete(itemId);
	}
	
}
