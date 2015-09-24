package items.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.category.model.ItemCategoryBean;
import item.category.model.ItemCategoryService;
import items.model.ItemImagesService;
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
			ItemCategoryService service = new ItemCategoryService();
			List<ItemCategoryBean> categoryList = service.selectCategory(null);
			request.setAttribute("categoryList", categoryList);
			ItemImagesService imgSvc = new ItemImagesService();
			try {
				String itemIdStr =  request.getParameter("itemId");
				int itemId = Integer.parseInt(itemIdStr);
				List<Integer> images = imgSvc.selectImagesNumbers(itemId);
				List<Integer> input = new ArrayList<Integer>();
				for(int i=0; i<3; i++){
					if(!images.isEmpty()){
						input.add(images.get(0));
						images.remove(0);
					}else{
						input.add(null);
					}
				}
				request.setAttribute("images", input);
				request.getRequestDispatcher("/items/itemUpdate.jsp").forward(request, response);	
			} catch (Exception e) {
				errors.put("action", "無法取得要修改的資料");
				request.getRequestDispatcher("/items/itemList.jsp").forward(request, response);
			}
		}
		
	}

}
