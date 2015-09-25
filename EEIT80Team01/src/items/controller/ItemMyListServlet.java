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
import javax.servlet.http.HttpSession;

import global.GlobalService;
import item.bid.model.BidLogBean;
import item.bid.model.BidLogDAOService;
import item.bid.model.BidService;
import items.model.ImagesBean;
import items.model.ItemImagesService;
import items.model.ItemsBean;
import items.model.ItemsService;
import member.model.MemberBean;

@WebServlet("/items/itemList")
public class ItemMyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ItemsService service;

    public ItemMyListServlet() {
    	service = new ItemsService();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();	//用來抓取seller資料用
		MemberBean memberBean = (MemberBean)session.getAttribute(GlobalService.LOGIN_TOKEN);
		String userName=null;
		if(memberBean!=null){
			userName = memberBean.getUserName();
		}
		
		ItemsService dao = new ItemsService();
		BidLogDAOService bidDaoSvc = new BidLogDAOService();
		ItemImagesService imgSvc = new ItemImagesService();
		//會員上架區塊
		List<ItemsBean> getseller = dao.getSeller(userName);	//取出seller欄位資料
		List<Object> list = new ArrayList<Object>();
		int itemId = 0;
		List<Integer> images = null;
		Integer image = null;
		for(ItemsBean findItemId: getseller){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("itemsBean", findItemId);
			itemId = findItemId.getItemId();

			images = imgSvc.selectImagesNumbers(itemId);
			if(!images.isEmpty()){
				image = images.get(0);	//抓商品第一張圖
				map.put("image", image);
			}else{
				getServletContext().getResourceAsStream("/imgs/NoImage.jpg");
			}

			String buyer = "尚未有人出價";
			double price = 0;
			BidLogBean topPrice = bidDaoSvc.getTopPrice(itemId);
			if(topPrice!=null){
				price = topPrice.getBidPrice();
				buyer = topPrice.getBuyer();
			} else {
				price = findItemId.getStartPrice();
			}
			int count = 0;
			int itemCount = bidDaoSvc.getItemBidCount(itemId);
			if(itemCount!=0){
				count = itemCount;
			}else{
				count = 0;
			}
			map.put("count", count);
			map.put("price", price);
			map.put("buyer", buyer);
			list.add(map);
		}

		//會員下架區塊
		List<ItemsBean> getsellerDown = dao.getSellerDown(userName);
		List<Object> listDown = new ArrayList<Object>();
		int itemIdDown = 0;
		List<Integer> imagesDown = null;
		Integer imageDown = null;
		for(ItemsBean findItemId: getsellerDown){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("itemsBean", findItemId);
			itemIdDown = findItemId.getItemId();

			imagesDown = imgSvc.selectImagesNumbers(itemIdDown);
			if(!imagesDown.isEmpty()){
				imageDown = imagesDown.get(0);	//抓商品第一張圖
				map.put("image", imageDown);
			}else{
				getServletContext().getResourceAsStream("/imgs/NoImage.jpg");
			}

			String buyer = "尚未有人出價";
			double price = 0;
			BidLogBean topPrice = bidDaoSvc.getTopPrice(itemIdDown);
			if(topPrice!=null){
				price = topPrice.getBidPrice();
				buyer = topPrice.getBuyer();
			} else {
				price = findItemId.getStartPrice();
			}
			int count = 0;
			int itemCount = bidDaoSvc.getItemBidCount(itemIdDown);
			if(itemCount!=0){
				count = itemCount;
			}else{
				count = 0;
			}
			map.put("count", count);
			map.put("price", price);
			map.put("buyer", buyer);
			listDown.add(map);
		}
		request.setAttribute("listPac", list);
		request.setAttribute("listDownPac", listDown);
				
		request.getRequestDispatcher("/items/itemList.jsp").forward(request, response);
	}


}
