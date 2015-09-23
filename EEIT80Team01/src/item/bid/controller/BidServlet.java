package item.bid.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import item.bid.model.BidLogBean;
import item.bid.model.BidService;
import item.trade.model.TradeBean;
import item.trade.model.TradeDAOService;
import item.trade.model.TradeTimer;
import member.model.MemberBean;

@WebServlet("/product/bid.do")
public class BidServlet extends HttpServlet {
	
	private BidService bidService = null;
	private BidLogBean bidLogBean = null;
	private int itemId = 0;
	private double bidPrice = 0;
	private Timestamp bidTime = null;
	private String buyer = null;
	private String seller = null;
	private TradeDAOService tradeDaoService = null;
	private TradeBean tradeBean = null;
	public BidServlet(){
		bidService = new BidService();
		tradeDaoService = new TradeDAOService();
		tradeBean = new TradeBean();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean)session.getAttribute("LoginOK");
		if(memberBean!=null){
			buyer = memberBean.getUserName();	
		}
		try {
			itemId = Integer.parseInt(request.getParameter("itemId"));
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException");
			e.printStackTrace();
		}
		StringBuilder url = new StringBuilder();
		url.append(request.getContextPath());
		url.append("/search/item");
		url.append("?itemid=");
		url.append(itemId);
		bidTime = new java.sql.Timestamp(new java.util.Date().getTime());
		seller = bidService.getSeller(itemId);
		if(seller!=buyer){
			
			if(action.equalsIgnoreCase("direct")){
				
				if(bidService.changeItemStatusToTwo(itemId)){
					bidLogBean = bidService.insertDirectBuyer(itemId,bidTime,buyer);
					if(bidLogBean!=null){
						tradeBean.setItemId(itemId);
						tradeBean.setBuyer(buyer);
						tradeBean.setBuyerCheck(0);
						tradeBean.setSeller(seller);
						tradeBean.setSellerCheck(0);
						tradeBean = tradeDaoService.insert(tradeBean);
						if(tradeBean!=null){
							Timer timer = new Timer();
							timer.schedule(new TradeTimer(itemId),new java.util.Date().getTime()+7*24*60*60*1000);
							session.setAttribute("message","購買成功");
							response.sendRedirect(url.toString());
							return;
						}else{
							session.setAttribute("errorMsg","購買失敗");
							response.sendRedirect(url.toString());
							return;
						}
					} else{
						bidService.changeItemStatusToZero(itemId);
					}
				}
				else{
					session.setAttribute("errorMsg","已售出!");
					response.sendRedirect(url.toString());
					return;
				}
			}
			
			if(action.equalsIgnoreCase("bid")){
				try {
					bidPrice = Double.parseDouble(request.getParameter("bidPrice"));
				} catch (NumberFormatException e) {
					System.out.println("NumberFormatException");
					e.printStackTrace();
				}
				
				if(bidService.checkStatus(itemId)){
					bidService.toggleThread(itemId);
					if(bidPrice>=1 && bidService.validateBidPrice(bidPrice, itemId) && 
							bidService.validateBidTime(bidTime, itemId)){
						bidLogBean = new BidLogBean();
						bidLogBean.setItemId(itemId);
						bidLogBean.setBuyer(buyer);
						bidLogBean.setBidPrice(bidPrice);
						bidLogBean.setBidTime(bidTime);
						BidLogBean result = bidService.compareTopPrice(bidLogBean,itemId);
						if(result!=null){
							bidService.toggleThread(itemId);
							request.setAttribute("bean",result);
							session.setAttribute("message","下標成功");
							response.sendRedirect(url.toString());
						}else{
							bidService.toggleThread(itemId);
							session.setAttribute("errorMsg","錯誤!出價請高於最高下標價");
							response.sendRedirect(url.toString());
							return;
						}
					} else{
						bidService.toggleThread(itemId);
						session.setAttribute("errorMsg","下標失敗!");
						response.sendRedirect(url.toString());
						return;
					}
					
				}
				else{
					session.setAttribute("errorMsg","錯誤!其他會員下標中或拍賣已結束");
					response.sendRedirect(url.toString());
					return;
				}
				
				
			}
			
		}//check seller!=buyer
		else{
			session.setAttribute("errorMsg","對不起,您是此商品的賣家!");
			response.sendRedirect(url.toString());
			return;
		}
		
		
		
		
	}

}
