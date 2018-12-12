package org.project.shoppingbackend.dao;

import java.util.List;

import org.project.shoppingbackend.dto.Address;
import org.project.shoppingbackend.dto.Cart;
import org.project.shoppingbackend.dto.User;

public interface UserDAO {

	
	/** add user */
	boolean addUser(User user);
	
	/** user related operation*/
		User getByEmail(String email);
		//User get(int id);
	
	/**add Address*/
	boolean addAddress(Address address);
	
	/**Alternative way  to skip multiple query execute behind ManyToOne mapping in addresses*/
	
		//Address getBillingAddress(int userId);
		//List<Address> listShippingAddresses(int  userId);

	/** adding and updating a new address*/
	
		Address getAddress(int addressId);
		//boolean addAddress(Address address);
		//boolean updateAddress(Address address);
		Address getBillingAddress(int userId);
		List<Address> listShippingAddresses(int userId);
		
	/**By using multiple query in background*/
		
		//Address getBillingAddress(User user);
		//List<Address> listShippingAddresses(User user);
	
	/**updateCart cart*/
		//boolean addCart(Cart cart);
		//boolean updateCart(Cart cart);
	
}
