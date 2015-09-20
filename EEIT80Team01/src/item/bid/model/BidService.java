package item.bid.model;

import java.sql.Timestamp;

import items.model.ItemsBean;
import items.model.ItemsDAO;
import items.model.dao.ItemsDAOjdbc;

public class BidService {
	private BidLogBean bidLogBean = null;
	private BidLogDAOService bidLogDao = null;
	private ItemsDAO itemsDao = null;
	private ItemsBean itemsBean = null;
	public BidService(){
		bidLogBean = new BidLogBean();
		bidLogDao = new BidLogDAOService();
		itemsDao = new ItemsDAOjdbc();
	}
	public String getSeller(int itemId){
		itemsBean = itemsDao.selectId(itemId);
		return itemsBean.getSeller();
	}
	
	public BidLogBean compareTopPrice(BidLogBean newBean,int itemId){
		BidLogBean result = null;
		bidLogBean = bidLogDao.getTopPrice(itemId);
		if(bidLogBean!=null && newBean!=null){
			double bidPrice = newBean.getBidPrice();
			double topPrice = bidLogBean.getBidPrice();
			if(bidPrice>topPrice){
				result = bidLogDao.insert(newBean);
				if(result!=null){
					System.out.println("新增成功!");
					return result;
				} 
				return null;
			}
			return null;
		}
		if(bidLogBean==null){
			System.out.println("the column of this itemId in table bidLog is empty");
			result = bidLogDao.insert(newBean);
			if(result!=null){
				return result;
			}
			return null;
		}
		return null;
	}
	public boolean validateBidPrice(double bidPrice,int itemId){
		ItemsBean itemsBean = itemsDao.selectId(itemId);
		if(itemsBean!=null){
			double startPrice = itemsBean.getStartPrice();
			double directPrice = itemsBean.getDirectPrice();
			if(bidPrice>startPrice && bidPrice<directPrice){
				return true;
			}
			return false;
		}
		return false;
	}
	public boolean validateBidTime(Timestamp bidTime,int itemId){
		itemsBean = itemsDao.selectId(itemId);
		if(itemsBean!=null){
			long endTime = itemsBean.getEndTime().getTime();
			if(bidTime.getTime()<=endTime){
				return true;
			}
			return false;
		}
		return false;
	}
	public boolean checkStatus(int itemId){
		itemsBean = itemsDao.selectId(itemId);
		if(itemsBean!=null){
			int itemStatus = itemsBean.getItemStatus();
			int threadStatus = itemsBean.getThreadLock();
			if(itemStatus==0 && threadStatus==0){
				return true;
			}
		}
		return false;
	}
	public ItemsBean toggleThread(int itemId){
		ItemsBean result = null;
		itemsBean = itemsDao.selectId(itemId);
		if(itemsBean!=null && itemsBean.getThreadLock()==0){
			itemsBean.setThreadLock(1);
			result = itemsDao.update(itemsBean);
		} else if(itemsBean!=null && itemsBean.getThreadLock()==1){
			itemsBean.setThreadLock(0);
			result = itemsDao.update(itemsBean);
		} else{
			return null;
		}
		return result;
	}
	public boolean changeItemStatusToTwo(int itemId){
		ItemsBean result = null;
		itemsBean = itemsDao.selectId(itemId);
		if(itemsBean!=null && itemsBean.getItemStatus()==0){
			itemsBean.setItemStatus(2);
			result = itemsDao.update(itemsBean);
			if(result!=null){
				return true;
			}
		}
		return false;
	}
	public boolean changeItemStatusToZero(int itemId){
		ItemsBean result = null;
		itemsBean = itemsDao.selectId(itemId);
		if(itemsBean!=null && itemsBean.getItemStatus()==2){
			itemsBean.setItemStatus(0);
			result = itemsDao.update(itemsBean);
			if(result!=null){
				return true;
			}
		}
		return false;
	}
	public BidLogBean insertDirectBuyer(int itemId,Timestamp bidTime,String buyer){
		itemsBean = itemsDao.selectId(itemId);
		if(itemsBean!=null){
			double directPrice = itemsBean.getDirectPrice();
			bidLogBean.setItemId(itemId);
			bidLogBean.setBidTime(bidTime);
			bidLogBean.setBuyer(buyer);
			bidLogBean.setBidPrice(directPrice);
			bidLogBean = bidLogDao.insert(bidLogBean);
			return bidLogBean;
		}
		return null;
	}
}
