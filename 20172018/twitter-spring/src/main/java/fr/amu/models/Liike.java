package fr.amu.models;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
public class Liike{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long liikeId;
	private String liikeAuthor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.REFRESH})
	private Message message;
	
	

	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public JsonObjectBuilder toJson() {
		JsonObjectBuilder obj = Json.createObjectBuilder();
		obj.add("liike_id", liikeId);
		obj.add("liike_author", liikeAuthor);
		return obj;
	}
	public String getLiikeAuthor() {
		return liikeAuthor;
	}
	public void setLiikeAuthor(String liikeAuthor) {
		this.liikeAuthor = liikeAuthor;
	}
	public Long getLiikeId() {
		return liikeId;
	}
	public void setLiikeId(Long liikeId) {
		this.liikeId = liikeId;
	}

	
	
	
	

	

}