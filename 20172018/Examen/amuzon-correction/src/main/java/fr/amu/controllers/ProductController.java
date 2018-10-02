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
import fr.amu.models.Product;
import fr.amu.services.CommentService;
import fr.amu.services.ProductService;




@RestController
public class ProductController {
	
	@Autowired 
	private HttpSession httpSession;

	@Autowired
	ProductService ps = Application.productService;
	
	@Autowired
	CommentService cs = Application.commentService;
	
	
    @RequestMapping(value = "/products", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String Submit(@RequestParam(value = "img", required = false) String img,
    		@RequestParam(value = "action", required = false) String action, 
    		@RequestParam(value = "productTitle", required = false) String productTitle,
    		@RequestParam(value = "id", required = false) String id, 
    		@RequestParam(value = "category", required = false) String category,
    		@RequestParam(value = "description", required = false) String description ){
		

    	String sessionUser= (String) httpSession.getAttribute("user");
    	
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("user", sessionUser); // on ajoute ce champ juste pour aider. L'utilisateur doit être passé par attribut de la servletcontext
		
        // pour suivre ce que nous avons vu en cours. Mais cela aurait très bien pu être /products/xxx xxx étant l'action désirée enlevant ainsi le parametre action et les if imbriqués
        if(action != null) {	
	        if (("remove".equals(action))&&("admin".equals(sessionUser))) {
	        	System.out.println("remove product");
	            ps.removeProduct( id );
	            System.out.println(id);
	        } else if (action.equals("add")) {
	        	Product product = new Product();
	        	product.setCategory(category);
	        	product.setImg("http://placehold.it/700x400");
	        	product.setDate(LocalDateTime.now().toString());
	        	System.out.println(productTitle + " " + description);
	        	product.setProductTitle(productTitle);
	        	product.setDescription(description);
	        	ps.addProduct(product);
	        	System.out.println("oi");
	        	obj.add("product", product.toJson());
	        } else if (action.equals("lookup")) {
	        	if(category != null) {
	        		obj.add("products", ps.getCategoryProductsJson(category));
	        	}else {obj.add("products", ps.getProductsJson());}
	        	
	        } else if (action.equals("quit")) {
	        	try {
					System.exit(0);
				} catch (Exception e) {e.printStackTrace();}
			}else {
				obj.add("products", ps.getProductsJson());
	        }	        
		}
        
        
        String jsonString = obj.build().toString(); 
        System.out.println(jsonString);
        return jsonString;
    }
    
    
    
    
    
    
    

}