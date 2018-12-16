package net.idj.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.idj.shoppingbackend.dao.CategoryDAO;
import net.idj.shoppingbackend.dto.Category;

@Transactional
@Repository("categoryDao")
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Category> list() {
		
		//HQL not the table name insade the database!!! ZAPAMTI OVO JE ENTITY NAME
		String selectActiveCategory = "FROM Category WHERE active = :active";
		
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
	
		query.setParameter("active", true);
		
		return query.getResultList();
	}
	
	
	//Getting single category based on id

	public Category get(int id) {
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	public boolean add(Category category) {
		try {
			// add the category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	//Updateing a single cateogry
	public boolean update(Category category) {
		try {
			// update the category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean delete(Category category) {
		
		category.setActive(false);
		try {
			// delete the category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
