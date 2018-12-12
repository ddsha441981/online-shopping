package org.project.shoppingbackend.dao;

import java.util.List;

import org.project.shoppingbackend.dto.Cart;
import org.project.shoppingbackend.dto.CartLine;
import org.project.shoppingbackend.dto.OrderDetail;

public interface CartLineDAO {

	
	//common method
	public CartLine get(int id);	
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean delete(CartLine cartLine);
	public List<CartLine> list(int cartId);
	
	/*bussiness method related to the cartline*/
				
	// list of available cartLine
	public List<CartLine> listAvailable(int cartId);
	// fetch the CartLine based on cartId and productId
	public CartLine getByCartAndProduct(int cartId, int productId);		
	
	//update the cart
	boolean updateCart(Cart cart);
	// adding order details
	boolean addOrderDetail(OrderDetail orderDetail);


	
}
