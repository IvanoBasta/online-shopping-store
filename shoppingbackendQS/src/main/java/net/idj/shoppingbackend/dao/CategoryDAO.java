package net.idj.shoppingbackend.dao;

import java.util.List;

import ner.idj.shoppingbackend.dto.Category;

public interface CategoryDAO {
	
	List<Category> list();
	Category get(int id);

}
