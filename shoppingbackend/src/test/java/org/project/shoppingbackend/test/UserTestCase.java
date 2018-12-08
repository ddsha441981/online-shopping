package org.project.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import java.io.Serializable;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.project.shoppingbackend.dao.UserDAO;
import org.project.shoppingbackend.dto.Address;
import org.project.shoppingbackend.dto.Cart;
import org.project.shoppingbackend.dto.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserTestCase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private static User user = null;
	private static Address address = null;
	@SuppressWarnings("unused")
	private static Cart cart = null;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("org.project.shoppingbackend");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	
/*	@Test
	public void testAdd(){
		
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		//add the user
		assertEquals("Failed to add user!!", true, userDAO.addUser(user));
		
		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
		//link the user with the address using user id
		address.setUserId(user.getId());
		
		//add the Address
		assertEquals("Failed to add address!!", true, userDAO.addAddress(address));
		
		if(user.getRole().equals("USER")){
			
			//create a cart for this user
			cart = new Cart();
			//one to one mapping here
			cart.setUser(user);
			//cart.setUserId(user.getId());
			
			//add the cart
			assertEquals("Failed to add cart!!", true, userDAO.addCart(cart));
			
			//add a shipping address for this user
			address = new Address();
			address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
			address.setAddressLineTwo("Near Kudrat Store");
			address.setCity("Mumbai");
			address.setState("Maharashtra");
			address.setCountry("India");
			address.setPostalCode("400001");
			//set shipping true
			address.setShipping(true);	
			
			//link with user
			address.setUserId(user.getId());
			
			//add the shipping address
			assertEquals("Failed to add Shipping address!!", true, userDAO.addAddress(address));
		}		
	}*/
	
	/*@Test
	public void testAdd(){
		
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
			
		if(user.getRole().equals("USER")){
			
			//create a cart for this user
			cart = new Cart();
			//one to one mapping here
			cart.setUser(user);
			
			//attach  cart with user
			user.setCart(cart);
			
		}
			//add the user
			assertEquals("Failed to add user!!", true, userDAO.addUser(user));
	}*/
	
	/*@Test
	public void testUpdateCart(){
		
		//fetch the user by its email 
		user = userDAO.getByEmail("dummy@gmail.com");
		
		//get the cart of the user
		cart = user.getCart();
		System.out.println(cart+"khjmbdmdbmjdfbjdfbmjfbmcbv");
		cart.setGrandTotal(55555);
		cart.setCartLines(2);
		
		assertEquals("Failed to the update the cart", true, userDAO.updateCart(cart));
		
	}
	*/
	
	/*@Test
	public void testAddAddress(){
		
		//we need to add an new  user
		
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		//add the user
		assertEquals("Failed to add user!!", true, userDAO.addUser(user));
		
		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);

		
		//we are going to add the address
		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);

		//attached the user to the address
		address.setUser(user);
		assertEquals("Failed to add Billing Address!!", true, userDAO.addAddress(address));
		
	
		
		//we are also going to add the shipping address
		
		address = new Address();
		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		//set shipping true
		address.setShipping(true);	

		//attached the user to the address
		address.setUser(user);
		assertEquals("Failed to add Shipping Address!!", true, userDAO.addAddress(address));
			
	}*/

	/*@Test
	public void testAddAddress(){
		
				user = userDAO.getByEmail("hr@gmail.com");
		
		
				//we are also going to add the shipping address
				address = new Address();
				address.setAddressLineOne("301/B Jadoo Society, Kishan Kanhaiya Nagar");
				address.setAddressLineTwo("Near Kudrat Store");
				address.setCity("Banglore");
				address.setState("Karnataka");
				address.setCountry("India");
				address.setPostalCode("400001");
				//set shipping true
				address.setShipping(true);	

				//attached the user to the address
				address.setUser(user);
				assertEquals("Failed to add Shipping Address!!", true, userDAO.addAddress(address));
	}*/
	
	/*@Test
	public void testGetAddresses(){
		
		user = userDAO.getByEmail("hr@gmail.com");
		
		assertEquals("Failed to fetch the list of address and size not match!!", 
				2, userDAO.listShippingAddresses(user).size());
		
		assertEquals("Failed to fetch the billing  of address and size not match!!", 
			   "Mumbai", userDAO.getBillingAddress(user).getCity());
	}*/
	
	/*@Test
	public void testBillingAddressesByUserId(){
		
		address = userDAO.getBillingAddress(2);
		
		assertEquals("Failed to fetch the list of address and size not match!!", 
				2, userDAO.getBillingAddress(2).getUserId());
		
	}*/
	
	
}
