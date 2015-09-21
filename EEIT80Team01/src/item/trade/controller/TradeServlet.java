package item.trade.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import global.GlobalService;
import item.trade.model.TradeBean;
import item.trade.model.TradeDAOService;
import item.trade.model.TradeService;
import member.model.MemberBean;

@WebServlet("/trade/trade.do")
public class TradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TradeDAOService tradeDaoService = null;
	private TradeService tradeService = null;
	private String usr = null;
	private String seller = null;
	private TradeBean tradeBean = null;
	public TradeServlet(){
		tradeService = new TradeService();
		tradeDaoService = new TradeDAOService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("itemId");
		int itemId = Integer.parseInt(id);
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		MemberBean memberBean = (MemberBean)session.getAttribute(GlobalService.LOGIN_TOKEN);
		usr = memberBean.getUserName();
		tradeBean = tradeDaoService.getByPK(itemId);
		seller = tradeBean.getSeller();
		if(action.equalsIgnoreCase("check")){
			if(usr==seller){
				tradeBean = tradeService.changeSellerStatus(itemId,1);
				if(tradeBean!=null){
					request.setAttribute("message","交易確認完成");
				}else{
					request.setAttribute("errorMsg","交易確認失敗!");
				}
			}else{
				tradeBean = tradeService.changeBuyerStatus(itemId,1);
				if(tradeBean!=null){
					request.setAttribute("message","交易確認完成");
				}else{
					request.setAttribute("errorMsg","交易確認失敗!");
				}
			}
		}
		
		if(action.equalsIgnoreCase("cancel")){
			if(usr==seller){
				tradeBean = tradeService.changeSellerStatus(itemId,2);
				if(tradeBean!=null){
					request.setAttribute("message","交易取消完成");
				}else{
					request.setAttribute("errorMsg","交易取消失敗!");
				}
			}else{
				tradeBean = tradeService.changeBuyerStatus(itemId,2);
				if(tradeBean!=null){
					request.setAttribute("message","交易取消完成");
				}else{
					request.setAttribute("errorMsg","交易取消失敗!");
				}
			}
		}
		
		tradeBean = tradeDaoService.getByPK(itemId);
		int buyerCheck = tradeBean.getBuyerCheck();
		int sellerCheck = tradeBean.getSellerCheck();
		if(buyerCheck==2 && sellerCheck==2){
			tradeDaoService.delete(itemId);
		}
		request.getRequestDispatcher("tradeCenter.do").forward(request, response);
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
