package items.controller;

import item.bid.model.BidLogBean;
import item.bid.model.BidLogDAOService;
import item.category.model.ItemCategoryBean;
import item.category.model.ItemCategoryService;
import items.model.ItemImagesService;
import items.model.ItemsBean;
import items.model.ItemsService;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberBean;
import member.model.MemberService;


@WebServlet("/search/item")
public class ItemPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ItemPageServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String itemid = request.getParameter("itemid");
		ItemsBean bean=null;
		ItemCategoryBean icb=null;
		List<Integer> images=null;
		MemberBean mb = null;
		double price=0;
		try {
			int itemId = Integer.parseInt(itemid);
			ItemsService service = new ItemsService();
			bean = service.getOneItemId(itemId);
			if(bean!=null){
				ItemCategoryService ics = new ItemCategoryService();
				icb = ics.getOneCategory(bean.getItemCategory());
				BidLogDAOService bls = new BidLogDAOService();
				BidLogBean blb = bls.getTopPrice(itemId);
				ItemImagesService iis = new ItemImagesService();
				images = iis.selectImagesNumbers(itemId);
				if(blb!=null){
					price = blb.getBidPrice();
				} else{
				 	price = bean.getStartPrice();
				}
				MemberService ms = new MemberService();
				mb = ms.findMemberData(bean.getSeller());
						
			}
		} catch (NumberFormatException e) {
			bean = null;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/search/itempage.jsp");
		request.setAttribute("item",bean);
		request.setAttribute("itemCategory", icb);
		request.setAttribute("price", price);
		request.setAttribute("images", images);
		request.setAttribute("member", mb);
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
