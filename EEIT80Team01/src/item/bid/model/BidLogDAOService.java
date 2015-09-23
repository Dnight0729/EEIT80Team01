package item.bid.model;

import java.sql.Timestamp;
import java.util.List;

import item.bid.model.dao.BidLogDAOJdbc;

public class BidLogDAOService {
	public BidLogDAOService(){
		dao = new BidLogDAOJdbc();
	}
	private BidLogDAO dao = null;
	
	public BidLogBean getTopPrice(int itemId){
		return dao.getTopPrice(itemId);
	}
	public List<BidLogBean> getAll(){
		return dao.getAll();
	}
	public BidLogBean getByPK(int itemId,String buyer){
		return dao.getByPK(itemId, buyer);
	}
	public List<BidLogBean> getByBidPrice(double bidPrice){
		return dao.getByBidPrice(bidPrice);
	}
	public List<BidLogBean> getByBidTime(Timestamp bidTime){
		return dao.getByBidTime(bidTime);
	}
	public List<BidLogBean> getByBuyer(String buyer){
		return dao.getByBuyer(buyer);
	}
	public List<BidLogBean> getByItem(int itemId){
		return dao.getByItem(itemId);
	}
	public BidLogBean insert(BidLogBean bean){
		BidLogBean temp = dao.getByPK(bean.getItemId(), bean.getBuyer());
		
		if(temp!=null){
			int i = dao.update(bean);
			if(i==1){
				return bean;
			}
		}else{
			int i = dao.insert(bean);
			if(i==1){
				return bean;
			}
		}

		return null;
	}
	public BidLogBean update(BidLogBean bean){
		int i = dao.update(bean);
		if(i==1){
			return bean;
		}
		return null;
	}
	public void delete(int itemId,String buyer){
		dao.delete(itemId, buyer);
	}
	public int getItemBidCount(int itemId){
		BidLogDAO dao = new BidLogDAOJdbc();
		return dao.getItemBidCount(itemId);
	}
}
