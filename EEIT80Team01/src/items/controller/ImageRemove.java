package items.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.bid.model.BidService;
import items.model.ItemImagesService;

@WebServlet("/items/ImageRemove")
public class ImageRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private ItemImagesService service;
    public ImageRemove() {
        super();
    }
    @Override
	public void init() throws ServletException {
		service = new ItemImagesService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		
		int itemNo = 0;
		if(id!=null && id.length()!=0){
			itemNo = Integer.parseInt(id);
		}
		PrintWriter out = response.getWriter();
		if(service.delete(itemNo)){
			out.write("success");
		} else {
			out.write("fail");
		}
	}


}
