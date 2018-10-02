package fr.amu.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.amu.services.UserService;

@Controller
public class LoginController {

	@Autowired 
	private HttpSession httpSession;
	
	@Autowired
	UserService us;
	
	/*
	 * Quasi copier coller de ce que nous avons vu en cours
	 */
    @GetMapping("/login")
    public String login(@RequestParam(value = "id", required = false) String id,
    		@RequestParam(value = "pwd", required = false) String password) {
    	
    	String sessionUser= (String) httpSession.getAttribute("user");
    	sessionUser = "gael";
    	if(sessionUser!=null && us.userExists(sessionUser) ) {
    		return "homepage";
    	}
    	else if(id!=null){
			httpSession.setAttribute("user", id);

			if (us.userLogin(id, password)) {
				System.out.println("Bienvenue " + sessionUser);
				return "homepage";
			}
    	}
    	
        return "login";
    }

}