package fr.amu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.amu.models.Product;
import fr.amu.models.ProductDAO;

@Service
public class ProductService {
	
	@Autowired
	ProductDAO prdao;

	public ProductService() {	}

	public void addProduct(Product product) {
		prdao.add(product);
	}

	public Product getProduct(int id) {
		return prdao.findById(id);
	}

	public void removeProduct(int id) {
		prdao.delete(prdao.findById(id));
	}

	public List<Product> getProducts() {
		return prdao.findAll();
	}
	
	public List<Product> getCategoryProducts(String category) {
		return prdao.findByCategory(category);
	}
}
