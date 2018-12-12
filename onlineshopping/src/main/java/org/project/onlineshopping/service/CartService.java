package org.project.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.project.onlineshopping.model.UserModel;
import org.project.shoppingbackend.dao.CartLineDAO;
import org.project.shoppingbackend.dao.ProductDAO;
import org.project.shoppingbackend.dto.Cart;
import org.project.shoppingbackend.dto.CartLine;
import org.project.shoppingbackend.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartService")
public class CartService {

	
	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private HttpSession session;	
	
	//returns the cart of the user who has the logged in
	private Cart getCart(){
		
		return ((UserModel)session.getAttribute("userModel")).getCart();//this user model coming from global controller
	}
	
	//returns the entire cartlines
	public List<CartLine> getCartLines(){
		return cartLineDAO.list(this.getCart().getId());
	}

	
	
	public String manageCartLine(int cartLineId, int count) {
		// fetch the cart line based on the cartLineId
		CartLine cartLine = cartLineDAO.get(cartLineId);
		//when cart line not available
		if(cartLine == null){
			return "result=error";
		}
		else{
			//using one to one mapping
			Product product = cartLine.getProduct();
			double oldtotal = cartLine.getTotal();
			//when count is 3 and product is 1 than simple check first
			//checking if the product is available
			if(product.getQuantity() < count){
				//count = product.getQuantity();
				return "result=unavailable";
			}
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice() * count);
			//now call the DAO method to update the cartline
			cartLineDAO.update(cartLine);
			//fetch the cart from the session
			Cart cart = this.getCart();
			//now update the grand total
			cart.setGrandTotal(cart.getGrandTotal() - oldtotal + cartLine.getTotal());
			//call cartineDAO method update cart as well as
			cartLineDAO.updateCart(cart);
			
			return "result=updated";
		}
		
	}

	public String deleteCartLine(int cartLineId) {
		// fetch the cart Line
		CartLine cartLine = cartLineDAO.get(cartLineId);
		if(cartLine == null){
			
			return "result=error";
		}
		else{
			//fetch the cart before cartLine delete and update into database
			//fetch cart from the session
			//update the database 
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() - 1);
			cartLineDAO.updateCart(cart);
			
			//remove the cart
			cartLineDAO.delete(cartLine);
			return "result=deleted";
			}
	}

	public String addCartLine(int productId) {
		String response = null;
		//getting user from session who logged in
		Cart cart = this.getCart();
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		//check before add new cart line if null than add first cart line
		if(cartLine == null){
			//add new cartLine
			cartLine = new CartLine();
			//before adding fetch the product
			Product product = productDAO.get(productId);
			//set the cart id
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(true);
			
			cartLineDAO.add(cartLine);
			
			cart.setCartLines(cart.getCartLines() + 1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cartLineDAO.updateCart(cart);
			
			response = "result=added";
		}
		else{
			//check if the cart Line has been reached the maximum count
			if(cartLine.getProductCount() < 3){
				//update the product count for that cart Line
				response  = this.manageCartLine(cartLine.getId(), cartLine.getProductCount() + 1);
				
			}
			else{
				response = "result=maximum";
			}
		}
		
		
		return response ;
	}
	
}
