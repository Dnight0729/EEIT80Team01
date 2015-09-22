package items.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

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
	
	
	
	
	
	
	
	
	
	
	
	
//	public boolean changeItemStatusDown(int itemId){
//		ItemsBean bean = null;
//		ItemsDAO dao = new ItemsDAOjdbc();
//		
//		bean = dao.selectId(itemId);
//		if(bean!=null && bean.getItemStatus()==0){
//			bean.setItemStatus(1);
//			bean = dao.update(bean);
//			return true;
//		}
//		return false;
//	}
	
//	public boolean changeItemStatusSell(int itemId){
//		ItemsBean bean = null;
//		ItemsDAO dao = new ItemsDAOjdbc();
//		
//		bean = dao.selectId(itemId);
//		if(bean!=null && bean.getItemStatus()==0){
//			bean.setItemStatus(2);
//			bean = dao.update(bean);
//			return true;
//		}
//		return false;
//	}
}
