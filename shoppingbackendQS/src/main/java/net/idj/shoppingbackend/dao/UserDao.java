package net.idj.shoppingbackend.dao;
import java.util.List;

import net.idj.shoppingbackend.dto.Address;
import net.idj.shoppingbackend.dto.Cart;
import net.idj.shoppingbackend.dto.User;

public interface UserDao {
	
	//add an User
	boolean addUser(User user);
	
	public User getByEmail(String email);
	
	//add an address
	boolean addAdress(Address address);

	//alternative
	//	Address getBillingAddress (int userId);
	//	List<Address> listShippingAddresses(int userId);
	
	Address getBillingAddress (User user);
	List<Address> listShippingAddresses(User user);
	
	//update an Cart
	boolean updateCart(Cart cart);

}
