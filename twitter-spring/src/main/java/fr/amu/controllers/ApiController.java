package fr.amu.controllers;

import java.time.LocalDateTime;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.amu.Application;
import fr.amu.models.Message;
import fr.amu.models.User;
import fr.amu.services.LiikeService;
import fr.amu.services.MessageService;
import fr.amu.services.UserService;




@RestController
public class ApiController {
	
	@Autowired 
	private HttpSession httpSession;

	@Autowired
	UserService us = Application.userService;
	
	@Autowired
	MessageService ms = Application.messageService;
	
	@Autowired
	LiikeService ls = Application.liikeService;
	
	
    @RequestMapping(value = "/api", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String Submit(@RequestParam(value = "content", required = false) String content,
    		@RequestParam(value = "action", required = false) String action, 
    		@RequestParam(value = "id", required = false) String id) {
		

    	String sessionUser= (String) httpSession.getAttribute("user");
    	
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("user", sessionUser);
		
        if(action != null) {	
	        if ("removeMessage".equals(action)) {
	        	System.out.println("remove message");
	            ms.removeMessage( id );
	            System.out.println(id);
	        } else if (action.equals("Add Message")) {
	        	Message message = new Message();
	        	message.setContent(content);
	        	message.setAuthor(sessionUser);
	        	message.setDate(LocalDateTime.now().toString());
	        	ms.addMessage(message);
	        	obj.add("message", message.toJson());
			} else if (action.equals("quit")) {
	        	try {
					System.exit(0);
				} catch (Exception e) {e.printStackTrace();}
			}else if (action.equals("like")) {
				Message msn = ms.getMessage( id );
				ls.toggleLiike(msn, msn.getId(), sessionUser);
				obj.add("messages", ms.getMessagesJson());
			}else if (action.equals("user")) {
				User user = new User();
				user.setName("loul");
				user.setPassword("hehe");
				us.addUser(user);
				obj.add("users", us.getUsersJson());
			}
			else {
				obj.add("messages", ms.getMessagesJson());
	        }	        
		}
        

        return obj.build().toString();
    }
    
    

}