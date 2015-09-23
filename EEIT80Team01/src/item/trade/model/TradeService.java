package item.trade.model;


public class TradeService {
	private TradeDAOService tradeDaoService = null;
	private TradeBean bean = null;
	private TradeBean newBean = null;
	public TradeService(){
		tradeDaoService = new TradeDAOService();
	}
	public TradeBean changeSellerStatus(int itemId,int sellerCheck){
		bean = tradeDaoService.getByPK(itemId);
		if(bean!=null){
			bean.setSellerCheck(sellerCheck);
			newBean = tradeDaoService.update(bean);
			if(newBean!=null){
				return newBean;
			}
			return null;
		}
		return null;
	}
	public TradeBean changeBuyerStatus(int itemId,int buyerCheck){
		bean = tradeDaoService.getByPK(itemId);
		if(bean!=null){
			bean.setBuyerCheck(buyerCheck);
			newBean = tradeDaoService.update(bean);
			if(newBean!=null){
				return newBean;
			}
			return null;
		}
		return null;
	}
}
