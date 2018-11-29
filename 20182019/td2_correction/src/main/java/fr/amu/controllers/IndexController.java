package fr.amu.controllers;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import fr.amu.Application;
import fr.amu.services.ProductService;

@Controller
public class IndexController {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	@Autowired 
	private HttpSession httpSession;

	@Autowired
	ServletContext context;
	
	@Autowired
	ProductService ps;
	
    @GetMapping("/")
    public String index(Map<String, Object> model) {
    	String sessionUser= (String) httpSession.getAttribute("user");
    	model.put("products", ps.getProducts() ); // la map model est l'équivalent de HttpResponse, c'est là qu'on met les attributs
		
		log.info("session user = " + sessionUser);
		return "homepage";    	
    }  
    
    

}