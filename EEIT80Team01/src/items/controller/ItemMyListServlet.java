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


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//取得session
		HttpSession session = request.getSession();	//用來抓取seller資料用
		MemberBean memberBean = (MemberBean)session.getAttribute(GlobalService.LOGIN_TOKEN);
		String userName = memberBean.getUserName();
//		System.out.println("userName="+userName);
		
		ItemsService dao = new ItemsService();
		BidLogDAOService bidDaoSvc = new BidLogDAOService();
		ItemImagesService imgSvc = new ItemImagesService();
		//取出seller欄位資料
		List<ItemsBean> getseller = dao.getSeller(userName);
//		System.out.println("getseller="+getseller);	檢查取得seller資料
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
				image = images.get(0);
//				System.out.println("image="+image);	//測試圖片編號
				map.put("image", image);
			}else{
				getServletContext().getResourceAsStream("/imgs/NoImage.jpg");
			}

//			System.out.println("itemIdxxx="+itemId);	//檢查有無抓到itemId
			String buyer = "尚未有人出價";
			double price = 0;
			BidLogBean topPrice = bidDaoSvc.getTopPrice(itemId);
			if(topPrice!=null){
//				System.out.println(topPrice.getBidPrice());	//測試抓到的最高金額為多少
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

//		System.out.println("list="+list);	//檢查有無抓到list資料
		request.setAttribute("list", list);
				
		request.getRequestDispatcher("/items/itemList.jsp").forward(request, response);
	}

	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
