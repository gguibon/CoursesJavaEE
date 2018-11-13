package fr.amu.models;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTests {
	
	@Autowired
	ProductRepository pr;
	
	private static Category category = new Category("computer");
	private static Product product = new Product(category, "productTitle", "img", "description", "date");
	private static Product product2 = new Product(category, "productTitle2", "img", "a second product", "date");
	
	@Test // dire que c'est un test (annotation classique de JUnit)
    @Transactional // pour gérer les transactions
    @Rollback(true) // pour remettre la BDD dans son état initial
    public void add() {
		Product savedProduct = pr.save(product);
		List<Product> products = pr.findAll();
		Assert.assertEquals(savedProduct.getId(), Long.valueOf(products.get(products.size()-1).getId() ));
    }
	
	@Test
	@Transactional
	@Rollback(true)
	public void findAll() {
		pr.save(product); // soit on save un produit ici, soit on le fait via data.sql (voir td2_correction)
		pr.save(product2);
		List<Product> products = pr.findAll();
		Assert.assertTrue(products.size() == 2);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findByCategory() {
		pr.save(product2); // soit on rempli à la main, soit on le fait via un data.sql dasn src/main/resources
		List<Product> computerProducts = pr.findByCategory(category); 
		Assert.assertTrue(computerProducts.size() == 1); // il ne devrait en trouver qu'un seul car un seul a la catégorie 'computer'
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findById() {
		// pour ne pas faire de l'arbitraire, on ajoute un produit pour obtenir son ID et le retrouver. le product en private static est donc utiliie pour éviter la redondance du code
		Product savedProduct = pr.save(product);
		Assert.assertEquals(savedProduct.getId(), Long.valueOf(pr.findById(savedProduct.getId()).get().getId()) );
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void delete() {
		pr.save(product);
		Product savedProduct = pr.save(product2);
		pr.deleteById(savedProduct.getId());
		Assert.assertTrue(pr.findAll().size() == 1);
	}
}
