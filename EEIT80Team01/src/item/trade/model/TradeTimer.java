package item.trade.model;

import java.util.TimerTask;

public class TradeTimer extends TimerTask{
	private TradeDAOService tradeDaoService = null;
	private int itemId = 0;
	private TradeBean tradeBean= null;
	public TradeTimer(int itemId){
		tradeDaoService = new TradeDAOService();
		this.itemId = itemId;
	}
	@Override
	public void run() {
		tradeBean = tradeDaoService.getByPK(itemId);
		int buyerCheck = tradeBean.getBuyerCheck();
		int sellerCheck = tradeBean.getSellerCheck();
		if(!(buyerCheck==1 && sellerCheck==1)){
			int del = tradeDaoService.delete(itemId);
			if(del==1){
				System.out.println(itemId+"商品刪除成功");
			}else{
				System.out.println(itemId+"商品刪除失敗");
			}
		}
	}

}
