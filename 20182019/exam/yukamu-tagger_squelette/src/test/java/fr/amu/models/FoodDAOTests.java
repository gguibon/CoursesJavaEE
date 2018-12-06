//package fr.amu.models;
//
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class FoodDAOTests {
//
//	@Autowired
//	FoodDAO fooddao;
//	
//	private static Food food = new Food("food", "url.png", Food.TAG.EXCELLENT.toString());
//	
//	@Test 
//    @Transactional
//    @Rollback(true) 
//    public void add() {
//		Integer generatedId = fooddao.add(food);
//		List<Food> foodList = fooddao.findAll();
//		Assert.assertEquals(generatedId, Integer.valueOf(foodList.get(foodList.size()-1).getId() ));
//    }
//	
//	@Test
//	@Transactional
//	@Rollback(true)
//	public void findAll() {
//		List<Food> foodList = fooddao.findAll();
//		Assert.assertTrue(foodList.size() == 61);
//	}
//	
//	@Test
//	@Transactional
//	@Rollback(true)
//	public void findByTag() {
//		List<Food> foodList = fooddao.findByTag(Food.TAG.NONE.toString()); 
//		for(Food f : foodList)
//			Assert.assertEquals(Food.TAG.NONE.toString(), f.getTag()); 
//	}
//	
//	@Test
//	@Transactional
//	@Rollback(true)
//	public void findById() {
//		Integer generatedId = fooddao.add(food);
//		Assert.assertEquals(generatedId, Integer.valueOf(fooddao.findById(generatedId).getId()) );
//	}
//	
//	@Test
//	@Transactional
//	@Rollback(true)
//	public void delete() {
//		Integer generatedId = fooddao.add(food);
//		List<Food> foodList = fooddao.findAll();
//		fooddao.delete(generatedId);
//		List<Food> foodList2 = fooddao.findAll();
//		Assert.assertTrue(foodList.size() == foodList2.size()+1);
//	}
//}
