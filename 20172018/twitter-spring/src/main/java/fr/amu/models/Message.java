package fr.amu.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
public class Message{

	private String content;
	private String author;
	private String date;
	@Id
	private String id = UUID.randomUUID().toString();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "message")
	@Cascade({CascadeType.REMOVE})
	private Set<Liike> likes = new HashSet<Liike>(0);
	
	public String getContent() {
		return content;
	}
	public void setContent(String contenu) {
		this.content = contenu;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public Set<Liike> getLikes() {
		return likes;
	}
	public void setLikes(Set<Liike> likes) {
		this.likes = likes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public JsonObjectBuilder toJson() {
		JsonObjectBuilder obj = Json.createObjectBuilder();
		obj.add("author", author);
		obj.add("id", id);
		obj.add("content", content);
		obj.add("date", date);
		JsonArrayBuilder likesArray = Json.createArrayBuilder();
		for (Liike like : likes) likesArray.add(like.toJson());
		obj.add("likes", likesArray);
		return obj;
	}

	

}