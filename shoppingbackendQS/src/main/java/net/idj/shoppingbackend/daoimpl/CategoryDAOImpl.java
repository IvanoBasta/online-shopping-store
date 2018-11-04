package net.idj.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ner.idj.shoppingbackend.dto.Category;
import net.idj.shoppingbackend.dao.CategoryDAO;

@Repository("categoryDao")
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<>();

	static {
		Category category = new Category();

		category.setId(1);
		category.setName("Laptop");
		category.setImageUrl("CAT_1.png");
		category.setDescription("This is some description for laptop");

		categories.add(category);

		category = new Category();

		category.setId(2);
		category.setName("Television");
		category.setImageUrl("CAT_2.png");
		category.setDescription("This is some description for television");

		categories.add(category);

		category = new Category();

		category.setId(3);
		category.setName("Mobile");
		category.setImageUrl("CAT_3.png");
		category.setDescription("This is some description for mobile");

		categories.add(category);
	}

	@Override
	public List<Category> list() {

		return categories;
	}

	@Override
	public Category get(int id) {
		
		// enchanced for loop

		for (Category category : categories) {
			if (category.getId() == id) return category;
		}
		return null;
	}

}
