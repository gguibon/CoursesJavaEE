package fr.amu.models;

import java.util.UUID;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
public class Comment{
	
	@Id
	private String commentId = UUID.randomUUID().toString();
	private String commentAuthor;
	private String commentDate;
	@Column(columnDefinition = "LONGVARCHAR") // pour gerer les longs commentaires lors de l'import
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.REFRESH})
	private Product product;
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public JsonObjectBuilder toJson() {
		JsonObjectBuilder obj = Json.createObjectBuilder();
		obj.add("comment_id", commentId);
		obj.add("comment_author", commentAuthor);
		obj.add("text", text);
		obj.add("comment_date", commentDate);
		return obj;
	}
	public String getCommentAuthor() {
		return commentAuthor;
	}
	public void setCommentAuthor(String commentAuthor) {
		this.commentAuthor = commentAuthor;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}