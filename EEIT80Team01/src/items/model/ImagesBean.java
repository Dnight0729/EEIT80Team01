package items.model;

import java.sql.Blob;

public class ImagesBean {
private int itemId;
private Blob image;
private int imageNo;
public int getItemId() {
	return itemId;
}
public void setItemId(int itemId) {
	this.itemId = itemId;
}
public Blob getImage() {
	return image;
}
public void setImage(Blob image) {
	this.image = image;
}
public int getImageNo() {
	return imageNo;
}
public void setImageNo(int imageNo) {
	this.imageNo = imageNo;
}

}
