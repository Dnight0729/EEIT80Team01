package items.model;

import java.io.FileInputStream;
import java.util.List;

import items.model.ImagesBean;

public interface ItemImagesDAO {

	public int insert(ImagesBean bean, List<ImageInput> list);

	int update(ImagesBean bean, FileInputStream fis, long size);

	boolean delete(int itemNo);

	List<ImagesBean> selectOneItem(int itemId);
	
	ImagesBean selectOneImage(int imageNo);
	
	List<Integer> selectImages(int itemId);

}