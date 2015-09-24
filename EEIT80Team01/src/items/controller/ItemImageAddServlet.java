package items.controller;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import global.GlobalService;
import items.model.ImagesBean;
import items.model.ItemImagesService;


@WebServlet("/items/itemImageAdd.controller")
@MultipartConfig(location="",
fileSizeThreshold = 1024 * 1024,
maxFileSize=1024 * 1024 * 500,
maxRequestSize = 1024 * 1024 * 500 * 3)
public class ItemImageAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemImagesService service;
    public ItemImageAddServlet() {
        super();
    }
	@Override
	public void init() throws ServletException {
		service = new ItemImagesService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		String action = request.getParameter("action");
		if ("upload".equals(action)) {

			//存錯誤訊息
			Map<String, String> errors = new HashMap<String, String>();
			request.setAttribute("error", errors);
			
			try {
				//接收資料
				Part part = request.getPart("image");

				//驗證資料無

				//轉換無

				//永續層
				ImagesBean bean = new ImagesBean();
				bean.setItemId(6);	//寫死itemId

				FileInputStream fis = (FileInputStream) part.getInputStream();				
				long size = part.getSize();
				service.findPart(part, request);

				int insert = service.insert(bean, fis, size);
				
				if(!errors.isEmpty()){	
					request.setAttribute("bean", bean); // 含有輸入格式錯誤的bean物件,也存入request
					request.getRequestDispatcher("/items/itemImageAdd.jsp").forward(request, response);
					return;	//程式中斷
				}
				
				//轉交
				request.setAttribute("insert", insert);	// 資料庫insert成功後,正確的的bean物件,存入request
				request.getRequestDispatcher("/items/itemImageSuccess.jsp").forward(request, response);
				
			} catch (Exception e) {
				errors.put("UploadError", "請上傳圖片");
				request.getRequestDispatcher("/items/itemImageAdd.jsp").forward(request, response);
			}
		}
	

		
		
	}
}
