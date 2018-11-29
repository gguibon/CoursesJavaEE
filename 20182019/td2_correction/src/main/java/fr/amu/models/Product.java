package fr.amu.models;

public class Product{

	private int id;
	private String category;
	private String productTitle;
	private String img;
	private String description;
	private String date;
	
	public Product() {}
	
	public Product(String category, String productTitle, String img, String description, String date) {
		this.category = category;
		this.productTitle = productTitle;
		this.img = img;
		this.description = description;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}