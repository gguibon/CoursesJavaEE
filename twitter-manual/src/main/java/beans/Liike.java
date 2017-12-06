package beans;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

public class Liike implements java.io.Serializable{

	

	private static final long serialVersionUID = 1L;
	private Long liike_id = new Long("1234");
	private String liike_author ;
	private Message message;
	
	
	public Long getLiike_id() {
		return liike_id;
	}
	public void setLiike_id(Long liike_id) {
		this.liike_id = liike_id;
	}

	public String getLiike_author() {
		return liike_author;
	}
	public void setLiike_author(String liike_author) {
		this.liike_author = liike_author;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public JsonObjectBuilder toJson() {
		JsonObjectBuilder obj = Json.createObjectBuilder();
		obj.add("liike_id", liike_id);
		obj.add("liike_author", liike_author);
		return obj;
	}

	
	
	
	

	

}