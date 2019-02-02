package net.idj.shoppingbackendQS.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.idj.shoppingbackend.dao.UserDao;
import net.idj.shoppingbackend.dto.Address;
import net.idj.shoppingbackend.dto.Cart;
import net.idj.shoppingbackend.dto.User;

public class UserTestCase {
	
private static AnnotationConfigApplicationContext context;
	
	
	private static UserDao userDAO;
	
	
	private User user;
	private Address address;
	private Cart cart;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.idj.shoppingbackend");
		context.refresh();
		userDAO = (UserDao)context.getBean("userDAO");
	
	}
	
//	@Test 
//	public void testAdd() {
//		
//		user = new User();
//		user.setFirstName("Ivan");
//		user.setLastName("Djuric");
//		user.setEmail("djuricivan43@gmail.com");
//		user.setContactNumber("0656154728");
//		user.setRole("USER");
//		user.setPassword("123456");
//		
//		
//		//add the user
//		assertEquals("Faild to add user!", true, userDAO.addUser(user));
//		
//		address = new Address();
//		address.setAddressLineOne("Dimitrija Tucovica, Beograd 29");
//		address.setAddressLineTwo("Marine Velickovic");
//		address.setCity("Belgrade");
//		address.setState("Serbia");
//		address.setCountry("Serbia");
//		address.setPostalCode("11000");
//		address.setBilling(true);
//		
//		// link the user with the address using user id
//		
//		address.setUserId(user.getId());
//		
//		//add the address
//		assertEquals("Faild to add address!", true, userDAO.addAdress(address));
//		
//		
//		if(user.getRole().equals("USER")) {
//			
//			// create a cart for this user
//			cart = new Cart();
//			cart.setUser(user);
//			
//			//add the cart
//			assertEquals("Faild to add cart!", true, userDAO.addCart(cart));
//			
//			//add a shipping address for this user
//			
//			address = new Address();
//			address.setAddressLineOne("Dimitrija Tucovica, Beograd 29");
//			address.setAddressLineTwo("Marine Velickovic");
//			address.setCity("Belgrade");
//			address.setState("Serbia");
//			address.setCountry("Serbia");
//			address.setPostalCode("11000");
//			//set shipping to true
//			address.setShipping(true);
//			
//			//link it with the user
//			
//			address.setUserId(user.getId());
//			
//			//add the shipping address
//			assertEquals("Faild to add shipping address!", true, userDAO.addAdress(address));
//			
//		}
//		
//	}
	
//	@Test 
//	public void testAdd() {
//		
//		user = new User();
//		user.setFirstName("Ivan");
//		user.setLastName("Djuric");
//		user.setEmail("djuricivan43@gmail.com");
//		user.setContactNumber("0656154728");
//		user.setRole("USER");
//		user.setPassword("123456");
//		
//		
//		if(user.getRole().equals("USER")) {
//			
//			// create a cart for this user
//			cart = new Cart();
//			
//			
//			cart.setUser(user);
//			
//			//attach cart with the user
//			user.setCart(cart);
//			
//		}
//		
//		//add the user
//		assertEquals("Faild to add user!", true, userDAO.addUser(user));
//		
//	}

//	
//	@Test 
//	public void testUpdateCart() {
//		
//		//fetch the user by its email
//		user = userDAO.getByEmail("djuricivan43@gmail.com");
//		
//		//get the cart of the user
//		cart = user.getCart();
//		cart.setGrandTotal(555);
//		cart.setCartLines(2);
//		
//		assertEquals("Faild to update the cart!",true, userDAO.updateCart(cart));
//		
//	}

	
//	@Test 
//	public void testAddAddress() {
//		
//		// we need to add an user
//		
//		user = new User();
//		user.setFirstName("Ivan");
//		user.setLastName("Djuric");
//		user.setEmail("djuricivan43@gmail.com");
//		user.setContactNumber("0656154728");
//		user.setRole("USER");
//		user.setPassword("123456");
//		
//		assertEquals("Faild to add user!", true, userDAO.addUser(user));
//		
//		//we are going to add address for billing
//		
//		address = new Address();
//		address.setAddressLineOne("Dimitrija Tucovica, Beograd 29");
//		address.setAddressLineTwo("Marine Velickovic");
//		address.setCity("Belgrade");
//		address.setState("Serbia");
//		address.setCountry("Serbia");
//		address.setPostalCode("11000");
//		address.setBilling(true);
//		
//		//attached the user to the address
//		address.setUser(user);
//		
//		assertEquals("Faild to add address", true, userDAO.addAdress(address));
//		
//		//we are going to add address for shipping
//		
//		address = new Address();
//		address.setAddressLineOne("Dimitrija Tucovica, Beograd 29");
//		address.setAddressLineTwo("Marine Velickovic");
//		address.setCity("Belgrade");
//		address.setState("Serbia");
//		address.setCountry("Serbia");
//		address.setPostalCode("11000");
//		address.setShipping(true);
//		
//		//attached the user to the address
//		address.setUser(user);
//		
//		assertEquals("Faild to add shipping address", true, userDAO.addAdress(address));
//		
//	}
	
//	@Test
//	public void testAddAddress() {
//		
//		user = userDAO.getByEmail("djuricivan43@gmail.com");
//		
//		address = new Address();
//		address.setAddressLineOne("Resavska, Novi Sad 29");
//		address.setAddressLineTwo("Milutina Milankovica");
//		address.setCity("Novi Sad");
//		address.setState("Serbia");
//		address.setCountry("Serbia");
//		address.setPostalCode("11000");
//		address.setShipping(true);
//		
//		address.setUser(user);
//		
//		assertEquals("Faild to add address", true, userDAO.addAdress(address));
//		
//	}
	
	@Test
	public void testGetAddresses() {
		
		user = userDAO.getByEmail("djuricivan43@gmail.com");
		
		assertEquals("Faild to fetch the list of address and size does not match", 2 , 
				userDAO.listShippingAddresses(user).size());
		
		assertEquals("Faild to fetch the billing address and size does not match", "Belgrade", 
				userDAO.getBillingAddress(user).getCity());
	}
}
