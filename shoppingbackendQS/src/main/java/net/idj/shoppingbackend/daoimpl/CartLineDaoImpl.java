package net.idj.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.idj.shoppingbackend.dao.CartLineDAO;
import net.idj.shoppingbackend.dto.CartLine;

@Repository("cartLineDao")
@Transactional
public class CartLineDaoImpl implements CartLineDAO{

	@Autowired
	SessionFactory sessionFactory;

	public CartLine get(int id) {
		return sessionFactory.getCurrentSession().get(CartLine.class, id);
	}

	public boolean add(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().persist(cartLine);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean update(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().update(cartLine);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean delete(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().delete(cartLine);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public List<CartLine> list(int cartId) {
		String query = "FROM CartLine WHERE cartId = :cartId";
		 return sessionFactory.getCurrentSession()
					.createQuery(query,CartLine.class)
						.setParameter("cartId", cartId)
							.getResultList();
	}

	public List<CartLine> listAvailable(int cartId) {
		String query = "FROM CartLine WHERE cartId = :cartId and available = :available";
		 return sessionFactory.getCurrentSession()
					.createQuery(query,CartLine.class)
						.setParameter("cartId", cartId)
							.setParameter("available", true)
								.getResultList();
	}

	public CartLine getByCartAndProduct(int cartId, int productId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
