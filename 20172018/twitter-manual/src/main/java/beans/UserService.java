package beans;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.hibernate.Session;

import database.HibernateUtil;
import launch.Main;

public class UserService {

//	private LinkedHashMap<String, Message2> messages = new LinkedHashMap<String, Message2>();
	private HibernateUtil hibernateUtil = Main.hibernateUtil;
	private Session session = hibernateUtil.getSession();

	public UserService() {
		
		try {
			checkData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addUser(User user) {
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.flush();
	}
	
	@SuppressWarnings("unchecked")
	public boolean userLogin(String name, String password) {
		session.beginTransaction();
		List<User> listUsers  = new ArrayList<User>(session.createQuery("from beans.User").list() );
		session.getTransaction().commit();
		session.flush();
		for(User user : listUsers) {
			if(user.getName().equals(name) && user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public boolean userExists(String name) {
		session.beginTransaction();
		List<User> listUsers  = new ArrayList<User>(session.createQuery("from beans.User").list() );
		session.getTransaction().commit();
		session.flush();
		for(User user : listUsers) {
			if(user.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	

	@SuppressWarnings("unchecked")
	public JsonArrayBuilder getUsersJson() {
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		session.beginTransaction();
		
		
		List<User> listUsers  = new ArrayList<User>(session.createQuery("from beans.User").list() );
		session.getTransaction().commit();
		session.flush();
		for (User user : listUsers) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			objectBuilder.add("name", user.getName());
			objectBuilder.add("pwd", user.getPassword());
			arrayBuilder.add(objectBuilder);
		}

		return arrayBuilder;
	}

	public void checkData() throws Exception {
		hibernateUtil.checkData("select * from user");
	}

}
