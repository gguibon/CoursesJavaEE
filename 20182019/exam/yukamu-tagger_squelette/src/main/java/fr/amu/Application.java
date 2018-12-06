package fr.amu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
		// Utils.openBrowser("http://localhost:8090"); // pour ouvrir le navigateur par
		// défaut. Utile lorsque la vue sera disponible.
	}

}