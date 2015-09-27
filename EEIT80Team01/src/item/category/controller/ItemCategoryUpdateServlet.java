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

@WebServlet("/support/manage/itemCategory/itemCategoryUpdate.controller")
public class ItemCategoryUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemCategoryService service;
	@Override
	public void init() throws ServletException {
		service = new ItemCategoryService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if ("update".equals(action)) { // 來自itemCategoryUpdate.jsp的請求	
			Map<String, String> errors = new HashMap<String, String>();
			request.setAttribute("error", errors);
		
			try {
				String itemCategoryStr =  request.getParameter("itemCategory");
				String categoryName = request.getParameter("categoryName");
				if(categoryName == null || categoryName.trim().length()==0){
					errors.put("categoryNameError", "請輸入商品分類名稱");
				}
				if(errors!=null && !errors.isEmpty()){
					request.setAttribute("error", errors);
					RequestDispatcher rd = request.getRequestDispatcher("/support/manage/itemCategory/itemCategoryUpdate.jsp");
					rd.forward(request, response);
					return;
				} 
				//格式轉換
				int itemCategory = 0;
				if(itemCategoryStr!=null && itemCategoryStr.length()!=0){
					itemCategory = Integer.parseInt(itemCategoryStr);
				}
				
				ItemCategoryBean bean = new ItemCategoryBean();
				bean.setItemCategory(itemCategory);
				bean.setCategoryName(categoryName);
				bean = service.update(bean);
				if(bean!=null){
					session.setAttribute("Success", "修改資料成功");
					response.sendRedirect(request.getContextPath()+"/support/manage/itemCategory/itemCategoryList.jsp");
				}else{
					session.setAttribute("Failure", "修改資料失敗");
					response.sendRedirect(request.getContextPath()+"/support/manage/itemCategory/itemCategoryList.jsp");
				}
			} catch (Exception e) {
				session.setAttribute("Failure", "修改資料失敗");
				response.sendRedirect(request.getContextPath()+"/support/manage/itemCategory/itemCategoryList.jsp");
			}
		}
	}
}
