package fr.amu.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.amu.models.Product;
import fr.amu.models.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository pr;

	public ProductService() {
	}

	public void addProduct(Product product) {
		pr.save(product);
		System.out.println(getProductsJson());
	}
	
	public Product addProductFromJson(JsonObject object) {
		Product product = new Product();
    	product.setCategory(object.getString("category"));
    	product.setId(object.getString("id"));
    	product.setProductTitle(object.getString("productTitle"));
    	product.setImg("http://placehold.it/700x400");
    	product.setDate(LocalDateTime.now().toString());
    	product.setDescription("none");
    	pr.save(product);
    	return product;
	}

	public Product getProduct(String id) {
		return pr.findOne(id);

	}

	public void removeProduct(String id) {
		pr.delete(id);
	}

	public JsonArrayBuilder getProductsJson() {

		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		List<Product> listProducts = (List<Product>) pr.findAllByOrderByDateAsc();
		for (Product product : listProducts) {
			JsonObjectBuilder objectBuilder = product.toJson();
			arrayBuilder.add(objectBuilder);
		}

		return arrayBuilder;
	}

	// pourrait également être fait par polymorphisme
	public JsonArrayBuilder getCategoryProductsJson(String category) {

		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		List<Product> listProducts = (List<Product>) pr.findByCategory(category);
		for (Product product : listProducts) {
			JsonObjectBuilder objectBuilder = product.toJson();
			arrayBuilder.add(objectBuilder);
		}

		return arrayBuilder;
	}

}
