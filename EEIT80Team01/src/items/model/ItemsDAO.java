package items.model;

import java.util.List;

import items.model.ItemsBean;

public interface ItemsDAO {

	ItemsBean selectId(int itemId);

	ItemsBean selectCategory(int itemCategory);

	List<ItemsBean> getAll();

	ItemsBean insert(ItemsBean bean, List<ImageInput> list);
	
	ItemsBean update(ItemsBean bean);
	
	ItemsBean update(ItemsBean bean, List<ImageInput> list);

	public boolean delete(int itemId);
	
	boolean delete(int imageNo, int itemId);
	
	public List<ItemsBean> selectSeller(String seller);

}