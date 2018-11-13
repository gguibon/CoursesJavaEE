package fr.amu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.amu.models.Product;
import fr.amu.models.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository pr;

	public ProductService() {	}

	public Product addProduct(Product product) {
		return pr.save(product);
	}

	public Optional<Product> getProduct(Long id) {
		return pr.findById(id);
	}

	public void removeProduct(Long id) {
		pr.deleteById(id);
	}

	public List<Product> getProducts() {
		return pr.findAll();
	}
	
	public List<Product> getCategoryProducts(String category) {
		return pr.findByCategory(category);
	}
}
