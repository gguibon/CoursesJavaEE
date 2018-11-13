package fr.amu.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/*
 * Quelques import static qui peuvent être utiles
 * 
 * import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import fr.amu.services.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTests {

	@Autowired
	private ProductController controller;

	@Autowired
	MockMvc mvc;
	
	@MockBean 
	ProductService ps;

	@Before
	public void setup() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp");
		viewResolver.setSuffix(".jsp");

		mvc = standaloneSetup(controller).setViewResolvers(viewResolver).build();
	}

	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull(); // on vérifie que le controller est bien lancé
	}

	@Test
	public void add() throws Exception {
		mvc.perform(post("/add").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).param("title", "value")
				.param("category", "value").param("description", "value"))
		.andExpect(view().name("homepage"));
	}

	 @Test
	 public void remove() throws Exception {
		 mvc.perform(post("/remove").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).param("productId", "12"))
			.andExpect(view().name("homepage"));
	 }
	
	 @Test
	 public void category() throws Exception {
		 mvc.perform(post("/category").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).param("category", "computer"))
			.andExpect(view().name("homepage"));
	 }

}
