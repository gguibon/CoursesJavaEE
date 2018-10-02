package fr.amu.models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {

	public List<Product> findAllByOrderByDateAsc();
	
	public List<Product> findByCategory(String category);
}