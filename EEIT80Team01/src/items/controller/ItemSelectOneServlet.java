package items.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.category.model.ItemCategoryBean;
import items.model.ItemsBean;
import items.model.ItemsService;

@WebServlet("/items/itemSelectOne.controller")
public class ItemSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemsService service;
    public ItemSelectOneServlet() {
        super();
    }

	@Override
	public void init() throws ServletException {
		service = new ItemsService();
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
		
		if("getOne_For_Update".equals(action)){
			Map<String, String> errors = new HashMap<String, String>();
			request.setAttribute("error", errors);
			
			try {
//				//接收資料
//				String itemIdStr =  request.getParameter("itemId");
//				//格式轉換
//				int itemId = 0;
//				if(itemIdStr!=null && itemIdStr.length()!=0){
//					itemId = Integer.parseInt(itemIdStr);
//				}
//				ItemsBean selectItemId = service.getOneItemId(itemId);
//				request.setAttribute("selectItemId", selectItemId);         // 資料庫取出的bean物件,存入request
				request.getRequestDispatcher("/items/itemUpdate.jsp").forward(request, response);	
			} catch (Exception e) {
				errors.put("action", "無法取得要修改的資料");
				request.getRequestDispatcher("/items/itemList.jsp").forward(request, response);
			}
		}
		
	}

}
