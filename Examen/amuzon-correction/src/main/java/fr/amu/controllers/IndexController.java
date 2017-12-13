package fr.amu.controllers;

import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fr.amu.Application;
import fr.amu.models.Comment;
import fr.amu.models.Product;
import fr.amu.services.CommentService;
import fr.amu.services.ProductService;
import fr.amu.util.Utils;

@Controller
public class IndexController {

	@Autowired 
	private HttpSession httpSession;

	@Autowired
	ServletContext context;
	
	@Autowired
	ProductService ps = Application.productService;
	
	@Autowired
	CommentService cs = Application.commentService;
	
	
	// quasi copié-collé du cours
    @GetMapping("/")
    public String index() {
    	String sessionUser= (String) httpSession.getAttribute("user");
    	
    	if(sessionUser==null) {
    		return "login";
    	}
		
		System.out.println("session user = " + sessionUser);
		return "homepage";
		
    	
    }
    
    
    // le mapping pourrait être changé et suivre ensuite une redirection vers "/" 
    @PostMapping("/")
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {
			
    	String content = "{}";
    	try {
			content = Utils.getStringFromInputStream(file.getInputStream()) ;
		} catch (IOException e) {
			e.printStackTrace();
		}

    	JsonReader jsonReader = Json.createReader(new StringReader(content));
    	JsonObject object = jsonReader.readObject();
    	jsonReader.close();
    	
    	Product product = ps.addProductFromJson(object);
    	
    	for( JsonValue o : object.getJsonArray("reviews").asJsonArray() ) {
    		JsonObject obj = o.asJsonObject();
    		Comment com = new Comment();
    		com.setCommentDate(obj.getString("date"));
    		com.setCommentAuthor("unknown");
    		com.setCommentId(obj.getString("id"));
    		com.setText(obj.getString("text"));
    		com.setProduct(product);
    		cs.addComment(com);
    	}
    	System.out.println(object.getString("productTitle"));
    	
    	
        return "homepage";    	
    }
  
    
    

}