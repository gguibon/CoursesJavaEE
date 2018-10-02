package fr.amu.services;

import java.util.List;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.amu.models.Comment;
import fr.amu.models.CommentRepository;
import fr.amu.models.Product;

@Service
public class CommentService {


	@Autowired
	CommentRepository cr;
	
	public CommentService() {
	}

	public JsonObjectBuilder addComment(Product product, String text, String date, String author) {
		Comment com = new Comment();
    	com.setText(text);
    	com.setCommentDate(date);
    	com.setProduct(product);
    	com.setCommentAuthor(author);
		cr.save(com);
		return com.toJson();
	}
	
	public JsonObjectBuilder addComment(Comment com) {
		cr.save(com);
		return com.toJson();
	}
	
	public void deleteComment(String author, String id) {
		Comment com = cr.findOne(id);
		if((com.getCommentAuthor().equals(author)) || (author.equals("admin"))) {
			System.out.println("deleting");
			cr.delete(id);
		}
	}

	public JsonArrayBuilder getCommentsJson() {
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

		List<Comment> comments = (List<Comment>) cr.findAllByOrderByCommentDateAsc();
		for (Comment comment : comments) {
			JsonObjectBuilder objectBuilder = comment.toJson();
			objectBuilder.add("product_id", comment.getProduct().getId());
			arrayBuilder.add(objectBuilder);
		}

		return arrayBuilder;
	}
	
	public JsonArrayBuilder getCommentsJson(Product product) {
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		Set<Comment> comments = product.getComments();
		for (Comment comment : comments) arrayBuilder.add(comment.toJson());
		return arrayBuilder;
	}

}
