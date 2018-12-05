package fr.amu.models;

import java.util.List;

public interface FoodDAO {
	Integer add(Food food);
	void update(Food food);
	void delete(Integer id);
	List<Food> findAll();
	Food findById( Integer id );
	List<Food> findByType(String type);
	List<Food> findByQuality(String quality);
	List<Food> findByQualityAndType(String quality, String type);
	List<Food> findByFavorite(boolean bool);
	
}

