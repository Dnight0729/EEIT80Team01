package items.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import item.bid.model.BidService;


@WebServlet("/items/itemDel.controller")
public class ItemHideServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BidService service;
    public ItemHideServlet() {
        super();
    }

	@Override
	public void init() throws ServletException {
		service = new BidService();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String action = request.getParameter("action");
		
		//商品刪除(隱藏)
		if("itemDel".equals(action)){
			Map<String, String> errors = new HashMap<String, String>();
			request.setAttribute("error", errors);
			
			try {
				//接收參數
				String DelBtnStr = request.getParameter("DelBtn");
				//轉換格式
				int DelBtn = 0;
				if(DelBtnStr!=null && DelBtnStr.length()!=0){
					DelBtn = Integer.parseInt(DelBtnStr);
				}
				//呼叫永續層
				service.changeItemStatusToHide(DelBtn);
				
				//轉交
				String url = "/items/itemList";
				request.getRequestDispatcher(url).forward(request, response);
			} catch (NumberFormatException e) {
				errors.put("deleteError", "商品下架失敗");
				request.getRequestDispatcher("/items/itemList").forward(request, response);
			}
			
		}
	}

}
