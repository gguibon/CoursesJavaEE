//package fr.amu.controllers;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
//
///*
// * Quelques import static qui peuvent être utiles
// * 
// * import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
// */
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//import fr.amu.services.FoodService;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(FoodController.class)
//public class FoodControllerTests {
//
//	@Autowired
//	private FoodController controller;
//
//	@Autowired
//	MockMvc mvc;
//	
//	@MockBean 
//	FoodService fs;
//
//	@Before
//	public void setup() {
//
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setPrefix("/WEB-INF/jsp");
//		viewResolver.setSuffix(".jsp");
//
//		mvc = standaloneSetup(controller).setViewResolvers(viewResolver).build();
//	}
//
//	@Test
//	public void contexLoads() throws Exception {
//		assertThat(controller).isNotNull(); 
//	}
//
//	@Test
//	public void add() throws Exception {
//		mvc.perform(post("/food/add").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).param("name", "value")
//				.param("brand", "value").param("type", "MEAT").param("quality", "BAD"))
//		.andExpect(view().name("index"));
//	}
//
//	 @Test
//	 public void remove() throws Exception {
//		 mvc.perform(post("/food/remove").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).param("foodId", "0"))
//			.andExpect(view().name("index"));
//	 }
//	
//	 @Test
//	 public void type() throws Exception {
//		 mvc.perform(post("/food/type").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).param("type", "MEAT"))
//			.andExpect(view().name("index"));
//	 }
//	 
//	 @Test
//	 public void favtoggle() throws Exception {
//		 mvc.perform(post("/food/fav").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE).param("id", "0"))
//			.andExpect(view().name("index"));
//	 }
//	 
//	 @Test
//	 public void favorites() throws Exception {
//		 mvc.perform(get("/food/favorites").contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
//			.andExpect(view().name("favoritespage"));
//	 }
//
//}
