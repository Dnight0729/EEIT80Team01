package items.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import items.model.dao.ItemImagesDAOjdbc;
import items.model.dao.ItemsDAOjdbc;

public class ItemsService {

	public List<ItemsBean> select (ItemsBean bean){
		ItemsDAO dao = new ItemsDAOjdbc();
		
		List<ItemsBean> result = null;
		if(bean!=null && bean.getItemId()!=0){
			ItemsBean temp = dao.selectId(bean.getItemId());
			if(temp!=null){
				result = new ArrayList<ItemsBean>();
				result.add(temp);
			}
		}else{
			result = dao.getAll();
		}
		return result;
	}
	
	public ItemsBean getOneItemId(int itemId) {
		ItemsDAO dao = new ItemsDAOjdbc();
		return dao.selectId(itemId);
	}
	
	public ItemsBean insert(ItemsBean bean, List<ImageInput> list){
		ItemsDAO dao = new ItemsDAOjdbc();
		
		ItemsBean result = null;
		if(bean!=null){
			result = dao.insert(bean, list);
		}
		return result;
	}
	
	public ItemsBean update(ItemsBean bean){
		ItemsDAO dao = new ItemsDAOjdbc();
		ItemsBean result = null;
		if(bean!=null){
			result = dao.update(bean);
		}
		return result;
	}
	
	public ItemsBean update(ItemsBean bean, List<Integer> delete,List<ImageInput> list ){
		ItemsDAO dao = new ItemsDAOjdbc();
		
		ItemsBean result = null;
		if(bean!=null){
			result = dao.update(bean, delete, list);
		}
		return result;
	}
	
	public boolean delete(int itemId){
		ItemsDAO dao = new ItemsDAOjdbc();
		
		boolean result = false;
		if(itemId != 0){
			result = dao.delete(itemId);
		}
		return result;
	}
	
	public Timer timerForEndTime(){
		Timer timer = new Timer();
		return timer;
	}
	
	public List<ItemsBean> getSeller(String seller){
		List<ItemsBean> result = null;
		ItemsDAO dao = new ItemsDAOjdbc();
		result = dao.selectSeller(seller);
		return result;
	}
	
	public List<ItemsBean> getSellerDown(String seller){
		List<ItemsBean> result = null;
		ItemsDAO dao = new ItemsDAOjdbc();
		result = dao.selectSellerDown(seller);
		return result;
	}
	
	public List<Map<String, Object>> frontpage(){
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		List<ItemsBean> list = null;
		ItemsDAO dao = new ItemsDAOjdbc();
		ItemImagesDAO iid = new ItemImagesDAOjdbc();
		list = dao.selectLatest();
		if(list!=null){
			for(ItemsBean bean:list){			
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("itemId", bean.getItemId());
				List<Integer> imageNos = iid.selectImages(bean.getItemId());
				if(imageNos!=null && !imageNos.isEmpty()){
					map.put("imageNo", imageNos.get(0));
				}
				result.add(map);
			}
		}
		return result;
	}
}
