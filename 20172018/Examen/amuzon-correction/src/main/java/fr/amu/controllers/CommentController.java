package fr.amu.controllers;

import java.time.LocalDateTime;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.amu.Application;
import fr.amu.models.Product;
import fr.amu.services.CommentService;
import fr.amu.services.ProductService;




@RestController
public class CommentController {
	
	@Autowired 
	private HttpSession httpSession;
	
	@Autowired
	ServletContext context;

	@Autowired
	ProductService ps = Application.productService;
	
	@Autowired
	CommentService cs = Application.commentService;
	
	
	// les différents mapping auraient pu tous se trouver dans une seule classe java, selon vos préférences
    @RequestMapping(value = "/comments", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String Submit(@RequestParam(value = "img", required = false) String img,
    		@RequestParam(value = "action", required = false) String action, 
    		@RequestParam(value = "text", required = false) String text,
    		@RequestParam(value = "id", required = false) String id, 
    		@RequestParam(value = "category", required = false) String category,
    		@RequestParam(value = "product", required = false) String productId) {
		

    	String sessionUser= (String) httpSession.getAttribute("user");
    	
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("user", sessionUser);
        
		
        if(action != null) {	
        	Product product = new Product();
        	if(productId != null) product = ps.getProduct(productId);
        	if ("remove".equals(action)) {
	        	System.out.println("remove comment");
	            cs.deleteComment(sessionUser, id);
	            System.out.println(id);
	        } else if (action.equals("add")) {
	        	obj.add("comment", cs.addComment(product, text, LocalDateTime.now().toString(), sessionUser));
	        } else if (action.equals("quit")) {
	        	try {
					System.exit(0);
				} catch (Exception e) {e.printStackTrace();}
			}else {
				obj.add("comments", cs.getCommentsJson(product));
	        }	        
		}
        
        
        String jsonString = obj.build().toString(); 
        System.out.println(jsonString);
        return jsonString;
    }
    
    

}