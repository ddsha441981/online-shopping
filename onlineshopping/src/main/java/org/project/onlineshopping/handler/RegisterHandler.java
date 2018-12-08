package org.project.onlineshopping.handler;

import org.project.onlineshopping.model.RegisterModel;
import org.project.shoppingbackend.dao.UserDAO;
import org.project.shoppingbackend.dto.Address;
import org.project.shoppingbackend.dto.Cart;
import org.project.shoppingbackend.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

@Component
public class RegisterHandler {

	@Autowired
	private UserDAO userDAO;
	
	
	public RegisterModel init(){
		
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel, User user){
		
		registerModel.setUser(user);
		
	}
	
	public void addBilling(RegisterModel registerModel, Address billing){
		
		registerModel.setBilling(billing);
		
	}
	
	public String saveAll(RegisterModel registerModel){
		
		String transitionValue = "success";
		
		
		//fetch the user
		User user = registerModel.getUser();
		
		if(user.getRole().equals("USER")){
			//create a new cart
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
			System.out.println("In if statement "+cart.getUser());
			System.out.println("In if statement "+user.getRole());
		}
		else{
			System.out.println("Else statement "+user.getRole());
			System.out.println("Else statement "+user.getCart());
			
		}
		//save the user
		userDAO.addUser(user);
		
		//get the address
		Address billing = registerModel.getBilling();
		billing.setUserId(user.getId());
		billing.setBilling(true);
		
		//save the address
		userDAO.addAddress(billing);
		
		
		return transitionValue;
	}
	
	public String validateUser(User user, MessageContext error) {
		  String transitionValue = "success";
		  //check if password matches confirm password
		  
		   if(!(user.getPassword().equals(user.getConfirmPassword()))) {
		    error.addMessage(new MessageBuilder().error().source(
		      "confirmPassword").defaultText("Password does not match confirm password!").build());
		    transitionValue = "failure";    
		   }  
		   
		   //check the uniqueness of the email id
		   if(userDAO.getByEmail(user.getEmail())!=null) {
		    error.addMessage(new MessageBuilder().error().source(
		      "email").defaultText("Email address is already taken!").build());
		    transitionValue = "failure";
		   }
		  return transitionValue;
		 }
	
}
