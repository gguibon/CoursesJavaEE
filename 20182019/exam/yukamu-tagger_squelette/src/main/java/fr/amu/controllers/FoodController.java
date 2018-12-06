package fr.amu.controllers;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping(value="/food")
public class FoodController {

	private static final Logger log = LoggerFactory.getLogger(FoodController.class);
	
	@Autowired
	ServletContext context;
	
	final ObjectMapper mapper = new ObjectMapper(); // initialize un mapper qui tranforme un objet Java en JSON pour le graphique de la vue
//	mapper.writeValueAsString( Map<String, Integer> ) //
	
	// TODO : completer avec les methodes adequates
	
	
	
	
}
