package item.trade.model;

import java.util.List;

public interface TradeDAO {
	public List<TradeBean> getAll();
	public TradeBean getByPK(int itemId);
	public List<TradeBean> getBySeller(String seller);
	public List<TradeBean> getByBuyer(String buyer);
	public int insert(TradeBean bean);
	public int update(TradeBean bean);
	public int delete(int itemId);
}
