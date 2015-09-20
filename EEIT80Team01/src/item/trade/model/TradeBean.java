package item.trade.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


public class TradeBean {
	private int itemId;
    private String seller;
    private int sellerCheck;
    private String buyer;
    private int buyerCheck;
    
//    public boolean equals(Object obj) {
// 	   if (obj == null) { return false; }
// 	   if (obj == this) { return true; }
// 	   if (obj.getClass() != getClass()) {
// 	     return false;
// 	   }
// 	  TradeBean rhs = (TradeBean) obj;
// 	   return new EqualsBuilder()
// 	                 .append(itemId, rhs.itemId)
// 	                 .append(buyer, rhs.buyer)
// 	                 .append(seller, rhs.seller)
// 	                 .isEquals();
// 	  }
//	 public int hashCode() {
//	     return new HashCodeBuilder(23,29).
//	       append(itemId).
//	       append(buyer).
//	       append(seller).
//	       toHashCode();
//	  }
    
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
