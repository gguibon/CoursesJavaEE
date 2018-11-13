package fr.amu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.amu.models.Category;
import fr.amu.models.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository cr;

	public CategoryService() {	}

	public Category addCategory(Category category) {
		return cr.save(category);
	}

	public Optional<Category> getCategory(String name) {
		return cr.findById(name);
	}

	public void removeCategory(String name) {
		cr.deleteById(name);
	}

	public List<Category> getCategories() {
		return cr.findAll();
	}
	
}
