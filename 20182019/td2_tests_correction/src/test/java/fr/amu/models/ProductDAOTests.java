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
public class ProductDAOTests {

	// la base de données est auto remplie grâce à src/main/java/resources/data.sql
	@Autowired
	ProductDAO prdao;
	
	private static Product product = new Product("category", "productTitle", "img", "description", "date");
	
	@Test // dire que c'est un test (annotation classique de JUnit)
    @Transactional // pour gérer les transactions
    @Rollback(true) // pour remettre la BDD dans son état initial
    public void add() {
		Integer generatedId = prdao.add(product);
		List<Product> products = prdao.findAll();
		Assert.assertEquals(generatedId, Integer.valueOf(products.get(products.size()-1).getId() ));
    }
	
	@Test
	@Transactional
	@Rollback(true)
	public void findAll() {
		List<Product> products = prdao.findAll();
		Assert.assertTrue(products.size() == 2);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findByCategory() {
		List<Product> computerProducts = prdao.findByCategory("computer"); // comme on a auto remplie la BDD on connait les categories disponibles
		Assert.assertTrue(computerProducts.size() == 1); // il ne devrait en trouver qu'un seul car un seul a la catégorie 'computer'
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void findById() {
		// pour ne pas faire de l'arbitraire, on ajoute un produit pour obtenir son ID et le retrouver. le product en private static est donc utiliie pour éviter la redondance du code
		Integer generatedId = prdao.add(product);
		Assert.assertEquals(generatedId, Integer.valueOf(prdao.findById(generatedId).getId()) );
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void delete() {
		Integer generatedId = prdao.add(product);
		prdao.delete(prdao.findById(generatedId));
		Assert.assertTrue(prdao.findAll().size() == 2);
//		Assert.(prdao.findById(generatedId));
	}
}
