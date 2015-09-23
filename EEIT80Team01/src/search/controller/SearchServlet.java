package search.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import items.model.ItemImagesService;
import items.model.ItemsBean;
import search.model.SearchService;
@WebServlet("/search/searchItems.do")
public class SearchServlet extends HttpServlet {
	public SearchServlet(){
		searchService = new SearchService();
		itemImgService = new ItemImagesService();
	}
	private ItemImagesService itemImgService = null;
	private SearchService searchService = null;
	private List<ItemsBean> beans = null;
	private Map<Integer,Integer> imgNumMap = null;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword="";
		int option =-100;
		try {
			option = Integer.parseInt(request.getParameter("option"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		 keyword = request.getParameter("keyword");
		 imgNumMap = new HashMap<Integer,Integer>();
		if(option==-100){
			beans = searchService.getItemsByKeyword(keyword);
			if(beans!=null && !beans.isEmpty()){
				for(ItemsBean bean:beans){
					int id = bean.getItemId();
					Integer itemId = id;
					List<Integer> imgNums = itemImgService.selectImagesNumbers(id);
					if(!imgNums.isEmpty()){
						Integer imgNum = imgNums.get(0);
						imgNumMap.put(itemId,imgNum);
					}
				}
				request.setAttribute("imgNumMap",imgNumMap);
				request.setAttribute("items",beans);
				request.getRequestDispatcher("/search/search.jsp").forward(request, response);
			}else{
				request.setAttribute("error","查無商品");
				request.getRequestDispatcher("/search/search.jsp").forward(request, response);
			}
		}else{
			beans = searchService.getItemsWithOption(option, keyword);
			if(beans!=null && !beans.isEmpty()){
				for(ItemsBean bean:beans){
					int id = bean.getItemId();
					Integer itemId = id;
					List<Integer> imgNums = itemImgService.selectImagesNumbers(id);
					if(!imgNums.isEmpty()){
						Integer imgNum = imgNums.get(0);
						imgNumMap.put(itemId,imgNum);
					}
				}
				request.setAttribute("imgNumMap",imgNumMap);
				request.setAttribute("items",beans);
				request.getRequestDispatcher("/search/search.jsp").forward(request, response);
			}else{
				request.setAttribute("error","查無商品");
				request.getRequestDispatcher("/search/search.jsp").forward(request, response);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
}
