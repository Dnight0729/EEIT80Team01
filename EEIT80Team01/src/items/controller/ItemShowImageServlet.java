package items.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import items.model.ImagesBean;
import items.model.ItemImagesService;


@WebServlet("/search/showImage")
public class ItemShowImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ItemShowImageServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ImagesBean bean = null;
		InputStream is = null;
		OutputStream os = response.getOutputStream();

			//存錯誤資訊
			Map<String, String> errors = new HashMap<String, String>();
			request.setAttribute("error", errors);
			
			try {
				//接收資料
				String no = request.getParameter("imageNo");

				if(no!=null){
					if(no!=null || no.length()>0){
						int imageNo = Integer.parseInt(no);
						ItemImagesService service = new ItemImagesService();
						bean = service.selectOneImage(imageNo);
					}													
					if(bean!=null){
						is= bean.getImage().getBinaryStream();
						int count = 0;
						byte[] bytes = new byte[8192];
						while ((count = is.read(bytes)) != -1) {
							os.write(bytes, 0, count);
						}
						os.flush();
						os.close();
					}
					return;
				} 
					is = getServletContext().getResourceAsStream(
							"/imgs/NoImage.jpg");
					int count = 0;
					byte[] bytes = new byte[8192];
					while ((count = is.read(bytes)) != -1) {
						os.write(bytes, 0, count);
					}
					os.flush();
					os.close();
					return;
				
			} catch (Exception e) {
				is = getServletContext().getResourceAsStream(
						"/imgs/NoImage.jpg");
				int count = 0;
				byte[] bytes = new byte[8192];
				while ((count = is.read(bytes)) != -1) {
					os.write(bytes, 0, count);
				}
				os.flush();
				os.close();
				return;
				
			} finally{
				os.close();				
			}
		 
		
		
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
