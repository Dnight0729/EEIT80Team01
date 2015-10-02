package items.model;

import java.util.List;

import items.model.ItemsBean;

public interface ItemsDAO {

	ItemsBean selectId(int itemId);

	List<ItemsBean> selectCategory(int itemCategory);

	List<ItemsBean> getAll();

	ItemsBean insert(ItemsBean bean, List<ImageInput> list);
	
	ItemsBean update(ItemsBean bean);
	
	ItemsBean update(ItemsBean bean, List<Integer> deletes, List<ImageInput> list);

	boolean delete(int itemId);
	
	List<ItemsBean> selectThreeBySeller(String username);
	
	boolean delete(int imageNo, int itemId);
	
	public List<ItemsBean> selectSeller(String seller);
	
	public List<ItemsBean> selectSellerDown(String seller);
	
	public List<ItemsBean> selectSellerSold(String seller);
	
	List<ItemsBean> selectLatest();


}
