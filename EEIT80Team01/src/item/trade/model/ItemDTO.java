package item.trade.model;

public class ItemDTO {
	private int imageNo;
	private String title;
	private TradeBean tradeBean;
	public int getImageNo() {
		return imageNo;
	}
	public void setImageNo(int imageNo) {
		this.imageNo = imageNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public TradeBean getTradeBean() {
		return tradeBean;
	}
	public void setTradeBean(TradeBean tradeBean) {
		this.tradeBean = tradeBean;
	}
	
}
