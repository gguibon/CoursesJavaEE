package fr.amu.models;

import java.util.List;

public interface FoodDAO {
	Integer add(Food food);
	void update(Food food);
	void delete(Integer id);
	List<Food> findAll();
	Food findById( Integer id );
	List<Food> findByTag(String type);
	List<Food> findByDone(boolean bool);
	List<Food> findByTagAndDone(String tag, boolean done);
}

