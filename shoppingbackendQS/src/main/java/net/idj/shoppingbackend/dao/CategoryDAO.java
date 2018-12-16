package net.idj.shoppingbackend.dao;

import java.util.List;

import net.idj.shoppingbackend.dto.Category;

public interface CategoryDAO {
	
	List<Category> list();
	Category get(int id);
	
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);

}
