package fr.amu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.amu.services.LiikeService;
import fr.amu.services.MessageService;
import fr.amu.services.UserService;


@SpringBootApplication
public class Application {

	
	public static UserService userService = new UserService();
	public static MessageService messageService = new MessageService();
	public static LiikeService liikeService = new LiikeService();
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
		
		// ici nous pourrions ajouter l'ouverture automatique du navigateur par défaut
		
	}
	

}