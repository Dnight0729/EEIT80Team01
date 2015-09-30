package items.model;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import items.model.dao.ItemImagesDAOjdbc;

public class ItemImagesService {
	public int insert(ImagesBean bean, List<ImageInput> list) throws FileNotFoundException{
		ItemImagesDAO dao = new ItemImagesDAOjdbc();
			
			int result = 0;
			if(bean!=null){
				result = dao.insert(bean, list);
			}
			return result;
	}
	
	public void findPart(Part part, HttpServletRequest request) throws IOException{
		InputStream fis = part.getInputStream();
		long size = part.getSize();
	}
	
	public int update(ImagesBean bean, FileInputStream fis, long size){
		ItemImagesDAO dao = new ItemImagesDAOjdbc();
		
		int result = 0;
		if(bean!=null){
			result = dao.update(bean, fis, size);
		}
		return result;
	}
	
	public boolean delete(int itemNo){
		ItemImagesDAO dao = new ItemImagesDAOjdbc();
		
		boolean result =false;
		if(itemNo>0){
			result = dao.delete(itemNo);
		}
		return result;
	}
	
	public List<ImagesBean> selectItemImages(int itemId){
		ItemImagesDAO dao = new ItemImagesDAOjdbc();
		
		List<ImagesBean> result = null;
		if(itemId>0){
			result = dao.selectOneItem(itemId);
		}
		return result;
	}
	
	public List<Integer> selectImagesNumbers(int itemId){
		ItemImagesDAO dao = new ItemImagesDAOjdbc();
		List<Integer> result = dao.selectImages(itemId);
		
		return result;
	}
	
	public ImagesBean selectOneImage(int imageNo){
		ItemImagesDAO dao = new ItemImagesDAOjdbc();
		
		ImagesBean result = dao.selectOneImage(imageNo);
		return result;
	}
	
	
	
}
