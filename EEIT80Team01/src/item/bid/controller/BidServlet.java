package item.bid.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import item.bid.model.BidLogBean;
import item.bid.model.BidService;
import member.model.MemberBean;

@WebServlet("/product/bid.do")
public class BidServlet extends HttpServlet {
	private BidService bidService = null;
	private BidLogBean bidLogBean = null;
	private int itemId = 0;
	private double bidPrice = 0;
	private Timestamp bidTime = null;
	private String buyer = null;
	public BidServlet(){
		bidService = new BidService();
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
		buyer = memberBean.getUserName();
		try {
			itemId = Integer.parseInt(request.getParameter("itemId"));
		} catch (NumberFormatException e) {
			System.out.println("NumberFormatException");
			e.printStackTrace();
		}
		bidTime = new java.sql.Timestamp(new java.util.Date().getTime());
		
		if(bidService.getSeller(itemId)!=buyer){
			
			if(action.equalsIgnoreCase("direct")){
				
				if(bidService.changeItemStatusToTwo(itemId)){
					bidLogBean = bidService.insertDirectBuyer(itemId,bidTime,buyer);
					if(bidLogBean!=null){
						request.setAttribute("message","購買成功");
						request.getRequestDispatcher("/search/itempage.jsp").forward(request, response);
					} else{
						bidService.changeItemStatusToZero(itemId);
					}
				}
				else{
					request.setAttribute("errorMsg","已售出!");
					request.getRequestDispatcher("/search/itempage.jsp").forward(request, response);
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
							request.setAttribute("message","下標成功");
							request.getRequestDispatcher("/search/itempage.jsp").forward(request,response);
						}else{
							bidService.toggleThread(itemId);
							request.setAttribute("errorMsg","錯誤!出價請高於最高下標價");
							request.getRequestDispatcher("/search/itempage.jsp").forward(request,response);
						}
					} else{
						bidService.toggleThread(itemId);
						request.setAttribute("errorMsg","下標失敗!");
						request.getRequestDispatcher("/search/itempage.jsp").forward(request,response);
					}
					
				}
				else{
					request.setAttribute("errorMsg","錯誤!其他會員下標中或拍賣已結束");
					request.getRequestDispatcher("/search/itempage.jsp").forward(request,response);
				}
				
				
			}
			
		}//check seller!=buyer
		else{
			request.setAttribute("errorMsg","對不起,您是此商品的賣家!");
			request.getRequestDispatcher("/search/itempage.jsp").forward(request, response);
		}
		
		
		
		
	}

}
