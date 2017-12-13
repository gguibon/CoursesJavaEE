package fr.amu.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
public class Product{

	
	@Id
	private String id = UUID.randomUUID().toString();
	private String category;
	private String productTitle;
	private String img;
	private String description;
	private String date;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	@Cascade({CascadeType.REMOVE})
	private Set<Comment> comments = new HashSet<Comment>(0);
	
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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

	public JsonObjectBuilder toJson() {
		JsonObjectBuilder obj = Json.createObjectBuilder();
		obj.add("id", id);
		obj.add("productTitle", productTitle);
		obj.add("category", category);
		obj.add("img", img);
		obj.add("comments", comments.size());
		obj.add("description", description);
		return obj;
	}
	
	

}