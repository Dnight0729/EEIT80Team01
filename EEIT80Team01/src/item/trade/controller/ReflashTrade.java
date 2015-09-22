package item.trade.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import global.GlobalService;
import item.trade.model.TradeBean;
import item.trade.model.TradeDAOService;
import items.model.ItemImagesService;
import items.model.ItemsBean;
import items.model.ItemsService;
import member.model.MemberBean;

@WebServlet("/trade/myTradeCenter.jsp")
public class ReflashTrade extends HttpServlet { 
	private TradeDAOService tradeDAOService = null;
	private ItemsService itemsService = null;
	private ItemImagesService itemImgService = null;
	private String usr = null;
	private int itemId = -1;
	private int itemImgNum = -1;
	private String itemTitle = null;
	private ItemsBean itemsBean = null;
	public ReflashTrade(){
		tradeDAOService = new TradeDAOService();
		itemsService = new ItemsService();
		itemImgService = new ItemImagesService();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean)session.getAttribute(GlobalService.LOGIN_TOKEN);
		usr = memberBean.getUserName();
		List<TradeBean> myBuyItems = tradeDAOService.getByBuyer(usr);
		List<TradeBean> mySellItems = tradeDAOService.getBySeller(usr);
		List<TradeBean> myBuyItemsFinished = new ArrayList<>();
		List<TradeBean> myBuyItemsCheck = new ArrayList<>();
		List<TradeBean> myBuyItemsUncheck = new ArrayList<>();
		List<TradeBean> mySellItemsFinished  = new ArrayList<>();
		List<TradeBean> mySellItemsCheck = new ArrayList<>();
		List<TradeBean> mySellItemsUncheck = new ArrayList<>();
		if(myBuyItems!=null){
			for(TradeBean myBuyItem:myBuyItems){
				if(myBuyItem.getBuyerCheck()==1 && myBuyItem.getSellerCheck()==1){
					myBuyItemsFinished.add(myBuyItem);
					itemId = myBuyItem.getItemId();
					itemsBean = itemsService.getOneItemId(itemId);
					itemTitle = itemsBean.getTitle();
					itemImgNum = itemImgService.selectImagesNumbers(itemId).get(0);
					
				}else if(myBuyItem.getBuyerCheck()==1 && myBuyItem.getSellerCheck()==0){
					myBuyItemsCheck.add(myBuyItem);
				}else{
					myBuyItemsUncheck.add(myBuyItem);
				}
			}
		}
		if(mySellItems!=null){
			for(TradeBean mySellItem:mySellItems){
				if(mySellItem.getBuyerCheck()==1 && mySellItem.getSellerCheck()==1){
					mySellItemsFinished.add(mySellItem);
				}else if(mySellItem.getBuyerCheck()==0 && mySellItem.getSellerCheck()==1){
					mySellItemsCheck.add(mySellItem);
				}else{
					mySellItemsUncheck.add(mySellItem);
				}
			}
		}
		request.setAttribute("myBuyItemsCheck", myBuyItemsCheck);
		request.setAttribute("myBuyItemsUncheck", myBuyItemsUncheck);
		request.setAttribute("myBuyItemsFinished", myBuyItemsFinished);
		request.setAttribute("mySellItemsCheck", mySellItemsCheck);
		request.setAttribute("mySellItemsUncheck", mySellItemsUncheck);
		request.setAttribute("mySellItemsFinished", mySellItemsFinished);
		request.getRequestDispatcher("tradeCenter.jsp").forward(request, response);
		
		
		
	}
	
}
