package items.model;

import java.util.TimerTask;

import item.bid.model.BidLogBean;
import item.bid.model.BidLogDAOService;
import item.bid.model.BidService;

public class ItemTimer extends TimerTask{
	private int itemId;
	private BidService bidService;
	private BidLogDAOService daoService;
	private BidLogBean bidBean;
	public ItemTimer(ItemsBean bean){
		this.itemId = bean.getItemId();
		System.out.println(this.itemId);
	}
	
	@Override
	public void run() {
		bidBean = new BidLogBean();
		bidService = new BidService();
		daoService = new BidLogDAOService();
		bidBean = daoService.getTopPrice(itemId);
		System.out.println(bidBean);
		if(bidBean!=null ){
			bidService.changeItemStatusToTwo(itemId);
		}else{
			bidService.changeItemStatusToOne(itemId);
		}	
		
		
//		boolean del = dao.delete(itemId);
//		if(del){
//			System.out.println(itemId+"刪除成功");
//		}else{
//			System.out.println(itemId+"刪除失敗");
//		}
	}
	public ItemTimer(){}
}
