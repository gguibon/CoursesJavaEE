package fr.amu.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Category {
	
	@Id
	private String name;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<Product> products = new HashSet<Product>(0);
	
	public Category() {}
	
	public Category(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
		
	
}
