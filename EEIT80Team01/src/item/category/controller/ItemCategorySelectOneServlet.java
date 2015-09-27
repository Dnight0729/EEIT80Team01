package item.category.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import item.category.model.ItemCategoryBean;
import item.category.model.ItemCategoryService;

@WebServlet("/support/manage/itemCategory/itemCategorySelectOne.controller")
public class ItemCategorySelectOneServlet extends HttpServlet {
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
			
			String action = request.getParameter("action");
			
			if ("getOne_For_Update".equals(action)) { // 來自itemCategoryList.jsp的請求

				Map<String, String> errors = new HashMap<String, String>();
				request.setAttribute("error", errors);
				HttpSession session = request.getSession();
				try {
					/***************************1.接收請求參數****************************************/
					String itemCategoryStr =  request.getParameter("itemCategory");
					
					/***************************2.開始查詢資料****************************************/
					//格式轉換
					int itemCategory = 0;
					if(itemCategoryStr!=null && itemCategoryStr.length()!=0){
						itemCategory = Integer.parseInt(itemCategoryStr);
					}
					
					ItemCategoryBean selectCategoryId = service.getOneCategory(itemCategory);
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					request.setAttribute("selectCategoryId", selectCategoryId);         // 資料庫取出的bean物件,存入request
					String url = "/support/manage/itemCategory/itemCategoryUpdate.jsp";
					request.getRequestDispatcher(url).forward(request, response);	// 成功轉交 itemCategoryUpdate.jsp
					

					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					session.setAttribute("Failure", "無法讀取資料");
					response.sendRedirect(request.getContextPath()+"/support/manage/itemCategory/itemCategoryList.jsp");
				}
			}
		}	
}
