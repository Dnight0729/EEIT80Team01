package items.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import global.GlobalService;
import items.model.ImageInput;
import items.model.ItemTimer;
import items.model.ItemsBean;
import items.model.ItemsService;
import member.model.MemberBean;

@WebServlet("/items/itemAdd.controller")
@MultipartConfig(maxFileSize=1024 * 1024 * 500)
public class ItemsAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemsService service;
	
	@Override
	public void init() throws ServletException {
		service = new ItemsService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//需要進行登入在操作，不然會爆500
		HttpSession session = request.getSession();	//用來抓取seller資料用
		MemberBean memberBean = (MemberBean)session.getAttribute(GlobalService.LOGIN_TOKEN);
		String userName=null;
		if(memberBean!=null){
			userName = memberBean.getUserName();
		}
		
		
		// 1. 讀取使用者輸入資料
		String itemCategorySelect = request.getParameter("itemCategory");	//使用select方式讓會員使用
		String title = request.getParameter("title");
		String startPriceStr = request.getParameter("startPrice");
		String directPriceStr = request.getParameter("directPrice");
		String bidStr = request.getParameter("bid");
		String endTimeStr = request.getParameter("endTime");
		String itemDescribe = request.getParameter("itemDescribe");
		//新增按鈕
		String itemsButton = request.getParameter("itemsButton");
		Part[] parts = new Part[3];

		try {
			parts[0] = request.getPart("image1");
			parts[1] = request.getPart("image2");
			parts[2] = request.getPart("image3");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// 存放錯誤訊息物件
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error", errors);
		
		// 2. 檢查使用者輸入資料
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
				
		if(errors!=null && !errors.isEmpty()){
			RequestDispatcher rd = request.getRequestDispatcher("/items/itemAdd.jsp");
			rd.forward(request, response);
			return;
		}
		
		
		// 3. 進行必要的資料轉換
		
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
		
		//呼叫Model
		ItemsBean bean = new ItemsBean();
		bean.setSeller(userName);	
		bean.setItemCategory(itemCategory);	
		bean.setTitle(title);
		bean.setStartPrice(startPrice);
		bean.setDirectPrice(directPrice);
		bean.setBid(bid);
		bean.setEndTime(endTime);
		bean.setItemDescribe(itemDescribe);
		bean.setItemStatus(0);	//上架為0
		bean.setThreadLock(0);	//預設為0
		
		List<ImageInput> list = new ArrayList<ImageInput>();							
		for(Part part: parts ){
			try {
				
				ImageInput input= new ImageInput();
				input.setFis((FileInputStream) part.getInputStream());				
				input.setSize(part.getSize());
				list.add(input);
			} catch (Exception e) {
			}
		}

		
		//根據Model執行結果導向View
		if(itemsButton!=null &&itemsButton.equals("Insert")){
			ItemsBean result = service.insert(bean, list);
			
			Date date = new Date(endTime.getTime());
			Timer timer = service.timerForEndTime();
			timer.schedule(new ItemTimer(result), date);
			
			if(result==null){
				errors.put("action", "商品上架錯誤");
			}else{
				request.setAttribute("insert", result);
			}
			request.getRequestDispatcher("/items/itemSuccess.jsp").forward(request, response);
		}
		
		
		
		
		
		
	}

}
