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
import item.trade.model.ItemDTO;
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
	private ItemDTO itemDto = null;
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
		List<ItemDTO> myBuyItemsFinished = new ArrayList<>();
		List<ItemDTO> myBuyItemsCheck = new ArrayList<>();
		List<ItemDTO> myBuyItemsUncheck = new ArrayList<>();
		List<ItemDTO> mySellItemsFinished  = new ArrayList<>();
		List<ItemDTO> mySellItemsCheck = new ArrayList<>();
		List<ItemDTO> mySellItemsUncheck = new ArrayList<>();
		if(myBuyItems!=null){
			for(TradeBean myBuyItem:myBuyItems){
				if(myBuyItem.getBuyerCheck()==1 && myBuyItem.getSellerCheck()==1){
					itemDto = new ItemDTO();
					itemId = myBuyItem.getItemId();
					itemsBean = itemsService.getOneItemId(itemId);
					itemTitle = itemsBean.getTitle();
					List<Integer> itemImgNums = itemImgService.selectImagesNumbers(itemId);
					if(!itemImgNums.isEmpty()){
						itemImgNum = itemImgNums.get(0);
					}
					itemDto.setImageNo(itemImgNum);
					itemDto.setTitle(itemTitle);
					itemDto.setItemId(itemId);
					itemDto.setBuyer(myBuyItem.getBuyer());
					itemDto.setSeller(myBuyItem.getSeller());
					itemDto.setBuyerCheck(myBuyItem.getBuyerCheck());
					itemDto.setSellerCheck(myBuyItem.getSellerCheck());
					myBuyItemsFinished.add(itemDto);
					
				}else if(myBuyItem.getBuyerCheck()==1 && myBuyItem.getSellerCheck()==0){
					itemDto = new ItemDTO();
					itemId = myBuyItem.getItemId();
					itemsBean = itemsService.getOneItemId(itemId);
					itemTitle = itemsBean.getTitle();
					List<Integer> itemImgNums = itemImgService.selectImagesNumbers(itemId);
					if(!itemImgNums.isEmpty()){
						itemImgNum = itemImgNums.get(0);
					}
					itemDto.setImageNo(itemImgNum);
					itemDto.setTitle(itemTitle);
					itemDto.setItemId(itemId);
					itemDto.setBuyer(myBuyItem.getBuyer());
					itemDto.setSeller(myBuyItem.getSeller());
					itemDto.setBuyerCheck(myBuyItem.getBuyerCheck());
					itemDto.setSellerCheck(myBuyItem.getSellerCheck());
					myBuyItemsCheck.add(itemDto);
				}else if(myBuyItem.getBuyerCheck()==0 && myBuyItem.getSellerCheck()==0 || myBuyItem.getBuyerCheck()==0 && myBuyItem.getSellerCheck()==1){
					itemDto = new ItemDTO();
					itemId = myBuyItem.getItemId();
					itemsBean = itemsService.getOneItemId(itemId);
					itemTitle = itemsBean.getTitle();
					List<Integer> itemImgNums = itemImgService.selectImagesNumbers(itemId);
					if(!itemImgNums.isEmpty()){
						itemImgNum = itemImgNums.get(0);
					}
					itemDto.setImageNo(itemImgNum);
					itemDto.setTitle(itemTitle);
					itemDto.setItemId(itemId);
					itemDto.setBuyer(myBuyItem.getBuyer());
					itemDto.setSeller(myBuyItem.getSeller());
					itemDto.setBuyerCheck(myBuyItem.getBuyerCheck());
					itemDto.setSellerCheck(myBuyItem.getSellerCheck());
					myBuyItemsUncheck.add(itemDto);
				}
			}
		}
		if(mySellItems!=null){
			for(TradeBean mySellItem:mySellItems){
				if(mySellItem.getBuyerCheck()==1 && mySellItem.getSellerCheck()==1){
					itemDto = new ItemDTO();
					itemId = mySellItem.getItemId();
					itemsBean = itemsService.getOneItemId(itemId);
					itemTitle = itemsBean.getTitle();
					List<Integer> itemImgNums = itemImgService.selectImagesNumbers(itemId);
					if(!itemImgNums.isEmpty()){
						itemImgNum = itemImgNums.get(0);
					}
					itemDto.setImageNo(itemImgNum);
					itemDto.setTitle(itemTitle);
					itemDto.setItemId(itemId);
					itemDto.setBuyer(mySellItem.getBuyer());
					itemDto.setSeller(mySellItem.getSeller());
					itemDto.setBuyerCheck(mySellItem.getBuyerCheck());
					itemDto.setSellerCheck(mySellItem.getSellerCheck());
					mySellItemsFinished.add(itemDto);
				}else if(mySellItem.getBuyerCheck()==0 && mySellItem.getSellerCheck()==1){
					itemDto = new ItemDTO();
					itemId = mySellItem.getItemId();
					itemsBean = itemsService.getOneItemId(itemId);
					itemTitle = itemsBean.getTitle();
					List<Integer> itemImgNums = itemImgService.selectImagesNumbers(itemId);
					if(!itemImgNums.isEmpty()){
						itemImgNum = itemImgNums.get(0);
					}
					itemDto.setImageNo(itemImgNum);
					itemDto.setTitle(itemTitle);
					itemDto.setItemId(itemId);
					itemDto.setBuyer(mySellItem.getBuyer());
					itemDto.setSeller(mySellItem.getSeller());
					itemDto.setBuyerCheck(mySellItem.getBuyerCheck());
					itemDto.setSellerCheck(mySellItem.getSellerCheck());
					mySellItemsCheck.add(itemDto);				
				}else if(mySellItem.getBuyerCheck()==0 && mySellItem.getSellerCheck()==0 || mySellItem.getBuyerCheck()==1 && mySellItem.getSellerCheck()==0){
					itemDto = new ItemDTO();
					itemId = mySellItem.getItemId();
					itemsBean = itemsService.getOneItemId(itemId);
					itemTitle = itemsBean.getTitle();
					List<Integer> itemImgNums = itemImgService.selectImagesNumbers(itemId);
					if(!itemImgNums.isEmpty()){
						itemImgNum = itemImgNums.get(0);
					}
					itemDto.setImageNo(itemImgNum);
					itemDto.setTitle(itemTitle);
					itemDto.setItemId(itemId);
					itemDto.setBuyer(mySellItem.getBuyer());
					itemDto.setSeller(mySellItem.getSeller());
					itemDto.setBuyerCheck(mySellItem.getBuyerCheck());
					itemDto.setSellerCheck(mySellItem.getSellerCheck());
					mySellItemsUncheck.add(itemDto);
				}
			}
		}
		session.setAttribute("myBuyItemsCheck", myBuyItemsCheck);
		session.setAttribute("myBuyItemsUncheck", myBuyItemsUncheck);
		session.setAttribute("myBuyItemsFinished", myBuyItemsFinished);
		session.setAttribute("mySellItemsCheck", mySellItemsCheck);
		session.setAttribute("mySellItemsUncheck", mySellItemsUncheck);
		session.setAttribute("mySellItemsFinished", mySellItemsFinished);
		
		if(request.getAttribute("refererPage")!=null){
			System.out.println("c");
			response.sendRedirect(request.getAttribute("refererPage").toString());
		}else if(request.getHeader("referer").equalsIgnoreCase("https://"+request.getServerName()+request.getContextPath()+"/trade/tradeCenter.jsp")){
//		}else if(request.getHeader("referer").equalsIgnoreCase("https://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/trade/tradeCenter.jsp")){
			System.out.println("a");
			response.sendRedirect(request.getContextPath()+"/trade/tradeCenterSeller.jsp");
		}else if(request.getHeader("referer").equalsIgnoreCase("https://"+request.getServerName()+request.getContextPath()+"/trade/tradeCenterSeller.jsp")){
//		}else if(request.getHeader("referer").equalsIgnoreCase("https://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/trade/tradeCenterSeller.jsp")){
			System.out.println("b");
			response.sendRedirect(request.getContextPath()+"/trade/tradeCenter.jsp");
		}else{
			System.out.println("d");
			response.sendRedirect(request.getContextPath()+"/trade/tradeCenter.jsp");
		}
		
		
		
		
	}
	
}
