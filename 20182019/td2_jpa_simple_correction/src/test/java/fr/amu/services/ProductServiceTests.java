package fr.amu.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.amu.models.Product;
import fr.amu.models.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTests {

	@Autowired
	ProductService ps;
	
	@Autowired
	ProductRepository pr;
	
	private static Product product = new Product("category", "productTitle", "img", "description", "date");
	
	@Test
	public void getProduct() {
		Product p = ps.addProduct(product);
//		assertThat(ps.getProduct(0), instanceOf(Product.class));
		Product pRetrieved = ps.getProduct(p.getId()).get();
		Assert.assertTrue(pRetrieved instanceof Product);
		Assert.assertTrue(p.getId() == pRetrieved.getId());
	}
	
}
