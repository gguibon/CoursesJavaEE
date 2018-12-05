//package fr.amu.services;
//
//import java.util.Map;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import fr.amu.models.Food;
//import fr.amu.models.FoodDAO;
//import fr.amu.models.Food.QUALITY;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class FoodServiceTests {
//
//	@Autowired
//	FoodService fs;
//	
//	@Autowired
//	FoodDAO prdao;
//	
//	
//	@Test
//	public void getQualityCounts() {
//		Map<String, Integer> map = fs.getQualityCounts();
//		for(QUALITY quality : Food.QUALITY.values()) 
//			Assert.assertTrue(map.containsKey(quality.toString()));
//	}
//	
//}
