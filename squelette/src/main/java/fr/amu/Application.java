package fr.amu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import fr.amu.util.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
//        Utils.openBrowser("http://localhost:8090"); // pour ouvrir le navigateur par défaut au démarrage (sans implements CommandLineRunner)
    }

    @Override
    public void run(String... strings) throws Exception {
    	log.info("run something");
    }
}