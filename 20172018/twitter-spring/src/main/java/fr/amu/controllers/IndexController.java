package fr.amu.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@Autowired 
	private HttpSession httpSession;

	
    @GetMapping("/")
    public String index() {
    	String sessionUser= (String) httpSession.getAttribute("user");
    	
    	if(sessionUser==null) {
    		return "login";
    	}
		
		System.out.println("session user = " + sessionUser);
		return "index";
		
    	
    }

}