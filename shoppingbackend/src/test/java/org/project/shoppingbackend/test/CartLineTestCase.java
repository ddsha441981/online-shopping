package org.project.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.project.shoppingbackend.dao.CartLineDAO;
import org.project.shoppingbackend.dao.ProductDAO;
import org.project.shoppingbackend.dao.UserDAO;
import org.project.shoppingbackend.dto.Cart;
import org.project.shoppingbackend.dto.CartLine;
import org.project.shoppingbackend.dto.Product;
import org.project.shoppingbackend.dto.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CartLineTestCase {

	private static AnnotationConfigApplicationContext context;
	
	
	private static CartLineDAO cartLineDAO = null;
	private static ProductDAO productDAO = null;
	private static UserDAO userDAO = null;
	
	private Product product = null;
	private User user = null;
	private Cart cart = null;
	private CartLine cartLine = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("org.project.shoppingbackend");
		context.refresh();
		productDAO = (ProductDAO)context.getBean("productDAO");
		userDAO = (UserDAO)context.getBean("userDAO"); 
		cartLineDAO = (CartLineDAO) context.getBean("cartLineDAO");
	}
	
	@Test
	public void testAddNewCartLine() {
		
		// 1. fetch the user 
		User user = userDAO.getByEmail("anjali@gmail.com");	
		
		// 2.  fetch the cart of that user
		Cart cart = user.getCart();
		
		// 3. fetch the product 
		Product product = productDAO.get(1);
		
		// 4.  Create a new CartLine
		cartLine = new CartLine();
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		
		assertEquals("Failed to add the CartLine!",true, cartLineDAO.add(cartLine));
		//update the cart
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() + 1);
		
		assertEquals("Failed to update the Cart!",true, cartLineDAO.updateCart(cart));
	}
	
}
