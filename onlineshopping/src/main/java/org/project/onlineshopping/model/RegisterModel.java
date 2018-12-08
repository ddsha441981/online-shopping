package org.project.onlineshopping.model;

import java.io.Serializable;

import org.project.shoppingbackend.dto.Address;
import org.project.shoppingbackend.dto.Cart;
import org.project.shoppingbackend.dto.User;

public class RegisterModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private filed
	private User user;
	private Cart cart;
	private Address billing;
	
	//setter and getter 
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getBilling() {
		return billing;
	}
	public void setBilling(Address billing) {
		this.billing = billing;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
}
