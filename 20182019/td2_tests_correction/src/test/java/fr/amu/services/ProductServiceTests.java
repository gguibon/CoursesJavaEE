package fr.amu.services;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.amu.models.Product;
import fr.amu.models.ProductDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTests {

	@Autowired
	ProductService ps;
	
	@Autowired
	ProductDAO prdao;
	
	private static Product product = new Product("category", "productTitle", "img", "description", "date");
	
	@Test
	public void getProduct() {
		ps.addProduct(product);
//		assertThat(ps.getProduct(0), instanceOf(Product.class));
		Product product = ps.getProduct(0);
		Assert.assertTrue(ps.getProduct(0) instanceof Product);
		Assert.assertTrue(product.getId() == 0);
	}
	
}
