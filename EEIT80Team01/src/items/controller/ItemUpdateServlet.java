package items.controller;


import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import global.GlobalService;
import items.model.ItemsBean;
import items.model.ItemsService;
import member.model.MemberBean;

@WebServlet("/items/itemUpdate.controller")
public class ItemUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemsService service;

    public ItemUpdateServlet() {
    	service = new ItemsService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();	//用來抓取seller資料用
		MemberBean memberBean = (MemberBean)session.getAttribute(GlobalService.LOGIN_TOKEN);
		String userName=null;
		
		if(memberBean!=null){
			userName = memberBean.getUserName();
		}
		String action = request.getParameter("action");
		if ("update".equals(action)) { // 來自itemUpdate.jsp的請求	
			Map<String, String> errors = new HashMap<String, String>();
			request.setAttribute("error", errors);
			try {
				//接收資料
				String itemCategorySelect = request.getParameter("itemCategory");	//使用select方式讓會員使用
				String title = request.getParameter("title");
				String startPriceStr = request.getParameter("startPrice");
				String directPriceStr = request.getParameter("directPrice");
				String bidStr = request.getParameter("bid");
				String endTimeStr = request.getParameter("endTime");
				String itemDescribe = request.getParameter("itemDescribe");
				String itemIdStr = request.getParameter("itemId");
				//資料驗證
				if(userName==null || userName.trim().length()==0){
					errors.put("loginError", "請重新登入");
				}
				if(itemCategorySelect==null || itemCategorySelect.length()==0){
					errors.put("itemCategoryError", "請選擇一項商品分類");
				}	
				if(title==null || title.trim().length()==0){
					errors.put("titleError", "請輸入商品名稱");
				}
				if(startPriceStr==null || startPriceStr.trim().length()==0){
					errors.put("startPriceError", "請輸入起標價格");
				}else if (!startPriceStr.matches("^[0-9]*[1-9][0-9]*$")) {
					errors.put("startPriceError", "起標價格必須為零以上的正整數");
				}
				if(directPriceStr==null || directPriceStr.trim().length()==0){
					errors.put("directPriceError", "請輸入直購價格");
				}else if (!directPriceStr.matches("^[0-9]*[1-9][0-9]*$")) {
					errors.put("directPriceError", "直購價格必須為零以上的正整數");
				}
				if(bidStr==null || bidStr.trim().length()==0){
					errors.put("bidError", "請輸入每次加價金額");
				}else if (!directPriceStr.matches("^[0-9]*[1-9][0-9]*$")) {
					errors.put("bidError", "加價金額必須為零以上的正整數");
				}
				if(endTimeStr==null || endTimeStr.trim().length()==0){
					errors.put("endTimeError", "請點擊選擇結標時間");
				}	
				if(itemDescribe==null || itemDescribe.trim().length()==0){
					itemDescribe = "";
				}
				if(!errors.isEmpty()){
					RequestDispatcher rd = request.getRequestDispatcher("/items/itemUpdate.jsp");
					rd.forward(request, response);
					return;
				}
				//格式轉換
				int itemCategory = 0;
				if(itemCategorySelect!=null && itemCategorySelect.length()!=0){
					itemCategory = Integer.parseInt(itemCategorySelect);
				}
				double startPrice = 0;
				if(startPriceStr!=null && startPriceStr.length()!=0){
					startPrice = Double.parseDouble(startPriceStr);
				}
				double directPrice = 0;
				if(directPriceStr!=null && directPriceStr.length()!=0){
					directPrice = Double.parseDouble(directPriceStr);
				}
				int bid = 0;
				if(bidStr!=null && bidStr.length()!=0){
					bid = Integer.parseInt(bidStr);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Timestamp endTime = null;
				if(endTimeStr!=null && endTimeStr.length()!=0){
					try {
						endTime = new Timestamp(sdf.parse(endTimeStr).getTime());
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				int itemId = 0;
				if(itemIdStr!=null && itemIdStr.length()!=0){
					itemId = Integer.parseInt(itemIdStr);
				}
				
				ItemsBean bean = new ItemsBean();
				bean.setSeller(userName);
				bean.setItemCategory(itemCategory);
				bean.setTitle(title);
				bean.setStartPrice(startPrice);
				bean.setDirectPrice(directPrice);
				bean.setBid(bid);
				bean.setEndTime(endTime);
				bean.setItemDescribe(itemDescribe);
				bean.setItemStatus(0);
				bean.setThreadLock(0);
				bean.setItemId(itemId);					
				
				ItemsBean update = service.update(bean);
//				request.setAttribute("update", update); // 資料庫update成功後,正確的的bean物件,存入request
				
				
//				if (!errors.isEmpty()) {
//					request.setAttribute("bean", bean); // 含有輸入格式錯誤的bean物件,也存入request
//					RequestDispatcher failureView = request
//							.getRequestDispatcher("/items/itemList.jsp");
//					failureView.forward(request, response);
//					return; //程式中斷
//				}
				
				
				
				request.getRequestDispatcher("/items/itemList").forward(request, response); // 修改成功後,轉交itemList.jsp
			} catch (Exception e) {
				errors.put("action", "修改資料失敗");
				request.getRequestDispatcher("/items/itemUpdate.jsp").forward(request, response);
			}
		}
	}

}
