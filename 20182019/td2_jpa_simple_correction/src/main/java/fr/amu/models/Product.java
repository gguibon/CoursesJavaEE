package fr.amu.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // indique qu'il s'agit d'une entité à persister
public class Product{

	@Id // indique la clé primaire
    @GeneratedValue(strategy=GenerationType.AUTO) // indique la façon dont la clé primaire est auto générée
	private Long id; // id changé en Long pour gérer les très id possibles
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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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

	@Override
	public String toString() {
		return String.format("Prorduct[id=%s, category=%s, productTitle=%s, img=%s, description=%s, date=%s]", 
				String.valueOf(id), category, productTitle, img, description, date);
	}
	
}