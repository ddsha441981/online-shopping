package org.project.onlineshopping.controller;

import javax.servlet.http.HttpSession;

import org.project.onlineshopping.model.UserModel;
import org.project.shoppingbackend.dao.UserDAO;
import org.project.shoppingbackend.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

	//session management
	@Autowired
	HttpSession session;
	
	@Autowired
	private UserDAO userDAO;
	
	private UserModel userModel = null;
	
	@ModelAttribute("userModel")
	public UserModel getUserModel(){
		
		//check user available in session
		if(session.getAttribute("userModel")==null){
			
			/* check if user not available in session*/
			//add user model
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			User user = userDAO.getByEmail(authentication.getName());
			if(user!=null){
				
				//create a new userModel object to pass the user detail
				UserModel userModel = new UserModel();
				
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				userModel.setFullName(user.getFirstName() + " " + user.getLastName());
				
				if(userModel.getRole().equals("USER")){
					
					//set the cart only  if user is a buyer
					userModel.setCart(user.getCart());
				}
				
				//set the userModel in the session
				session.setAttribute("userModel", userModel);
				
				return userModel;
				
			}
		}
		
		return (UserModel) session.getAttribute("userModel");
	}
}
