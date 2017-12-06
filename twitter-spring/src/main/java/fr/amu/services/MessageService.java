package fr.amu.services;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.amu.models.Liike;
import fr.amu.models.Message;
import fr.amu.models.MessageRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository mr;

	
	public MessageService() {		
	}

	public void addMessage(Message message) {
		mr.save(message);
		System.out.println(getMessagesJson());
	}

	
	public Message getMessage(String id) {
		return mr.findOne(id);
		
	}

	public void removeMessage(String id) {
		mr.delete(id);
	}

	public JsonArrayBuilder getMessagesJson() {
		
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		List<Message> listMessages = (List<Message>) mr.findAllByOrderByDateAsc();
		for (Message message : listMessages) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			objectBuilder.add("author", message.getAuthor());
			objectBuilder.add("content", message.getContent());
			objectBuilder.add("date", message.getDate());
			objectBuilder.add("id", message.getId());
			JsonArrayBuilder likes = Json.createArrayBuilder();
			for (Liike like : message.getLikes()) likes.add(like.toJson());
			objectBuilder.add("likes", likes);
			arrayBuilder.add(objectBuilder);
		}

		return arrayBuilder;
	}


}
