package fr.amu.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.amu.Application;
import fr.amu.models.Product;
import fr.amu.services.ProductService;

@Controller
public class ProductPageController {

	@Autowired 
	private HttpSession httpSession;

	
	@Autowired
	ProductService ps = Application.productService;

	
    @RequestMapping(value = "/productpage", method = RequestMethod.GET)
    public String index(Model model, @RequestParam(value = "item", required = false) String item ){
    	String sessionUser= (String) httpSession.getAttribute("user");
    	
    	if(sessionUser==null) {
    		return "login";
    	}
		
    	Product product = ps.getProduct(item);
    	// ajout d'attributs dans la servletcontext (model) récupérables par une expression EL telle que ${productTitle} dans le template Thymeleaf (html)
    	model.addAttribute("productTitle", product.getProductTitle());
    	model.addAttribute("productDescription", product.getDescription());
    	model.addAttribute("productId", product.getId());
    	model.addAttribute("comments", product.getComments().size());
    	
		System.out.println("session user = " + sessionUser);
		return "productpage";
		
    	
    }

}