package org.project.shoppingbackend.dao;

import java.util.List;

import org.project.shoppingbackend.dto.Address;
import org.project.shoppingbackend.dto.Cart;
import org.project.shoppingbackend.dto.User;

public interface UserDAO {

	
	//add user
	boolean addUser(User user);
	
	User getByEmail(String email);
	
	//add Address
	boolean addAddress(Address address);
	
	//Alternative way  to skip multiple query execute behind ManyToOne mapping in addresses
	
	//Address getBillingAddress(int userId);
	//List<Address> listShippingAddresses(int  userId);

	
	
	Address getBillingAddress(User user);
	
	List<Address> listShippingAddresses(User user);
	
	//updateCart cart
	boolean updateCart(Cart cart);
}
