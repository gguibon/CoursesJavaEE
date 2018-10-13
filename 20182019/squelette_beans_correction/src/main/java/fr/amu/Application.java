package fr.amu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import fr.amu.services.RendezvousService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	public RendezvousService rdvServ; // cet appel de service est pour tester l'application sans vue. Par la suite les
										// contrôleurs le feront
	@Autowired
	JdbcTemplate jdbcTemplate; // placé ici simplement pour créer la table au lancement de la méthode run() de
								// CommandLineRunner

	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
		// Utils.openBrowser("http://localhost:8090"); // pour ouvrir le navigateur par
		// défaut. Utile lorsque la vue sera disponible.
	}

	@Override
	public void run(String... strings) throws Exception {

		log.info("Tests des fonctions du service et du contenu de la BDD");
		jdbcTemplate.execute("DROP TABLE rendezvous IF EXISTS");
		jdbcTemplate.execute(
				"CREATE TABLE rendezvous(id INTEGER IDENTITY PRIMARY KEY, duree VARCHAR(255), lieu VARCHAR(255), type VARCHAR(255), personnes VARCHAR(255))");
		log.info("BDD créée");
		rdvServ.addRDV(2, "Paris", "Cours", "Gérard, Olivier");
		rdvServ.visualizeRdvs();
		rdvServ.addRDV(1, "Marseille", "AG", "Jean, Lucie, Marco");
		rdvServ.visualizeRdvs();
		rdvServ.deleteRdv(rdvServ.getRDVByID(0));
		log.info("rdv id 0 supprimé");
		rdvServ.visualizeRdvs();
		log.info(String.format("Il y a %s rendezvous dans la BDD", String.valueOf(rdvServ.getNumRdv()) ));
		log.info("Tests finis");
	}

}