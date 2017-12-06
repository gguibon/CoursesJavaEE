package fr.amu.services;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.amu.models.Liike;
import fr.amu.models.LiikeRepository;
import fr.amu.models.Message;

@Service
public class LiikeService {


	@Autowired
	LiikeRepository lr;
	
	public LiikeService() {
	}

	public void addLiike(Liike like) {
		lr.save(like);
		
	}

	private List<Liike> findByIdAndLiikeAuthor(String id, String liikeAuthor) {
		List<Liike> liikesDB = new ArrayList<Liike>();
		for(Liike like : lr.findAll()) {
			if((like.getMessage().getId().equals(id))&&(like.getLiikeAuthor().equals(liikeAuthor)))
				liikesDB.add(like);
		}
		return liikesDB;
	}
	
	public Boolean liikeExists(String id, String liikeAuthor) {
		List<Liike> liikesDB = findByIdAndLiikeAuthor(id, liikeAuthor);
		return liikesDB.isEmpty();
	}


	public void toggleLiike(Message msn, String id, String liikeAuthor) {

		List<Liike> liikesDB = findByIdAndLiikeAuthor(id, liikeAuthor);
		System.out.println(getLiikesJson().build().toString());

		if (liikesDB.isEmpty()) {
			Liike like = new Liike();
			like.setLiikeAuthor(liikeAuthor);
			like.setMessage(msn);
			msn.getLikes().add(like);
			lr.save(like);
			System.out.println(like.toJson().build().toString());
		} else {
			lr.delete(liikesDB.get(0).getLiikeId());
			System.out.println(liikesDB.get(0).toJson().build().toString());
		}
		System.out.println(getLiikesJson().build().toString());
	}

	public JsonArrayBuilder getLiikesJson() {
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

		List<Liike> listLikes = (List<Liike>) lr.findAll();
		for (Liike like : listLikes) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			objectBuilder.add("like_id", like.getLiikeId().toString());
			objectBuilder.add("liike_author", like.getLiikeAuthor());
			objectBuilder.add("message_id", like.getMessage().getId());
			arrayBuilder.add(objectBuilder);
		}

		return arrayBuilder;
	}

}
