package fr.amu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.amu.services.CommentService;
import fr.amu.services.ProductService;
import fr.amu.services.UserService;
import fr.amu.util.Utils;


@SpringBootApplication
public class Application {

	
	public static UserService userService = new UserService();
	public static ProductService productService = new ProductService();
	public static CommentService commentService = new CommentService();
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
		
		Utils.openBrowser("http://localhost:8090");
		
	}
	

}