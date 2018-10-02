package fr.amu.services;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.amu.models.User;
import fr.amu.models.UserRepository;


@Service
public class UserService {

	@Autowired
	UserRepository ur;
	
	public UserService() {
	}

	
	public void addUser(User user) {
		ur.save(user);
	}
	

	public boolean userLogin(String name, String password) {
		List<User> listUsers =  (List<User>) ur.findAll() ;
		for(User user : listUsers) {
			if(user.getName().equals(name) && user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean userExists(String name) {
		List<User> listUsers =  (List<User>) ur.findAll() ;
		for(User user : listUsers) {
			if(user.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	

	
	public JsonArrayBuilder getUsersJson() {
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		List<User> listUsers =  (List<User>) ur.findAll() ;
		for (User user : listUsers) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			objectBuilder.add("name", user.getName());
			objectBuilder.add("pwd", user.getPassword());
			arrayBuilder.add(objectBuilder);
		}		
		return arrayBuilder;
	}

}
