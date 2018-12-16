package net.idj.shoppingbackendQS.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.idj.shoppingbackend.dao.CategoryDAO;
import net.idj.shoppingbackend.dto.Category;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static CategoryDAO categoryDao;
	
	private Category category;

	@BeforeClass
	public static void init() {
		
		context = new AnnotationConfigApplicationContext();
		context.scan("net.idj.shoppingbackend");
		context.refresh();
		categoryDao = (CategoryDAO) context.getBean("categoryDao");
	}
	
//	@Test
//	public void testAddCategory() {
//		category = new Category();
//		category.setName("Television");
//		category.setImageUrl("CAT_32.png");
//		category.setDescription("This is some description for television!");
//		
//		assertEquals("Successfully added a category inside the table!",true, categoryDao.add(category));
//		
//	}
	
//	@Test 
//	public void testGetCategory() {
//		
//		category = categoryDao.get(33);
//		assertEquals("Successfully fetached a single category from the table!","Television", category.getName());
//	}
	
//	@Test 
//	public void testUpdateCategory() {
//		
//		category = categoryDao.get(33);
//		category.setName("Laptop");
//		//assertEquals parametri: tekst koji se ispisuje, povratna vrednost, izvrsenje metoda (Metod)
//		assertEquals("Successfully updated a single category from the table!",true, categoryDao.update(category));
//	}
	
//	@Test
//	public void testDeleteCategory() {	
//	category = categoryDao.get(33);
//	assertEquals("Successfully deleted a single category from the table!",true, categoryDao.delete(category));
//	}
	
//	@Test
//	public void testListCategory() {	
//	assertEquals("Successfully fetched the list of cateogries from the table!",2, categoryDao.list().size());
//	}
	
	
	@Test
	public void testCRUDCategory() {
		
		//add operation
		
		category = new Category();
		
		category.setName("Laptop");
		category.setImageUrl("CAT_1.png");
		category.setDescription("This is some description for laptop!");
		
		assertEquals("Successfully added a category inside the table!",true, categoryDao.add(category));
		
		category = new Category();
		
		category.setName("Television");
		category.setImageUrl("CAT_1.png");
		category.setDescription("This is some description for television!");
		
		assertEquals("Successfully added a category inside the table!",true, categoryDao.add(category));
		
		//fetching and updateing the category
		category = categoryDao.get(2);
		category.setName("TV");
		//assertEquals parametri: tekst koji se ispisuje, povratna vrednost, izvrsenje metoda (Metod)
		assertEquals("Successfully updated a single category from the table!",true, categoryDao.update(category));
		
		//Delete the category
		category = categoryDao.get(2);
		assertEquals("Successfully deleted a single category from the table!",true, categoryDao.delete(category));
		
		assertEquals("Successfully fetched the list of cateogries from the table!",1, categoryDao.list().size());
		
	}
	
	
	
	
	
	
	
	
	
	
}
