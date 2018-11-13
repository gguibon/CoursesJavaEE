package fr.amu.controllers;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.amu.Application;
import fr.amu.models.Product;
import fr.amu.services.ProductService;


@Controller
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	ServletContext context;
	
	@Autowired
	ProductService ps;
	
	/**
	 * version of add with no parameters
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String add(Map<String, Object> model){
//		Product p = new Product("clothes", "hat", "none", "a simple cap", "20190512");
//		ps.addProduct(p);
//		model.put("products", ps.getProducts() );
//    	return "homepage";
//    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpServletRequest request, Map<String, Object> model){
		Product p = new Product(request.getParameter("category"), request.getParameter("title"), "none", request.getParameter("description"), "20190512");
		ps.addProduct(p);
		model.put("products", ps.getProducts() );
    	return "homepage";
    }
	
	@RequestMapping( value = "/remove", method= RequestMethod.POST)
	public String remove( HttpServletRequest request, Map<String, Object> model) {
		log.info("ID = " + String.valueOf( request.getParameter("productId") ));
		int id = Integer.valueOf(request.getParameter("productId"));
		ps.removeProduct(id);
		model.put("products", ps.getProducts());
		return "homepage";
	}
	
	@RequestMapping( value = "/category", method= RequestMethod.POST)
	public String category( HttpServletRequest request, Map<String, Object> model) {
		log.info("Category = " + request.getParameter("category"));
		if(request.getParameter("category").equals("all"))
			model.put("products", ps.getProducts());
		else
			model.put("products", ps.getCategoryProducts(request.getParameter("category")) );
		return "homepage";
	}
}
