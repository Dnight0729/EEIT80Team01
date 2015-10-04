package item.trade.model;

import java.io.Serializable;


public class ItemDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int imageNo;
	private String title;
	private int itemId;
    private String seller;
    private int sellerCheck;
    private String buyer;
    private int buyerCheck;
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
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public int getSellerCheck() {
		return sellerCheck;
	}
	public void setSellerCheck(int sellerCheck) {
		this.sellerCheck = sellerCheck;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public int getBuyerCheck() {
		return buyerCheck;
	}
	public void setBuyerCheck(int buyerCheck) {
		this.buyerCheck = buyerCheck;
	}
	
		

}
