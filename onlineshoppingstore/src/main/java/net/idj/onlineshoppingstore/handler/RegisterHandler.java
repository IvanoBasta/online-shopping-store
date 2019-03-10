package net.idj.onlineshoppingstore.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import net.idj.onlineshoppingstore.model.RegisterModel;
import net.idj.shoppingbackend.dao.UserDao;
import net.idj.shoppingbackend.dto.Address;
import net.idj.shoppingbackend.dto.Cart;
import net.idj.shoppingbackend.dto.User;


@Component
public class RegisterHandler {
	
	@Autowired
	private UserDao userDao;
	
	public RegisterModel init() {
		
		
		return new RegisterModel();
	}
	
	public String validateUser(User user, MessageContext error) {
		
		String transitionValue = "success";
		
		//checking if password matches confirm password
		
		if(!(user.getPassword().equals(user.getConfirmPassword()))) {
			
			error.addMessage(new MessageBuilder()
					.error()
					.source("confirmPassword")
					.defaultText("Password does not match the confirm password!")
					.build()
					);
			
			transitionValue = "failure";
			
		}
		
		//check the uniqueness of the email id
		
		if(userDao.getByEmail(user.getEmail())!=null) {
			
			error.addMessage(new MessageBuilder()
					.error()
					.source("email")
					.defaultText("Email address id already used!")
					.build()
					);
			
			transitionValue="failure";
		}
		
		return transitionValue;
		
	}
	
	public void addUser(RegisterModel registerModel, User user) {
		
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel, Address billing) {
		
		registerModel.setBilling(billing);
	}
	
	public String saveAll(RegisterModel model) {
		
		String transitionValue ="success";
		
		//fetch the user
		
		User user = model.getUser();
		
		if(user.getRole().equalsIgnoreCase("USER")) {	
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);		
		}
		
		//save the user
		
		userDao.addUser(user);
		
		// get Address
		
		Address billing = model.getBilling();
		billing.setUserId(user.getId());;
		billing.setBilling(true);
		
		//save the address
		userDao.addAdress(billing);
		
		return transitionValue;
	}
}
