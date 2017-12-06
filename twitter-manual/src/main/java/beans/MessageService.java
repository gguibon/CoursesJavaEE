package beans;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.hibernate.Session;

import database.HibernateUtil;
import launch.Main;

public class MessageService {

	private LinkedHashMap<String, Message> messages = new LinkedHashMap<String, Message>();
	private HibernateUtil hibernateUtil = Main.hibernateUtil;
	private Session session = hibernateUtil.getSession();


	public MessageService() {

		try {

			checkData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void addMessage(Message message) {
		this.messages.put(message.getId().toString(), message);
		session.beginTransaction();
		session.save(message);
		session.getTransaction().commit();
		session.flush();
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessages() {
		
		List<Message> messagesDB  = new ArrayList<Message>(session.createQuery("from Message").list() );
		for (Message msg : messagesDB) System.out.println(msg.getContent() + " " + msg.getId());
		System.out.println(messagesDB.get(0));

		return messagesDB;
	}
	
	@SuppressWarnings("unchecked")
	public Message getMessage(String id) {
		System.out.println("from beans.Message where id = '"+id+"'");
		session.beginTransaction();
		List<Message> messagesDB  = new ArrayList<Message>(session.createQuery("from beans.Message where id = '"+id+"'").list() );
		session.getTransaction().commit();
		session.flush();
		return messagesDB.get(0);
	}

	public void removeMessage(String id) {
		session.beginTransaction();
		Boolean deleted = deleteById(Message.class, id, session);
		if (deleted) this.messages.remove(id);
		session.getTransaction().commit();
		session.flush();
		System.out.println(this.getMessagesJson());
	}
	
	private boolean deleteById(Class<?> type, String id, Session session) {
	    Object persistentInstance = session.load(type, id);
	    if (persistentInstance != null) {
	        session.delete(persistentInstance);
	        return true;
	    }
	    return false;
	}

	@SuppressWarnings("unchecked")
	public JsonArrayBuilder getMessagesJson() {
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		session.beginTransaction();
		
		
		
		List<Message> listMessages  = new ArrayList<Message>(session.createQuery("from beans.Message ORDER BY date").list() );
		
		session.getTransaction().commit();
		session.flush();
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

	public void checkData() throws Exception {
		hibernateUtil.checkData("select * from message");
	}

}
