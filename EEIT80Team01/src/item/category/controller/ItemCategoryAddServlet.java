package item.category.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import item.category.model.ItemCategoryBean;
import item.category.model.ItemCategoryService;

@WebServlet("/support/manage/itemCategory/itemCategoryAdd.controller")
public class ItemCategoryAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemCategoryService service;
	@Override
	public void init() throws ServletException {
		service = new ItemCategoryService();
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
		
		//接收資料
		String itemCategoryStr = request.getParameter("itemCategory");
		String categoryName = request.getParameter("categoryName");
		String categoryButton = request.getParameter("categoryButton");

		//儲存錯誤訊息
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error", errors);

		//驗證資料
		if(itemCategoryStr == null || itemCategoryStr.trim().length()==0){
			errors.put("itemCategoryError", "請輸入商品分類編號");
		}else if (!itemCategoryStr.matches("^[0-9]*[1-9][0-9]*$")) {
			errors.put("itemCategoryError", "商品分類編號請輸入零以上的正整數");
		}else if(service.checkIfItemCategoryAlreadyExist(Integer.parseInt(itemCategoryStr))){
			errors.put("itemCategoryError", "商品分類編號重複");
		}
		if(categoryName == null || categoryName.trim().length()==0){
			errors.put("categoryNameError", "請輸入商品分類名稱");
		}
		if(!errors.isEmpty()){
			RequestDispatcher rd = request.getRequestDispatcher("/support/manage/itemCategory/itemCategoryAdd.jsp");
			rd.forward(request, response);
			return;
		} 
		
		//驗證無誤，進行格式轉換
		int itemCategory = 0;
		if(itemCategoryStr!=null && itemCategoryStr.length()!=0){
			itemCategory = Integer.parseInt(itemCategoryStr);
		}
		
		
		//呼叫model
		ItemCategoryBean bean = new ItemCategoryBean();
		bean.setItemCategory(itemCategory);
		bean.setCategoryName(categoryName);
		HttpSession session = request.getSession();
		
		//根據model結果轉向view
		if(categoryButton!=null &&categoryButton.equals("Insert")){
			ItemCategoryBean result = service.insert(bean);
			if(result==null){
				session.setAttribute("action", "商品分類新增失敗");
			}else{
				session.setAttribute("Success", "商品分類新增成功");
			}			
			response.sendRedirect(request.getContextPath()+"/support/manage/itemCategory/itemCategoryList.jsp");
			
		}
		
		
	}

	
	
}
